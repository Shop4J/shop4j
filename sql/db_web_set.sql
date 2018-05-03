/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.7.21-log : Database - db_web_set
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_web_set` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_web_set`;

/*Table structure for table `tbl_web_info` */

DROP TABLE IF EXISTS `tbl_web_info`;

CREATE TABLE `tbl_web_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '站点名称',
  `type` tinyint(3) DEFAULT NULL COMMENT '1主站，2站点管理站',
  `logo_url` varchar(300) DEFAULT NULL COMMENT 'logo地址',
  `key_world` varchar(300) DEFAULT NULL COMMENT '关键词',
  `status` tinyint(3) DEFAULT NULL COMMENT '数据状态',
  `add_operator` bigint(20) DEFAULT NULL COMMENT '添加人',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_operator` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `ico_url` varchar(300) DEFAULT NULL COMMENT '小图标地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `类型不重复` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_web_info` */

insert  into `tbl_web_info`(`id`,`name`,`type`,`logo_url`,`key_world`,`status`,`add_operator`,`add_time`,`update_operator`,`update_time`,`ico_url`) values 
(4,'小衣精商城',1,'/images/icons/weblogo.png','小衣精童装 小衣精官网 小衣精童装批发',NULL,NULL,NULL,NULL,NULL,'/static/favicon.ico');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
