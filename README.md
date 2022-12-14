# 项目简介

- 项目名：蚂蚁博客。   
- 整个项目完全从0开始开发。  
- 本项目是我为了学习Vue而建，是我独立开发的前后端分离项目，比较精简。 
  - 比如：数据库ID自增，没使用Redis等。（正常项目中要使用雪花算法生成ID，使用Redis缓存）


# 目录说明


- ant_backend：后端项目  
- ant_frontend：前端项目


# 功能


- 用户
  - 注册
  - 登录
  - 退出  
  - 用户列表
- 博客
  - 浏览
  - 发表
  - 编辑
  - 删除


# 技术栈

前后端分离。后端采用SpringBoot，前端采用Vue。  


## 后端


1. SpringBoot（spring-boot-starter-parent:2.4.2）
2. MyBatis-Plus（mybatis-plus-boot-starter:3.4.3.1）
3. MySQL（8.0.21）
4. Shiro（shiro-redis-spring-boot-starter:3.3.1）
5. JWT（java-jwt:3.18.1）
6. knife4j（swagger的升级版）（knife4j-spring-boot-starter:3.0.3）
7. hutool（hutool-all:5.5.7）
8. lombok（lombok（版本由spring-boot-starter-parent指定））


## 前端

1. vue（^2.6.11）
2. vue-router（^3.2.0）
3. vuex（^3.4.0）
4. element-uI（^2.15.0）
5. github-markdown-css（^4.0.0）
6. markdown-it（^12.0.4）
7. mavon-editor（^2.9.1）

# 效果展示
## 首页（博主列表）
![输入图片说明](%E6%95%88%E6%9E%9C%E5%9B%BE/%E9%A6%96%E9%A1%B5.png)

## 登录页
![输入图片说明](%E6%95%88%E6%9E%9C%E5%9B%BE/%E7%99%BB%E5%BD%95%E9%A1%B5.png)

## 博客列表（本人）
![输入图片说明](%E6%95%88%E6%9E%9C%E5%9B%BE/%E5%8D%9A%E5%AE%A2%E5%88%97%E8%A1%A8%EF%BC%88%E6%9C%AC%E4%BA%BA%E7%9A%84%EF%BC%89.png)

## 博客列表（他人）
![输入图片说明](%E6%95%88%E6%9E%9C%E5%9B%BE/%E5%8D%9A%E5%AE%A2%E5%88%97%E8%A1%A8%EF%BC%88%E4%BB%96%E4%BA%BA%E7%9A%84%EF%BC%89.png)

## 博客详情（本人）

![输入图片说明](%E6%95%88%E6%9E%9C%E5%9B%BE/%E5%8D%9A%E5%AE%A2%E8%AF%A6%E6%83%85.png)

## 博客详情（他人）
![输入图片说明](%E6%95%88%E6%9E%9C%E5%9B%BE/%E5%8D%9A%E5%AE%A2%E8%AF%A6%E6%83%85%EF%BC%88%E4%BB%96%E4%BA%BA%EF%BC%89.png)

## 博客编辑

![输入图片说明](%E6%95%88%E6%9E%9C%E5%9B%BE/%E5%8D%9A%E5%AE%A2%E7%BC%96%E8%BE%91.png)



