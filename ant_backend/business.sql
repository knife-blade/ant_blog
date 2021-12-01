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
    `title`        VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `description`  VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '摘要',
    `content`      LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
    `status`       INT(0) NOT NULL DEFAULT 0 COMMENT '0：正常。 1：正在审核。2：已删除',
    `create_time`  DATETIME(0),
    `update_time`  DATETIME(0),
    `deleted_flag` BIGINT(0) NOT NULL DEFAULT 0 COMMENT '0：未删除 其他：已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `t_user`
(
    `id`              BIGINT(0) NOT NULL AUTO_INCREMENT,
    `user_name`       VARCHAR(64),
    `nick_name`       VARCHAR(64),
    `avatar_url`      VARCHAR(256),
    `email`           VARCHAR(64),
    `password`        VARCHAR(64),
    `salt`            VARCHAR(64),
    `status`          INT(0) NOT NULL DEFAULT 0 COMMANT '0：正常 1：被锁定',
    `last_login_time` DATETIME(0),
    `create_time`     DATETIME(0),
    `update_time`     DATETIME(0),
    PRIMARY KEY (`id`) USING BTREE,
    INDEX             `INDEX_USERNAME`(`user_name`)
) ENGINE = InnoDB;

-- 账号：knife，密码：222333   密码会加盐，两次md5之后进行保存
INSERT INTO `t_user` VALUES (1, 'knife', '刀刃',
                             'https://i.postimg.cc/T2Cn6r8y/IOHWn0.png',
                             NULL, 'e4b8c8e43f8fabbe08d5aa67d58068ac', 'sCPKiMOoEl2ecPsfFhClcg==', 0, NULL, NULL, NULL);

-- 账号：sky，密码：123456    密码会加盐，两次md5之后进行保存
INSERT INTO `t_user` VALUES (2, 'sky', '天蓝',
                             'https://i.postimg.cc/Hn91nMcj/image.jpg',
                             NULL, '49b3146badc6479f5d6c306994a4a33e', '+SyMx8kT2CnKf6K2l3IH8g==', 0, NULL, NULL, NULL);

INSERT INTO `t_blog` VALUES (1, 1, 'Java中枚举的用法',
                             '本文介绍Java的枚举类的使用，枚举一般用于定义一些常量。枚举类完全单例、线程安全；性能高，常量值地址唯一，可以用==直接对比；不需重新编译引用类。枚举类编译时，没有把常量值编译到代码里，即使常量的值发生变化，也不会影响引用常量的类。',
                             '本文介绍Java的枚举类的使用，枚举一般用于定义一些常量。枚举类完全单例、线程安全；性能高，常量值地址唯一，可以用==直接对比；不需重新编译引用类。枚举类编译时，没有把常量值编译到代码里，即使常量的值发生变化，也不会影响引用常量的类。',
                             0, '2021-01-23 11:33:36', '2021-01-23 11:33:36', 0);
INSERT INTO `t_blog` VALUES (2, 1, 'Java中泛型的用法',
                             '本文介绍Java的泛型的使用。一些框架的源码中经常看到泛型，学习泛型可以帮助我们更好的阅读框架源码、理解框架，也可以提高编程水平',
                             '本文介绍Java的泛型的使用。一些框架的源码中经常看到泛型，学习泛型可以帮助我们更好的阅读框架源码、理解框架，也可以提高编程水平',
                             0, '2021-01-28 23:37:37', '2021-01-28 23:37:37', 0);
INSERT INTO `t_blog` VALUES (3, 1, 'Java的HashMap的原理',
                             '本文介绍Java的HashMap的原理。HashMap实际上是一个大的数组，key是数组的下标，value是数组的值。如果产生了哈希冲突，HashMap使用链地址法解决。JDK8中引入了红黑树，当同一个key上边大于8个元素时，链表会转化为红黑树，提高性能',
                             '本文介绍Java的HashMap的原理。HashMap实际上是一个大的数组，key是数组的下标，value是数组的值。如果产生了哈希冲突，HashMap使用链地址法解决。JDK8中引入了红黑树，当同一个key上边大于8个元素时，链表会转化为红黑树，提高性能',
                             0, '2021-05-28 09:06:06', '2021-05-28 09:06:06', 0);
INSERT INTO `t_blog` VALUES (4, 1, 'Java中BigDecimal的用法',
                             '本文介绍Java的BigDecimal的使用。BigDecimal处理数字很精确，而且可以表示大于16位的数。相比而言，double只能处理16位以内的数而且计算时不精确。比较经典的使用场景是金额',
                             '本文介绍Java的BigDecimal的使用。BigDecimal处理数字很精确，而且可以表示大于16位的数。相比而言，double只能处理16位以内的数而且计算时不精确。比较经典的使用场景是金额',
                             0, '2021-06-24 20:36:54', '2021-06-24 20:36:54', 0);
INSERT INTO `t_blog` VALUES (5, 1, 'Java中反射的用法',
                             '本文介绍Java的反射的使用。反射一般用于通过Class获得实例、调用方法等',
                             '本文介绍Java的反射的使用。反射一般用于通过Class获得实例、调用方法等',
                             0, '2021-10-28 22:24:18', '2021-10-28 22:24:18', 0);
INSERT INTO `t_blog` VALUES (6, 1, 'Java的ArrayList保证线程安全的方法',
                             'ArrayList不是线程安全的，也就是说：多个线程操作同一个ArrayList的时候会出现问题。有多种方法可以保证线程安全：Collections.synchronizedList(List)、JUC中的CopyOnWriteArrayList、Vector。推荐使用前两种，第三种性能很差，不推荐使用',
                             'ArrayList不是线程安全的，也就是说：多个线程操作同一个ArrayList的时候会出现问题。有多种方法可以保证线程安全：Collections.synchronizedList(List)、JUC中的CopyOnWriteArrayList、Vector。推荐使用前两种，第三种性能很差，不推荐使用',
                             0, '2021-08-28 21:31:20', '2021-08-28 21:31:20', 0);
INSERT INTO `t_blog` VALUES (7, 1, 'SpringBoot启动的流程',
                             '本文介绍SpringBoot启动的流程。分析Spring的启动流程有多种方法：1.构造一个AnnotationConfigApplicationContext对象，调用它的getBean(xxx.class)方法； 2.直接分析SpringBoot的启动流程。本文直接分析SpringBoot的启动流程。本文分析的版本：SpringBoot版本：2.3.0.RELEASE（其对应Spring：5.2.6.RELEASE）。',
                             '本文介绍SpringBoot启动的流程。分析Spring的启动流程有多种方法：1.构造一个AnnotationConfigApplicationContext对象，调用它的getBean(xxx.class)方法； 2.直接分析SpringBoot的启动流程。本文直接分析SpringBoot的启动流程。本文分析的版本：SpringBoot版本：2.3.0.RELEASE（其对应Spring：5.2.6.RELEASE）。',
                             0, '2021-09-25 19:02:55', '2021-09-25 19:02:55', 0);
INSERT INTO `t_blog` VALUES (8, 1, 'ArrayList扩容原理',
                             '本文介绍Java的ArrayList扩容的原理。直接new 一个ArrayList对象时（未指定初始容量大小）是一个空的数组，容量大小为零。当第一次调用ArrayList对象的add方法时，分配容量大小',
                             '本文介绍Java的ArrayList扩容的原理。直接new 一个ArrayList对象时（未指定初始容量大小）是一个空的数组，容量大小为零。当第一次调用ArrayList对象的add方法时，分配容量大小',
                             0, '2021-10-29 22:30:32',
                             '2021-10-29 22:30:32', 0);
INSERT INTO `t_blog` VALUES (9, 1, 'Java的类加载流程',
                             '本文介绍Java的类加载流程。Java的类加载流程为：加载=> 链接（验证+准备+解析）=> 初始化=> 使用=> 卸载。加载：通过一个类的全限定名获取定义此类的二进制字节流；将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构。在内存（不一定在堆，对于HotSpot在方法区）中生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各种数据的访问入口。',
                             '本文介绍Java的类加载流程。Java的类加载流程为：加载=> 链接（验证+准备+解析）=> 初始化=> 使用=> 卸载。加载：通过一个类的全限定名获取定义此类的二进制字节流；将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构。在内存（不一定在堆，对于HotSpot在方法区）中生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各种数据的访问入口。',
                             0, '2021-10-29 23:03:05', '2021-10-29 23:03:05', 0);
INSERT INTO `t_blog` VALUES (10, 1, 'SpringBoot整合RabbitMQ',
                             'SpringBoot引入RabbitMQ依赖，生产者调用发送方法，消费者进行订阅',
                             'SpringBoot引入RabbitMQ依赖，生产者调用发送方法，消费者进行订阅',
                             0, '2021-10-15 21:38:59', '2021-10-15 21:38:59', 0);
INSERT INTO `t_blog` VALUES (11, 1, 'ElasticSearch复杂查询的方法',
                             '本文介绍如何使用ElasticSearch进行复杂查询。ElasticSearch使用布尔查询进行复杂查询。布尔查询的所有子查询之间的逻辑关系是与（and）：只有当一个文档满足布尔查询中的所有子查询条件时，ElasticSearch引擎才认为该文档满足查询条件。',
                             '本文介绍如何使用ElasticSearch进行复杂查询。ElasticSearch使用布尔查询进行复杂查询。布尔查询的所有子查询之间的逻辑关系是与（and）：只有当一个文档满足布尔查询中的所有子查询条件时，ElasticSearch引擎才认为该文档满足查询条件。对于单个子句，只要一个文档满足该子句的查询条件，返回的逻辑结果就是true。对于should子句，它一般包含多个子查询条件，参数 minimum_should_match 控制文档必须满足should子句中的子查询条件的数量，只有当文档满足 minimum_should_match 时，should子句返回的逻辑结果才是true。',
                             0, '2021-03-28 18:55:01', '2021-03-28 18:55:01', 0);
INSERT INTO `t_blog` VALUES (12, 1, 'Kafka如何保证消息不丢失',
                             '本文介绍保证Kafka消息不丢失的方案。Kafka在生产者、服务器、消费者三个地方都可能导致消息丢失。',
                             '本文介绍保证Kafka消息不丢失的方案。Kafka在生产者、服务器、消费者三个地方都可能导致消息丢失。',
                             0, '2021-10-18 23:00:08', '2021-10-18 23:00:08', 0);
INSERT INTO `t_blog` VALUES (13, 1, 'Java的CAS的原理',
                             '本文介绍Java的CAS的原理。CAS是多线程的基础，含义是：Compare And SetCAS，Compare And Swap，即比较并交换。Doug lea大神在同步组件中大量使用CAS技术鬼斧神工地实现了Java多线程的并发操作，可以说CAS是整个JUC（java.util.concurrent）的基石。CAS性能很高，适合于高并发场景。',
                             '本文介绍Java的CAS的原理。CAS是多线程的基础，含义是：Compare And SetCAS，Compare And Swap，即比较并交换。Doug lea大神在同步组件中大量使用CAS技术鬼斧神工地实现了Java多线程的并发操作，可以说CAS是整个JUC（java.util.concurrent）的基石。CAS性能很高，适合于高并发场景。',
                             0, '2021-07-26 19:59:10', '2021-07-26 19:59:10', 0);
INSERT INTO `t_blog` VALUES (14, 1, 'Spring的AOP的原理',
                             '本文介绍Spring的AOP的原理。Spring的AOP是通过动态代理来实现的。',
                             '本文介绍Spring的AOP的原理。Spring的AOP是通过动态代理来实现的。',
                             0, '2021-08-28 20:59:58', '2021-08-28 20:59:58', 0);


INSERT INTO `t_blog` VALUES (15, 2, 'Vue-cli的使用',
                             'Vue-cli是Vue的一个脚手架工具',
                             'Vue-cli可以用来创建vue项目',
                             0, '2021-02-23 11:34:36', '2021-02-25 14:33:36', 0);
INSERT INTO `t_blog` VALUES (16, 2, 'Vuex的用法',
                             'Vuex是vue用于共享变量的插件',
                             '一般使用vuex来共享变量',
                             0, '2021-03-28 23:37:37', '2021-03-28 23:37:37', 0);
