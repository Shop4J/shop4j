/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.7.21-log : Database - db_order
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_order` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_order`;

/*Table structure for table `tbl_order` */

DROP TABLE IF EXISTS `tbl_order`;

CREATE TABLE `tbl_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `buyer_id` bigint(20) DEFAULT NULL COMMENT '买家ID',
  `price_total` decimal(10,0) DEFAULT NULL COMMENT '订单总价',
  `mobile` varchar(30) DEFAULT NULL COMMENT '预留手机号',
  `addr` varchar(200) DEFAULT NULL COMMENT '预留地址',
  `status` tinyint(3) DEFAULT NULL COMMENT '订单状态，1待付款，2待发货，3待收货，4完成，5已关闭，6已退款',
  `add_operator` bigint(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_operator` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL COMMENT '预留姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_order` */

/*Table structure for table `tbl_order_detail` */

DROP TABLE IF EXISTS `tbl_order_detail`;

CREATE TABLE `tbl_order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sku_id` bigint(20) DEFAULT NULL COMMENT '产品编号',
  `price_total` decimal(10,0) DEFAULT NULL COMMENT '总价',
  `num` int(12) DEFAULT NULL COMMENT '销售数量',
  `status` tinyint(3) DEFAULT NULL COMMENT '1有效，2无效（退款会导致这个）',
  `spu_id` bigint(20) DEFAULT NULL COMMENT '冗余',
  `add_time` datetime DEFAULT NULL,
  `add_operator` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_operator` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单编号',
  `unit_price` decimal(10,0) DEFAULT NULL COMMENT '商品单价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_order_detail` */

insert  into `tbl_order_detail`(`id`,`sku_id`,`price_total`,`num`,`status`,`spu_id`,`add_time`,`add_operator`,`update_time`,`update_operator`,`order_id`,`unit_price`) values 
(1,30,500,2,1,75016,'2018-05-03 13:42:01',1,NULL,0,13,250);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
