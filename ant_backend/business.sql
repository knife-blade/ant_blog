DROP DATABASE IF EXISTS blog;
CREATE DATABASE blog DEFAULT CHARACTER SET utf8;
USE blog;

DROP TABLE IF EXISTS `t_blog`;
DROP TABLE IF EXISTS `t_user`;
SET NAMES utf8mb4;

CREATE TABLE `t_blog`
(
    `id`          BIGINT(0) NOT NULL AUTO_INCREMENT,
    `user_id`     BIGINT(0) NOT NULL,
    `title`       VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `description` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '摘要',
    `content`     LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
    `status`      INT(0) NOT NULL DEFAULT 0,  -- 0：正常
    `create_time` DATETIME(0),
    `update_time` DATETIME(0),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `t_user`
(
    `id`              BIGINT(0) NOT NULL AUTO_INCREMENT,
    `user_name`       VARCHAR(64),
    `nick_name`       VARCHAR(64),
    `avatar_url`          VARCHAR(255),
    `email`           VARCHAR(64),
    `password`        VARCHAR(64),
    `salt`            VARCHAR(64),
    `status`          INT(0) NOT NULL DEFAULT 0, -- 0：正常 1：被锁定
    `last_login_time` DATETIME(0),
    `create_time`     DATETIME(0),
    `update_time`     DATETIME(0),
    PRIMARY KEY (`id`) USING BTREE,
    INDEX             `INDEX_USERNAME`(`user_name`)
) ENGINE = InnoDB;

INSERT INTO `t_blog` VALUES (1, 1, '博客1的标题', '博客1的摘要', '博客1的内容', 0, '2021-01-23 11:33:36', '2021-01-23 11:33:36');
INSERT INTO `t_blog` VALUES (2, 1, '博客2的标题', '博客2的摘要', '博客2的内容', 0, '2021-01-28 23:37:37', '2021-01-28 23:37:37');

-- 账号：knife，密码：222333    密码会加盐，两次md5之后进行保存
INSERT INTO `t_user` VALUES (1, 'knife', '刀刃',
        'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg',
        NULL, 'e4b8c8e43f8fabbe08d5aa67d58068ac', 'sCPKiMOoEl2ecPsfFhClcg==', 0, NULL, NULL, NULL);
