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

-- 账号：knife，密码：222333    密码会加盐，两次md5之后进行保存
INSERT INTO `t_user` VALUES (1, 'knife', '刀刃',
                             'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg',
                             NULL, 'e4b8c8e43f8fabbe08d5aa67d58068ac', 'sCPKiMOoEl2ecPsfFhClcg==', 0, NULL, NULL, NULL);

INSERT INTO `t_blog` VALUES (1, 1, 'Java中枚举的用法', '本文介绍Java的枚举类的使用', '枚举一般用于定义一些常量', 0, '2021-01-23 11:33:36', '2021-01-23 11:33:36');
INSERT INTO `t_blog` VALUES (2, 1, 'Java中泛型的用法', '本文介绍Java的泛型的使用', '一些框架的源码中经常看到泛型，学习泛型可以帮助我们更好的理解框架，也可以提高编程水平', 0, '2021-01-28 23:37:37', '2021-01-28 23:37:37');
INSERT INTO `t_blog` VALUES (3, 1, 'Java的HashMap的原理', '本文介绍Java的HashMap的原理',  'HashMap实际上是一个大的数组，key是数组的下标，value是数组的值', 0, '2021-01-28 23:37:37', '2021-01-28 23:37:37');
INSERT INTO `t_blog` VALUES (4, 1, 'Java中BigDecimal的用法', '本文介绍Java的BigDecimal的使用', 'BigDecimal处理数字很精确，而且可以表示大于16位的数', 0, '2021-01-28 23:37:37', '2021-01-28 23:37:37');
INSERT INTO `t_blog` VALUES (5, 1, 'Java中反射的用法', '本文介绍Java的反射的使用', '反射一般用于通过Class获得实例、调用方法等', 0, '2021-01-28 23:37:37', '2021-01-28 23:37:37');
INSERT INTO `t_blog` VALUES (6, 1, 'Java的ArrayList保证线程安全的方法', '本文介绍如何保证ArrayList的线程安全', 'ArrayList不是线程安全的，有多种方法可以保证线程安全', 0, '2021-01-28 23:37:37', '2021-01-28 23:37:37');
INSERT INTO `t_blog` VALUES (7, 1, 'SpringBoot启动的流程', '本文介绍SpringBoot启动的流程', '反射一般用于通过Class获得实例、调用方法等', 0, '2021-01-28 23:37:37', '2021-01-28 23:37:37');
INSERT INTO `t_blog` VALUES (8, 1, 'ArrayList扩容原理', '本文介绍Java的ArrayList扩容的原理', 'ArrayList实例化后容量为0，往里放数据时才会分配容量', 0, '2021-01-28 23:37:37', '2021-01-28 23:37:37');
INSERT INTO `t_blog` VALUES (9, 1, 'Java中反射的用法', '本文介绍Java的反射的使用', '反射一般用于通过Class获得实例、调用方法等', 0, '2021-01-28 23:37:37', '2021-01-28 23:37:37');
INSERT INTO `t_blog` VALUES (10, 1, 'Java中反射的用法', '本文介绍Java的反射的使用', '反射一般用于通过Class获得实例、调用方法等', 0, '2021-01-28 23:37:37', '2021-01-28 23:37:37');
INSERT INTO `t_blog` VALUES (11, 1, 'Java中反射的用法', '本文介绍Java的反射的使用', '反射一般用于通过Class获得实例、调用方法等', 0, '2021-01-28 23:37:37', '2021-01-28 23:37:37');
INSERT INTO `t_blog` VALUES (12, 1, 'Java中反射的用法', '本文介绍Java的反射的使用', '反射一般用于通过Class获得实例、调用方法等', 0, '2021-01-28 23:37:37', '2021-01-28 23:37:37');
INSERT INTO `t_blog` VALUES (13, 1, 'Java中反射的用法', '本文介绍Java的反射的使用', '反射一般用于通过Class获得实例、调用方法等', 0, '2021-01-28 23:37:37', '2021-01-28 23:37:37');
INSERT INTO `t_blog` VALUES (14, 1, 'Java中反射的用法', '本文介绍Java的反射的使用', '反射一般用于通过Class获得实例、调用方法等', 0, '2021-01-28 23:37:37', '2021-01-28 23:37:37');


