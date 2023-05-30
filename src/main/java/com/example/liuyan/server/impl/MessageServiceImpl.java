package com.example.liuyan.server.impl;

import com.example.liuyan.entity.dto.*;
import com.example.liuyan.entity.pojo.Message;
import com.example.liuyan.entity.pojo.User;
import com.example.liuyan.mapper.MessageMapper;
import com.example.liuyan.mapper.ReplyMapper;
import com.example.liuyan.mapper.UserMapper;
import com.example.liuyan.server.IMessageService;
import com.example.liuyan.util.ConstantUtil;
import com.example.liuyan.util.EmailUtil;
import com.example.liuyan.util.ResultUtil;
import com.example.liuyan.util.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 17:34
 * @packagename com.example.liuyan.server.impl
 * @classname MessageServiceImpl
 * @description
 */
@Slf4j
@Service
public class MessageServiceImpl implements IMessageService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    EmailUtil emailUtil;
    @Override
    public ResultUtil addMessage(Message message) {
        message.setCreatedate(new Date());
        message.setUid((Integer) UserHolder.getUser().get("userId"));
        message.setState(0);
        if (messageMapper.insertMessage(message) != 1) {
            throw new RuntimeException("留言失败");
        }
        log.info("新增留言: "+message.getId());
        return ResultUtil.ok();
    }

    @Override
    @Transactional
    public ResultUtil deleteById(Integer id) {
        Message message = messageMapper.findmeeageById(id);
        if (message == null) {
            return ResultUtil.fail(ConstantUtil.MESSAGE_FAIL_DELETE);
        }
        Integer userId = (Integer) UserHolder.getUser().get("userId");
        User user = userMapper.getUser(userId);
        Integer state = message.getState();
        // todo 如果用户是管理员直接删除这个留言以及回复
        if (user.getRole() == 1) {
            if (state == 1){
                messageMapper.deleteById(id);
                return ResultUtil.ok();
            } else {
                messageMapper.deleteReply(id);
                replyMapper.deleteReply(id);
            }
            log.info("删除一条留言: "+id);

        }

        // todo 不是管理员,则需要判断能否合法
        // todo 如果这是一条回复留言，则可以直接删除
        if (state == 1 && message.getUid() == user.getId()) {
            messageMapper.deleteById(id);
            log.info("删除一条回复留言: "+id);
            return ResultUtil.ok();
        }
        // todo 没有人回复可以直接删除
        Integer sum = replyMapper.sumReply(id);
        if (sum == 0 && message.getUid() ==  user.getId()) {
            messageMapper.deleteById(id);
            log.info("删除一条回复留言: "+id);
            return ResultUtil.ok();
        }
        return ResultUtil.fail(ConstantUtil.MESSAGE_FAIL_DELETEPER);
    }

    /**
     * 主页上进行查找相应的留言概要信息
     * @param form
     * @return
     */
    @Override
    public ResultUtil findAllMessageOutline(FindMessageListForm form) {
        List<MessageOutlineDTO> messageOutlineDTOList;

        // todo 查找是默认根据时间排序
        if (form.getType() == 0) {
            // todo 根据留言作者查找
            messageOutlineDTOList = messageMapper.findAllMessageOutlineByUser(form.getInfo());
        } else {
            // todo 根据主题信息查找
            messageOutlineDTOList = messageMapper.findAllMessageOutlineByTopic(form.getInfo());
        }
        for (MessageOutlineDTO user : messageOutlineDTOList) {
            // todo 遍历设置浏览数，回复数，最近更新以及最后一次回复的人和时间
            user.setReplyNum(replyMapper.sumReply(user.getMid()));
            Date lastUpdateTime = user.getLastUpdateTime();
            Integer replyId = replyMapper.findMessageOld(lastUpdateTime);
            if (replyId != null&&replyId != 0) {
                Message message = messageMapper.findmeeageById(replyId);
                user.setLastUpdateTime(message.getCreatedate());
                user.setLastReplyName(userMapper.findUserById(message.getUid()));
            }
        }


        if (form.getSort() == 0) {
            // todo 根据回复数量排序
            messageOutlineDTOList = messageOutlineDTOList.stream().sorted(Comparator.comparingInt(MessageOutlineDTO::getReplyNum)).collect(Collectors.toList());
        } else if (form.getSort() == 1) {
            // todo 根据浏览数排序
            messageOutlineDTOList = messageOutlineDTOList.stream().sorted(Comparator.comparingInt(MessageOutlineDTO::getViews)).collect(Collectors.toList());
        }

        long size = messageOutlineDTOList.size();
        log.info("查询到留言："+ messageOutlineDTOList);
        return ResultUtil.ok(messageOutlineDTOList,size);
    }

    @Override
    public ResultUtil findMessageDetail(Integer id) {
        Message message = messageMapper.findmeeageById(id);
        User user = userMapper.getUser(message.getUid());
        MessageAndUserDTO messageAndUserDTO = new MessageAndUserDTO(user, message);

        // todo 查看一次更新一次浏览数
        messageMapper.addView(id);

        // todo 获取回复id列表
        List<Integer> rids = replyMapper.findAllReply(id);
        if (rids == null || rids.size() == 0) {
            return ResultUtil.ok(new MessageDetailDataDTO(messageAndUserDTO,null));
        }
        List<MessageAndUserDTO> replyList = messageMapper.findReply(rids);

        MessageDetailDataDTO messageDetailDTO = new MessageDetailDataDTO(messageAndUserDTO, replyList);
        log.info("此条留言数据： "+messageDetailDTO);
        return ResultUtil.ok(messageDetailDTO);
    }

    @Override
    @Transactional
    public ResultUtil addReply(Integer mid, Integer isEmail, Message reply) {
        reply.setState(1);
        reply.setCreatedate(new Date());
        Map user1 = UserHolder.getUser();
        if (user1 != null) {
            reply.setUid((Integer) UserHolder.getUser().get("userId"));
        }else {
            reply.setUid(2);
        }

        try {
            Integer insertNum = messageMapper.insertMessage(reply);
            Integer rid = reply.getId();
            Integer replyNum = replyMapper.insertReply(mid, rid);
            if ( insertNum != 1 || replyNum != 1) {
                throw new RuntimeException("回复失败");
            }
            if (isEmail == 1) {
                Message message = messageMapper.findmeeageById(mid);
                User user = userMapper.getUser(message.getUid());
                String musername = user.getUsername();
                String emailTo = user.getEmail();
                String rusername;
                if (user1 != null) {
                    rusername = (String) UserHolder.getUser().get("userName");
                } else {
                    rusername = "游客" ;
                }
                EmailDTO emailDTO = new EmailDTO(musername, rusername, message, reply);
                emailUtil.sendMail(emailTo,emailDTO);
            }
            log.info("新增回复: " +reply);
            return ResultUtil.ok();
        } catch (Exception e) {
            throw new RuntimeException(ConstantUtil.REPLY_FAIL);
        }


    }
}
