/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.7.21-log : Database - db_user
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_user` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_user`;

/*Table structure for table `tbl_user` */

DROP TABLE IF EXISTS `tbl_user`;

CREATE TABLE `tbl_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ticket` varchar(200) DEFAULT NULL COMMENT '账号',
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `status` tinyint(3) DEFAULT NULL COMMENT '状态1启用 2关闭',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_operator` bigint(20) DEFAULT NULL COMMENT '更新人',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机号',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `add_operator` bigint(20) DEFAULT NULL COMMENT '添加人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_user` */

insert  into `tbl_user`(`id`,`ticket`,`name`,`password`,`add_time`,`status`,`update_time`,`update_operator`,`mobile`,`email`,`add_operator`) values 
(1,'admin','系统','a15945435723','2018-04-20 18:38:35',1,NULL,NULL,'15101001253','893351947@qq.com',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
