DROP DATABASE IF EXISTS blog;
CREATE DATABASE blog DEFAULT CHARACTER SET utf8;
USE blog;

DROP TABLE IF EXISTS `t_blog`;
DROP TABLE IF EXISTS `t_user`;
SET NAMES utf8mb4;

CREATE TABLE `t_blog`
(
    `id`           BIGINT(0) NOT NULL AUTO_INCREMENT,
    `user_id`      BIGINT(0) NOT NULL,
    `title`        VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `description`  VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '摘要',
    `content`      LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
    `status`       INT(0) NOT NULL DEFAULT 0,  -- 0：正常
    `create_time`  DATETIME(0),
    `update_time`  DATETIME(0),
    `deleted_flag` BIGINT(0) NOT NULL DEFAULT 0, -- 0：未删除 其他：已删除
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `t_user`
(
    `id`              BIGINT(0) NOT NULL AUTO_INCREMENT,
    `user_name`       VARCHAR(64),
    `nick_name`       VARCHAR(64),
    `avatar_url`      VARCHAR(255),
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

INSERT INTO `t_blog` VALUES (1, 1, 'Java中枚举的用法', '本文介绍Java的枚举类的使用', '枚举一般用于定义一些常量', 0, '2021-01-23 11:33:36', '2021-01-23 11:33:36', 0);
INSERT INTO `t_blog` VALUES (2, 1, 'Java中泛型的用法', '本文介绍Java的泛型的使用', '一些框架的源码中经常看到泛型，学习泛型可以帮助我们更好的理解框架，也可以提高编程水平', 0, '2021-01-28 23:37:37', '2021-01-28 23:37:37', 0);
INSERT INTO `t_blog` VALUES (3, 1, 'Java的HashMap的原理', '本文介绍Java的HashMap的原理',  'HashMap实际上是一个大的数组，key是数组的下标，value是数组的值', 0, '2021-05-28 09:06:06', '2021-05-28 09:06:06', 0);
INSERT INTO `t_blog` VALUES (4, 1, 'Java中BigDecimal的用法', '本文介绍Java的BigDecimal的使用', 'BigDecimal处理数字很精确，而且可以表示大于16位的数', 0, '2021-06-24 20:36:54', '2021-06-24 20:36:54', 0);
INSERT INTO `t_blog` VALUES (5, 1, 'Java中反射的用法', '本文介绍Java的反射的使用', '反射一般用于通过Class获得实例、调用方法等', 0, '2021-10-28 22:24:18', '2021-10-28 22:24:18', 0);
INSERT INTO `t_blog` VALUES (6, 1, 'Java的ArrayList保证线程安全的方法', '本文介绍如何保证ArrayList的线程安全', 'ArrayList不是线程安全的，有多种方法可以保证线程安全', 0, '2021-08-28 21:31:20', '2021-08-28 21:31:20', 0);
INSERT INTO `t_blog` VALUES (7, 1, 'SpringBoot启动的流程', '本文介绍SpringBoot启动的流程', '反射一般用于通过Class获得实例、调用方法等', 0, '2021-09-25 19:02:55', '2021-09-25 19:02:55', 0);
INSERT INTO `t_blog` VALUES (8, 1, 'ArrayList扩容原理', '本文介绍Java的ArrayList扩容的原理', 'ArrayList实例化后容量为0，往里放数据时才会分配容量', 0, '2021-10-29 22:30:32', '2021-10-29 22:30:32', 0);
INSERT INTO `t_blog` VALUES (9, 1, 'Java的类加载流程', '本文介绍Java的类加载流程', 'Java的类加载流程为：加载=> 链接（验证+准备+解析）=> 初始化=> 使用=> 卸载', 0, '2021-10-29 23:03:05', '2021-10-29 23:03:05', 0);
INSERT INTO `t_blog` VALUES (10, 1, 'SpringBoot整合RabbitMQ', '本文介绍SpringBoot整合RabbitMQ', 'SpringBoot引入RabbitMQ依赖，生产者调用发送方法，消费者进行订阅', 0, '2021-10-15 21:38:59', '2021-10-15 21:38:59', 0);
INSERT INTO `t_blog` VALUES (11, 1, 'ElasticSearch复杂查询的方法', '本文介绍如何使用ElasticSearch进行复杂查询', 'ElasticSearch使用布尔查询进行复杂查询', 0, '2021-03-28 18:55:01', '2021-03-28 18:55:01', 0);
INSERT INTO `t_blog` VALUES (12, 1, 'Kafka如何保证消息不丢失', '本文介绍保证Kafka消息不丢失的方案', 'Kafka在生产者、服务器、消费者三个地方都可能导致消息丢失', 0, '2021-10-18 23:00:08', '2021-10-18 23:00:08', 0);
INSERT INTO `t_blog` VALUES (13, 1, 'Java的CAS的原理', '本文介绍Java的CAS的原理', 'CAS是多线程的基础，含义是：Compare And Set', 0, '2021-07-26 19:59:10', '2021-07-26 19:59:10', 0);
INSERT INTO `t_blog` VALUES (14, 1, 'Spring的AOP的原理', '本文介绍Spring的AOP的原理', 'Spring的AOP是通过动态代理来实现的', 0, '2021-08-28 20:59:58', '2021-08-28 20:59:58', 0);
