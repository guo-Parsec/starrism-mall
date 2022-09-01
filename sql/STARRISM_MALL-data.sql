-- MySQL dump 10.13  Distrib 5.7.39, for Win64 (x86_64)
--
-- Host: 1.15.57.220    Database: STARRISM-MALL
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bms_dict_category`
--

DROP TABLE IF EXISTS `bms_dict_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bms_dict_category` (
  `id` bigint NOT NULL COMMENT '主键',
  `dict_category_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典类别码',
  `dict_category_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典类别名称',
  `sort` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '排序',
  `enable_status` smallint NOT NULL DEFAULT '0' COMMENT '启用状态(数据字典 0-启用 1-禁用)',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `add_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `modify_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `bms_dict_ui_dict_category_code_dict_code` (`dict_category_code`) COMMENT '后台数据字典类别表字典类别码唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='后台数据字典类别表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_dict_category`
--

INSERT INTO `bms_dict_category` (`id`, `dict_category_code`, `dict_category_name`, `sort`, `enable_status`, `add_time`, `add_name`, `modify_time`, `modify_name`) VALUES (1,'SEX','性别码','10',0,'2022-08-03 11:29:42','admin','2022-08-03 11:29:42','admin'),(2,'ENABLE_STATUS','启用状态码','20',0,'2022-08-03 11:29:42','admin','2022-08-03 11:29:42','admin'),(3,'VISIBLE_STATUS','显示状态码','30',0,'2022-08-03 11:29:42','admin','2022-08-03 11:29:42','admin');

--
-- Table structure for table `bms_dict_detail`
--

DROP TABLE IF EXISTS `bms_dict_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bms_dict_detail` (
  `id` bigint NOT NULL COMMENT '主键',
  `dict_category_id` bigint NOT NULL COMMENT '字典类别主键',
  `dict_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典码',
  `dict_value` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典值',
  `parent_id` bigint DEFAULT NULL COMMENT '上级字典主键',
  `hierarchical_path` varchar(225) COLLATE utf8mb4_unicode_ci DEFAULT '/' COMMENT '层级路径',
  `sort` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '排序',
  `enable_status` smallint NOT NULL DEFAULT '0' COMMENT '启用状态(数据字典 0-启用 1-禁用)',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `add_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `modify_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `bms_dict_ui_dict_category_code_dict_code` (`dict_category_id`,`dict_code`) COMMENT '后台字典类别表字典类别码与字典码唯一索引',
  KEY `bms_dict_i_a` (`dict_category_id`,`dict_code`) COMMENT '字典类别表与字典码索引',
  CONSTRAINT `bms_dict_detail_fk_category` FOREIGN KEY (`dict_category_id`) REFERENCES `bms_dict_category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='后台字典详情表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_dict_detail`
--

INSERT INTO `bms_dict_detail` (`id`, `dict_category_id`, `dict_code`, `dict_value`, `parent_id`, `hierarchical_path`, `sort`, `enable_status`, `add_time`, `add_name`, `modify_time`, `modify_name`) VALUES (1,1,'0','未知',NULL,'/','10',0,'2022-08-02 18:29:40','admin','2022-08-02 18:29:46','admin'),(2,1,'1','男性',NULL,'/','20',0,'2022-08-02 18:30:21','admin','2022-08-02 18:30:24','admin'),(3,1,'2','女性',NULL,'/','30',0,'2022-08-02 18:30:51','admin','2022-08-02 18:30:54','admin'),(4,2,'0','启用',NULL,'/','10',0,'2022-08-02 18:30:51','admin','2022-08-02 18:30:54','admin'),(5,2,'1','禁用',NULL,'/','20',0,'2022-08-02 18:30:51','admin','2022-08-02 18:30:54','admin'),(6,2,'2','删除',NULL,'/','30',0,'2022-08-02 18:30:51','admin','2022-08-02 18:30:54','admin'),(7,3,'0','显示',NULL,'/','10',0,'2022-08-02 18:30:51','admin','2022-08-02 18:30:54','admin'),(8,3,'1','隐藏',NULL,'/','20',0,'2022-08-02 18:30:51','admin','2022-08-02 18:30:54','admin');

--
-- Table structure for table `bms_menu`
--

DROP TABLE IF EXISTS `bms_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bms_menu` (
  `id` bigint NOT NULL COMMENT '主键',
  `parent_id` bigint DEFAULT NULL COMMENT '上级菜单id',
  `hierarchical_path` varchar(225) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '/' COMMENT '层级路径',
  `menu_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '菜单标识',
  `menu_title` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '菜单标题',
  `menu_icon` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '菜单图标',
  `visible_status` smallint NOT NULL DEFAULT '0' COMMENT '显示状态(数据字典 0-显示 1-隐藏)',
  `menu_level` int NOT NULL DEFAULT '0' COMMENT '菜单级别',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `component` varchar(225) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '前端组件',
  `menu_url` varchar(225) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '菜单访问url',
  `description` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '菜单描述',
  `enable_status` smallint NOT NULL DEFAULT '0' COMMENT '启用状态(数据字典 0-启用 1-禁用)',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `add_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `modify_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `bms_menu_ui_menu_code` (`menu_code`) COMMENT '系统菜单表菜单标识唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_menu`
--

INSERT INTO `bms_menu` (`id`, `parent_id`, `hierarchical_path`, `menu_code`, `menu_title`, `menu_icon`, `visible_status`, `menu_level`, `sort`, `component`, `menu_url`, `description`, `enable_status`, `add_time`, `add_name`, `modify_time`, `modify_name`) VALUES (1,0,'/','系统管理','系统管理','',0,1,10,'/bms/user/userQuery','/bms/user','系统管理目录',0,'2022-08-02 18:47:59','admin','2022-08-02 18:48:01','admin'),(2,1,'/1','用户管理','用户管理',' ',0,2,10,'/bms/user/userQuery','/bms/user','用户管理',0,'2022-08-02 18:50:19','admin','2022-08-02 18:50:20','admin'),(3,1,'/1','角色管理','角色管理',' ',0,2,20,'/bms/role/roleQuery','/bms/role','角色管理',0,'2022-08-02 18:50:19','admin','2022-08-02 18:50:20','admin'),(4,1,'/1','菜单管理','菜单管理','',0,2,30,'/bms/menu/menuQuery','/bms/menu','菜单管理',0,'2022-08-03 11:10:44','admin','2022-08-03 11:10:47','admin'),(5,1,'/1','资源管理','资源管理','',0,2,40,'/bms/resource/resourceQuery','/bms/resource','资源管理',0,'2022-08-03 11:11:56','admin','2022-08-03 11:11:56','admin'),(6,1,'/1','日志管理','日志管理','',0,2,50,'/bms/log/loginLogQuery','/bms/log/login','日志管理',0,'2022-08-03 11:13:30','admin','2022-08-03 11:13:32','admin'),(7,1,'/1','数据字典管理','数据字典管理','',0,2,60,'/bms/dict/dictQuery','/bms/dict','数据字典管理',0,'2022-08-03 11:14:41','admin','2022-08-03 11:14:42','admin'),(8,6,'/1/6','登录日志','登录日志','',0,3,10,'/bms/log/loginQuery','/bms/log/login','登录日志',0,'2022-08-03 11:17:04','admin','2022-08-03 11:17:05','admin'),(9,6,'/1/6','操作日志','操作日志','',0,3,20,'/bms/log/operateQuery','/bms/log/operate','操作日志',0,'2022-08-03 11:17:04','admin','2022-08-03 11:17:05','admin');

--
-- Table structure for table `bms_param`
--

DROP TABLE IF EXISTS `bms_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bms_param` (
  `id` bigint NOT NULL COMMENT '主键',
  `param_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '参数标识',
  `param_value` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '参数值',
  `param_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '参数名称',
  `enable_status` smallint NOT NULL DEFAULT '0' COMMENT '启用状态(数据字典 0-启用 1-禁用)',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `add_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `modify_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `bms_param_ui_param_code` (`param_code`) COMMENT '系统参数表参数标识唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统参数表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_param`
--

INSERT INTO `bms_param` (`id`, `param_code`, `param_value`, `param_name`, `enable_status`, `add_time`, `add_name`, `modify_time`, `modify_name`) VALUES (1,'DEFAULT_ROLE','CLIM','默认角色',0,'2022-08-29 17:08:10','admin','2022-08-29 17:08:16','admin'),(2,'DEFAULT_SEX','0','默认性别',0,'2022-08-30 09:20:44','admin','2022-08-30 09:20:46','admin'),(3,'DEFAULT_OPERATE_USER','admin','默认操作用户',0,'2022-08-30 09:27:17','admin','2022-08-30 09:27:21','admin');

--
-- Table structure for table `bms_resource`
--

DROP TABLE IF EXISTS `bms_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bms_resource` (
  `id` bigint NOT NULL COMMENT '主键',
  `resource_category_id` bigint DEFAULT NULL COMMENT '资源分类id',
  `resource_code` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '资源名称',
  `resource_url` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '资源路径',
  `description` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '资源描述',
  `enable_status` smallint NOT NULL DEFAULT '0' COMMENT '启用状态(数据字典 0-启用 1-禁用)',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `add_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `modify_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `bms_resource_ui_resource_name` (`resource_code`) COMMENT '系统资源表资源名称唯一索引',
  KEY `bms_resource_fk_category_id` (`resource_category_id`),
  CONSTRAINT `bms_resource_fk_category_id` FOREIGN KEY (`resource_category_id`) REFERENCES `bms_resource_category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_resource`
--

INSERT INTO `bms_resource` (`id`, `resource_category_id`, `resource_code`, `resource_url`, `description`, `enable_status`, `add_time`, `add_name`, `modify_time`, `modify_name`) VALUES (1,1,'test','test','test',0,'2022-08-31 15:41:12','admin','2022-08-31 15:41:20','admin'),(2,2,'test2','test2','test2',0,'2022-08-31 15:41:12','admin','2022-08-31 15:41:20','admin');

--
-- Table structure for table `bms_resource_category`
--

DROP TABLE IF EXISTS `bms_resource_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bms_resource_category` (
  `id` bigint NOT NULL COMMENT '主键',
  `category_code` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '资源类别码',
  `sort` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '排序',
  `description` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '资源类别描述',
  `enable_status` smallint NOT NULL DEFAULT '0' COMMENT '启用状态(数据字典 0-启用 1-禁用)',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `add_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `modify_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `bms_resource_category_uindex_category_code` (`category_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统资源类别表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_resource_category`
--

INSERT INTO `bms_resource_category` (`id`, `category_code`, `sort`, `description`, `enable_status`, `add_time`, `add_name`, `modify_time`, `modify_name`) VALUES (1,'NO_AUTH','10','该资源无需权限认证',0,'2022-08-03 11:41:31','admin','2022-08-03 11:41:35','admin'),(2,'LOGIN_AUTH','20','',0,'2022-08-31 14:56:57','admin','2022-08-31 14:56:59','admin');

--
-- Table structure for table `bms_role`
--

DROP TABLE IF EXISTS `bms_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bms_role` (
  `id` bigint NOT NULL COMMENT '主键',
  `role_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '角色标识',
  `role_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `parent_id` bigint DEFAULT NULL COMMENT '上级角色id',
  `hierarchical_path` varchar(225) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '/' COMMENT '层级路径',
  `description` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '角色描述',
  `sort` int NOT NULL DEFAULT '10' COMMENT '排序',
  `enable_status` smallint NOT NULL DEFAULT '0' COMMENT '启用状态(数据字典 0-启用 1-禁用)',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `add_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `modify_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `bms_role_ui_role_code` (`role_code`) COMMENT '系统角色表角色标识唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_role`
--

INSERT INTO `bms_role` (`id`, `role_code`, `role_name`, `parent_id`, `hierarchical_path`, `description`, `sort`, `enable_status`, `add_time`, `add_name`, `modify_time`, `modify_name`) VALUES (1,'SUPA','超级管理员',0,'/','超级管理员',10,0,'2022-08-01 15:41:11','admin','2022-08-01 15:41:17','admin'),(2,'PRUM','商品管理员',1,'/1','商品管理员',10,0,'2022-08-01 15:43:01','admin','2022-08-01 15:43:06','admin'),(3,'ORDM','订单管理员',1,'/1','订单管理员',20,0,'2022-08-01 15:43:58','admin','2022-08-01 15:44:01','admin'),(4,'CUSM','客服管理员',1,'/1','客服管理员',30,0,'2022-08-01 15:46:09','admin','2022-08-01 15:46:11','admin'),(5,'BUSM','商家用户',0,'/','商家用户',10,0,'2022-08-01 15:47:30','admin','2022-08-01 15:47:32','admin'),(6,'CLIM','普通客户',0,'/','普通客户',20,0,'2022-08-01 15:48:46','admin','2022-08-01 15:48:48','admin');

--
-- Table structure for table `bms_role_menu`
--

DROP TABLE IF EXISTS `bms_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bms_role_menu` (
  `role_id` bigint NOT NULL COMMENT '关联角色表id',
  `menu_id` bigint NOT NULL COMMENT '关联菜单表id',
  PRIMARY KEY (`menu_id`,`role_id`),
  KEY `bms_role_menu_fk_role_id` (`role_id`),
  CONSTRAINT `bms_role_menu_fk_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `bms_menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bms_role_menu_fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `bms_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_role_menu`
--


--
-- Table structure for table `bms_role_resource`
--

DROP TABLE IF EXISTS `bms_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bms_role_resource` (
  `role_id` bigint NOT NULL COMMENT '关联角色表id',
  `resource_id` bigint NOT NULL COMMENT '关联菜单表id',
  PRIMARY KEY (`resource_id`,`role_id`),
  KEY `bms_role_resource_fk_role_id` (`role_id`),
  CONSTRAINT `bms_role_resource_fk_resource_id` FOREIGN KEY (`resource_id`) REFERENCES `bms_resource` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bms_role_resource_fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `bms_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色资源关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_role_resource`
--


--
-- Table structure for table `bms_user`
--

DROP TABLE IF EXISTS `bms_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bms_user` (
  `id` bigint NOT NULL COMMENT '主键',
  `username` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
  `nickname` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '昵称',
  `email` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '邮箱地址',
  `phone_number` varchar(18) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '手机号',
  `avatar_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '头像地址',
  `enable_status` smallint NOT NULL DEFAULT '0' COMMENT '启用状态(数据字典 0-启用 1-禁用)',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `add_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `modify_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
  `sex` int DEFAULT '0' COMMENT '性别(数据字典)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `bms_user_ui_username` (`username`) COMMENT '后台用户表用户名唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='后台用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_user`
--

INSERT INTO `bms_user` (`id`, `username`, `password`, `nickname`, `email`, `phone_number`, `avatar_url`, `enable_status`, `add_time`, `add_name`, `modify_time`, `modify_name`, `sex`) VALUES (256293591973888,'admin','37029f3c646ca4d471884ac903754946','1','111','111',NULL,0,'2022-08-13 16:58:25','admin','2022-08-13 16:58:25','admin',1),(6702746078154752,'test1','ec07b5ef91f3ecbc6333c71338d8bb4b',NULL,'1774472843@qq.com',NULL,NULL,0,'2022-08-31 11:54:19','test1','2022-08-31 11:54:19','test1',0),(7127254333263872,'test2','86e64e594a3ede67f01ae36c4b653ad4',NULL,'166@qq.com',NULL,NULL,0,'2022-09-01 16:01:10','test2','2022-09-01 16:01:10','test2',0);

--
-- Table structure for table `bms_user_role`
--

DROP TABLE IF EXISTS `bms_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bms_user_role` (
  `user_id` bigint NOT NULL COMMENT '关联用户表id',
  `role_id` bigint NOT NULL COMMENT '关联角色表id',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `bms_user_role_fk_role_id` (`role_id`),
  CONSTRAINT `bms_user_role_fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `bms_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bms_user_role_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `bms_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_user_role`
--

INSERT INTO `bms_user_role` (`user_id`, `role_id`) VALUES (6702746078154752,6),(7127254333263872,6);

--
-- Table structure for table `undo_log`
--

DROP TABLE IF EXISTS `undo_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `undo_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch_id` bigint NOT NULL,
  `xid` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `context` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `undo_log`
--

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-01 16:14:03
