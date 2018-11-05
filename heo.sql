/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.14 : Database - helpeachother
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`helpeachother` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `helpeachother`;

/*Table structure for table `express` */

DROP TABLE IF EXISTS `express`;

CREATE TABLE `express` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `express_type` tinyint(4) NOT NULL COMMENT '快递类型',
  `get_code` varchar(10) NOT NULL COMMENT '取货码',
  `price` decimal(10,2) NOT NULL COMMENT '竞价',
  `location_id` tinyint(4) NOT NULL,
  `message` varchar(255) DEFAULT NULL COMMENT '备注',
  `created_at` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `express_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '快递状态',
  `provider_id` bigint(20) DEFAULT NULL COMMENT '提供者Id',
  `order_status` tinyint(4) DEFAULT '0' COMMENT '订单状态',
  `score` tinyint(4) DEFAULT NULL COMMENT '评分',
  `start_at` timestamp NULL DEFAULT NULL COMMENT '订单开始',
  `end_at` timestamp NULL DEFAULT NULL COMMENT '订单结束',
  PRIMARY KEY (`id`),
  KEY `provider_id` (`provider_id`),
  CONSTRAINT `express_ibfk_1` FOREIGN KEY (`provider_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `express` */

insert  into `express`(`id`,`user_id`,`phone`,`express_type`,`get_code`,`price`,`location_id`,`message`,`created_at`,`updated_at`,`express_status`,`provider_id`,`order_status`,`score`,`start_at`,`end_at`) values (1,1,'17611233021',1,'6666','2.50',1,'尽快','2018-09-24 15:49:48','2018-09-24 15:49:57',3,NULL,0,NULL,NULL,NULL),(2,1,'17611233021',2,'123','2.50',1,'hhh','2018-09-28 15:39:48','2018-09-28 15:39:48',1,NULL,0,NULL,NULL,NULL);

/*Table structure for table `express_info` */

DROP TABLE IF EXISTS `express_info`;

CREATE TABLE `express_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `location` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `express_info` */

insert  into `express_info`(`id`,`name`,`location`) values (1,'圆通快递','大活'),(2,'京东快递','中心浴室'),(3,'顺丰快递','中心浴室'),(4,'中通快递','南门'),(5,'申通快递','中心浴室'),(6,'百世快递','中心浴室'),(7,'天天快递','南门');

/*Table structure for table `express_order` */

DROP TABLE IF EXISTS `express_order`;

CREATE TABLE `express_order` (
  `id` bigint(11) NOT NULL,
  `needer_id` bigint(20) NOT NULL,
  `provider_id` bigint(20) NOT NULL,
  `express_id` bigint(11) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `created_at` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `key1` (`needer_id`) USING BTREE,
  KEY `key2` (`provider_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `express_order` */

insert  into `express_order`(`id`,`needer_id`,`provider_id`,`express_id`,`price`,`status`,`created_at`,`updated_at`) values (1,1,2,1,'2.50',3,'2018-09-24 16:01:04','2018-09-24 16:01:12');

/*Table structure for table `location_info` */

DROP TABLE IF EXISTS `location_info`;

CREATE TABLE `location_info` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `part` tinyint(4) NOT NULL COMMENT '1A2B3C',
  `name` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `location_info` */

insert  into `location_info`(`id`,`part`,`name`,`content`) values (1,1,'A1',NULL),(2,1,'A2',NULL),(3,1,'A3',NULL),(4,1,'A4',NULL),(5,1,'A5',NULL),(6,1,'A6',NULL),(7,1,'A7',NULL),(8,1,'A8',NULL),(9,1,'A9',NULL),(10,1,'A10',NULL),(11,1,'A11',NULL),(12,1,'A12',NULL),(13,1,'A13',NULL),(14,1,'A14',NULL),(15,1,'A15',NULL),(16,1,'A16',NULL),(17,1,'A17',NULL),(18,2,'B1',NULL),(19,2,'B2',NULL),(20,2,'B3',NULL),(21,2,'B4',NULL),(22,2,'B6',NULL),(23,2,'B8',NULL),(24,2,'B10',NULL),(25,2,'B12',NULL),(26,2,'B14',NULL),(27,3,'C1',NULL),(28,3,'C2',NULL),(29,3,'C3',NULL),(30,3,'C4',NULL),(31,3,'C5',NULL),(32,3,'C6',NULL),(33,3,'C7',NULL),(34,3,'C8',NULL),(35,3,'C9',NULL),(36,3,'C10',NULL),(37,3,'C11',NULL),(38,3,'C12',NULL),(39,3,'C13',NULL),(40,3,'C14',NULL);

/*Table structure for table `login_information` */

DROP TABLE IF EXISTS `login_information`;

CREATE TABLE `login_information` (
  `info_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` int(1) DEFAULT '0' COMMENT '登录状态 0成功 1失败',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=209 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

/*Data for the table `login_information` */

insert  into `login_information`(`info_id`,`user_name`,`ipaddr`,`login_location`,`browser`,`os`,`status`,`msg`,`login_time`) values (146,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(147,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(148,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(149,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(150,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(151,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(152,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(153,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(154,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(155,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(156,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(157,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(158,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(159,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(160,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(161,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(162,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(163,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(164,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(165,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(166,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(167,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(168,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(169,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(170,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(171,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(172,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(173,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(174,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(175,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(176,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(177,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(178,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(179,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(180,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(181,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(182,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(183,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(184,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(185,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(186,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(187,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(188,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(189,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(190,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(191,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(192,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(193,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(194,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(195,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(196,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(197,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(198,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(199,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(200,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(201,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(202,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(203,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(204,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL),(205,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(206,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(207,'zwt','127.0.0.1','','Chrome','Windows 10',0,'登录成功',NULL),(208,'zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10',0,'登录成功',NULL);

/*Table structure for table `qrtz_blob_triggers` */

DROP TABLE IF EXISTS `qrtz_blob_triggers`;

CREATE TABLE `qrtz_blob_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `blob_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_blob_triggers` */

/*Table structure for table `qrtz_calendars` */

DROP TABLE IF EXISTS `qrtz_calendars`;

CREATE TABLE `qrtz_calendars` (
  `sched_name` varchar(120) NOT NULL,
  `calendar_name` varchar(200) NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_calendars` */

/*Table structure for table `qrtz_cron_triggers` */

DROP TABLE IF EXISTS `qrtz_cron_triggers`;

CREATE TABLE `qrtz_cron_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `cron_expression` varchar(200) NOT NULL,
  `time_zone_id` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_cron_triggers` */

/*Table structure for table `qrtz_fired_triggers` */

DROP TABLE IF EXISTS `qrtz_fired_triggers`;

CREATE TABLE `qrtz_fired_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `entry_id` varchar(95) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) NOT NULL,
  `job_name` varchar(200) DEFAULT NULL,
  `job_group` varchar(200) DEFAULT NULL,
  `is_nonconcurrent` varchar(1) DEFAULT NULL,
  `requests_recovery` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_fired_triggers` */

/*Table structure for table `qrtz_job_details` */

DROP TABLE IF EXISTS `qrtz_job_details`;

CREATE TABLE `qrtz_job_details` (
  `sched_name` varchar(120) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `job_class_name` varchar(250) NOT NULL,
  `is_durable` varchar(1) NOT NULL,
  `is_nonconcurrent` varchar(1) NOT NULL,
  `is_update_data` varchar(1) NOT NULL,
  `requests_recovery` varchar(1) NOT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_job_details` */

/*Table structure for table `qrtz_locks` */

DROP TABLE IF EXISTS `qrtz_locks`;

CREATE TABLE `qrtz_locks` (
  `sched_name` varchar(120) NOT NULL,
  `lock_name` varchar(40) NOT NULL,
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_locks` */

insert  into `qrtz_locks`(`sched_name`,`lock_name`) values ('HeoScheduler','STATE_ACCESS'),('HeoScheduler','TRIGGER_ACCESS'),('RuoyiScheduler','STATE_ACCESS'),('RuoyiScheduler','TRIGGER_ACCESS');

/*Table structure for table `qrtz_paused_trigger_grps` */

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;

CREATE TABLE `qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_paused_trigger_grps` */

/*Table structure for table `qrtz_scheduler_state` */

DROP TABLE IF EXISTS `qrtz_scheduler_state`;

CREATE TABLE `qrtz_scheduler_state` (
  `sched_name` varchar(120) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_scheduler_state` */

insert  into `qrtz_scheduler_state`(`sched_name`,`instance_name`,`last_checkin_time`,`checkin_interval`) values ('HeoScheduler','LAPTOP-1M5RA4JD1541227439909',1541227444501,15000),('RuoyiScheduler','LAPTOP-1M5RA4JD1538705913127',1538705930338,15000);

/*Table structure for table `qrtz_simple_triggers` */

DROP TABLE IF EXISTS `qrtz_simple_triggers`;

CREATE TABLE `qrtz_simple_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_simple_triggers` */

/*Table structure for table `qrtz_simprop_triggers` */

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;

CREATE TABLE `qrtz_simprop_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `str_prop_1` varchar(512) DEFAULT NULL,
  `str_prop_2` varchar(512) DEFAULT NULL,
  `str_prop_3` varchar(512) DEFAULT NULL,
  `int_prop_1` int(11) DEFAULT NULL,
  `int_prop_2` int(11) DEFAULT NULL,
  `long_prop_1` bigint(20) DEFAULT NULL,
  `long_prop_2` bigint(20) DEFAULT NULL,
  `dec_prop_1` decimal(13,4) DEFAULT NULL,
  `dec_prop_2` decimal(13,4) DEFAULT NULL,
  `bool_prop_1` varchar(1) DEFAULT NULL,
  `bool_prop_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_simprop_triggers` */

/*Table structure for table `qrtz_triggers` */

DROP TABLE IF EXISTS `qrtz_triggers`;

CREATE TABLE `qrtz_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `next_fire_time` bigint(13) DEFAULT NULL,
  `prev_fire_time` bigint(13) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `trigger_state` varchar(16) NOT NULL,
  `trigger_type` varchar(8) NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) DEFAULT NULL,
  `calendar_name` varchar(200) DEFAULT NULL,
  `misfire_instr` smallint(2) DEFAULT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_triggers` */

/*Table structure for table `score` */

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `provider_id` bigint(20) NOT NULL COMMENT '跑腿人ID',
  `needer_id` bigint(20) NOT NULL COMMENT '评分人ID',
  `attitude` tinyint(4) NOT NULL DEFAULT '0' COMMENT '服务态度',
  `speed` tinyint(4) NOT NULL DEFAULT '0' COMMENT '送达速度',
  `comment` text COLLATE utf8mb4_unicode_ci COMMENT '评价',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `score` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(16) NOT NULL,
  `pass_word` varchar(50) NOT NULL,
  `salt` int(11) NOT NULL,
  `email` varchar(20) NOT NULL COMMENT '邮件',
  `major_id` varchar(10) DEFAULT NULL COMMENT '专业编号',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `location_id` tinyint(4) NOT NULL COMMENT '宿舍号',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1用户2跑腿3都是',
  `alipay_id` varchar(11) DEFAULT NULL COMMENT '支付宝',
  `wechat_id` varchar(16) DEFAULT NULL COMMENT '微信',
  `qq_number` varchar(11) DEFAULT NULL COMMENT 'QQ',
  `picture` varchar(255) DEFAULT NULL,
  `student_id` varchar(10) DEFAULT '' COMMENT '如果用户类型为2，3必填',
  `created_at` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_by` tinyint(4) DEFAULT NULL COMMENT '1用户2系统',
  `login_ip` varchar(20) DEFAULT NULL,
  `last_login` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `nick_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `user` */

insert  into `user`(`id`,`user_name`,`pass_word`,`salt`,`email`,`major_id`,`phone`,`location_id`,`type`,`alipay_id`,`wechat_id`,`qq_number`,`picture`,`student_id`,`created_at`,`updated_at`,`modify_by`,`login_ip`,`last_login`,`nick_name`) values (1,'zwt','bdf94cae8883b035c2976e1b1e6c5d5c',11111,'1129114837@qq.com','1','17611233021',1,1,NULL,NULL,NULL,NULL,'','2018-09-18 16:54:44','2018-09-18 16:54:44',NULL,'127.0.0.1','2018-10-21 09:47:31',NULL),(2,'yangxu','15dd198e986482456a43108fce74cdc7',9258,'123@qq.com',NULL,'1761123302',1,1,'hhhh','ggg','111',NULL,'','2018-10-01 18:05:35','2018-10-01 18:05:35',NULL,NULL,'2018-10-01 18:05:35',NULL);

/*Table structure for table `user_online` */

DROP TABLE IF EXISTS `user_online`;

CREATE TABLE `user_online` (
  `sessionId` varchar(50) NOT NULL DEFAULT '' COMMENT '用户会话id',
  `user_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestsamp` datetime DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) DEFAULT '0' COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在线用户记录';

/*Data for the table `user_online` */

insert  into `user_online`(`sessionId`,`user_name`,`ipaddr`,`login_location`,`browser`,`os`,`status`,`start_timestsamp`,`last_access_time`,`expire_time`) values ('008c9494-f281-4ef4-b5f1-f6f868110600','zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10','在线','2018-09-15 08:50:39','2018-09-15 08:50:44',1800000),('14dc0f20-7b68-487a-a730-871cdcb34e7f','zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10','在线','2018-09-21 16:43:28','2018-09-21 16:43:54',1800000),('6021f840-2a15-4025-923b-110dd7f69600','zwt','127.0.0.1','','Chrome','Windows 10','在线','2018-09-16 08:40:24','2018-09-16 08:40:27',1800000),('a623ed58-fbc8-4f44-83ce-cda902a56bf6','zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10','在线','2018-09-28 14:11:25','2018-09-28 14:11:29',1800000),('b48b2072-de34-4d23-a64b-0602ccd6b006','zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10','在线','2018-09-26 14:27:01','2018-09-26 14:27:08',1800000),('d00aa602-e792-46ba-8cde-629401cf5864','zwt','127.0.0.1','XX 内网IP','Chrome','Windows 10','在线','2018-09-28 14:09:16','2018-09-28 14:09:18',1800000),('fb9ef657-e7c3-4e84-beb6-cff44c3bcb92','zwt','127.0.0.1','','Chrome','Windows 10','在线','2018-09-28 14:05:12','2018-09-28 14:05:14',1800000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
