CREATE DATABASE IF NOT EXISTS liuyan;
USE liuyan;
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
                            `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
                            `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
                            `password` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
                            `email` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
                            `state` tinyint(0) UNSIGNED NULL DEFAULT 0 COMMENT '判断是否在线',
                            `createdate` datetime(0) NULL DEFAULT NULL,
                            `role` tinyint(0) UNSIGNED NULL DEFAULT 0 COMMENT '这个用户扮演的角色，0为普通用户，1为管理员',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message`  (
                               `id` int(0) NOT NULL AUTO_INCREMENT,
                               `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '存放留言消息',
                               `uid` int(0) NOT NULL,
                               `createdate` datetime(0) NULL DEFAULT NULL,
                               `topic` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
                               `views` int(0) NULL DEFAULT 0,
                               `state` tinyint(0) NULL DEFAULT 0 COMMENT '表示这是一条留言还是回复留言，留言为0，回复留言为0',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `tb_reply`;
CREATE TABLE `tb_reply`  (
                             `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
                             `mid` int(0) UNSIGNED NOT NULL,
                             `rid` int(0) UNSIGNED NOT NULL,
                             `createdate` datetime(0) NULL DEFAULT NULL,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '作为回复留言的表格关联这条留言和回复这条留言的ID' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;