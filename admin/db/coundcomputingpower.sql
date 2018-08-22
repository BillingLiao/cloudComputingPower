/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.17-log : Database - cloudcomputingpower
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cloudcomputingpower` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cloudcomputingpower`;

/*Table structure for table `t_bank` */

DROP TABLE IF EXISTS `t_bank`;

CREATE TABLE `t_bank` (
  `bank_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户银行编号',
  `user_id` INT(11) NOT NULL COMMENT '用户编号',
  `bank_type` VARCHAR(50) NOT NULL COMMENT '银行类型',
  `bank_name` VARCHAR(50) NOT NULL COMMENT '银行名称',
  `opening_bank` VARCHAR(50) NOT NULL COMMENT '开户行',
  `card_number` VARCHAR(50) NOT NULL COMMENT '银行卡号',
  `true_name` VARCHAR(20) NOT NULL COMMENT '姓名',
  `create_at` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_at` DATETIME DEFAULT NULL COMMENT '更新时间',
  `create_user` INT(11) DEFAULT NULL COMMENT '创建者',
  `update_user` INT(11) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`bank_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='银行表';

/*Data for the table `t_bank` */

/*Table structure for table `t_category` */

DROP TABLE IF EXISTS `t_category`;

CREATE TABLE `t_category` (
  `category_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '产品类别编号',
  `category_name` VARCHAR(50) NOT NULL COMMENT '产品类别名称',
  `category_code` VARCHAR(50) NOT NULL COMMENT '产品编码',
  PRIMARY KEY (`category_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='产品类别表';

/*Data for the table `t_category` */

INSERT INTO `t_category` VALUES ('1', '矿机产品', 'minerProduct');
INSERT INTO `t_category` VALUES ('2', '云算力产品', 'cloudProduct');
INSERT INTO `t_category` VALUES ('3', '理财产品', 'financialProduct');

/*Table structure for table `t_income` */

DROP TABLE IF EXISTS `t_income`;

CREATE TABLE `t_income` (
  `income_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '收益编号',
  `order_id` INT(11) DEFAULT NULL COMMENT '订单编号',
  `income_type` VARCHAR(50) NOT NULL COMMENT '收益类别',
  `daily_income` DECIMAL(15,3) NOT NULL COMMENT '每日收益值',
  `total_income` DECIMAL(18,3) NOT NULL COMMENT '总收益值',
  `unit` VARCHAR(20) NOT NULL COMMENT '收益单位',
  PRIMARY KEY (`income_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='产品收益表';

/*Data for the table `t_income` */

/*Table structure for table `t_jurisdiction` */

DROP TABLE IF EXISTS `t_jurisdiction`;

CREATE TABLE `t_jurisdiction` (
  `jurisdiction_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `jurisdiction_name` VARCHAR(20) NOT NULL COMMENT '权限名称',
  `url` VARCHAR(50) NOT NULL COMMENT '权限url',
  `level` INT(11) NOT NULL COMMENT '权限等级',
  `icon` VARCHAR(50) NOT NULL COMMENT '权限图标',
  `parent_id` INT(11) DEFAULT NULL COMMENT '父级id',
  `is_open` SMALLINT(6) NOT NULL COMMENT '是否开启',
  PRIMARY KEY (`jurisdiction_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

/*Data for the table `t_jurisdiction` */

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `menu_id` INT(11) NOT NULL AUTO_INCREMENT,
  `parent_id` INT(11) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` VARCHAR(50) DEFAULT NULL COMMENT '菜单名称',
  `url` VARCHAR(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` VARCHAR(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` INT(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` VARCHAR(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` INT(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '0', '系统管理', NULL, NULL, '0', 'fa fa-cog', '0');
INSERT INTO `t_menu` VALUES ('2', '1', '管理员管理', 'modules/sys/user.html', NULL, '1', 'fa fa-user', '2');
INSERT INTO `t_menu` VALUES ('3', '1', '角色管理', 'modules/sys/role.html', NULL, '1', 'fa fa-user-secret', '2');
INSERT INTO `t_menu` VALUES ('4', '1', '菜单管理', 'modules/sys/menu.html', NULL, '1', 'fa fa-th-list', '3');
INSERT INTO `t_menu` VALUES ('5', '0', '产品管理', NULL, NULL, '0', 'fa fa-shopping-basket', '0');
INSERT INTO `t_menu` VALUES ('6', '5', '矿机产品管理', 'modules/good/good.html', NULL, '1', NULL, '1');
INSERT INTO `t_menu` VALUES ('7', '5', '云算力产品管理', 'modules/good/good.html', NULL, '1', NULL, '2');
INSERT INTO `t_menu` VALUES ('8', '5', '理财产品管理', 'modules/product/financialProduct.html', NULL, '1', NULL, '3');
INSERT INTO `t_menu` VALUES ('10', '8', '商品规格', 'modules/good/categoryspec.html', 'good:categoryspec:list,good:categoryspec:info,good:categoryspec:save,good:categoryspec:update,good:categoryspec:delete', '1', NULL, '0');
INSERT INTO `t_menu` VALUES ('11', '0', '订单管理', NULL, NULL, '0', 'fa fa-bars', '3');
INSERT INTO `t_menu` VALUES ('12', '11', '订单列表', 'modules/order/orderList.html', NULL, '1', NULL, '0');

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `order_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `order_no` VARCHAR(50) NOT NULL COMMENT '订单单号',
  `product_id` INT(11) NOT NULL COMMENT '产品编号',
  `user_id` INT(11) DEFAULT NULL COMMENT '用户编号',
  `type` VARCHAR(50) NOT NULL COMMENT '申购类型',
  `order_status` VARCHAR(50) NOT NULL COMMENT '订单状态',
  `amount` DECIMAL(15,3) DEFAULT NULL COMMENT '购买金额',
  `memo` VARCHAR(200) DEFAULT NULL COMMENT '备注',
  `create_user` INT(11) DEFAULT NULL COMMENT '创建者',
  `caeate_at` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_user` INT(11) DEFAULT NULL COMMENT '更新者',
  `update_at` DATETIME DEFAULT NULL COMMENT '更新时间',
  `del_flag` INT(11) DEFAULT '0' COMMENT '活动状态：0-未删除,1-已删除',
  PRIMARY KEY (`order_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

/*Data for the table `t_order` */

/*Table structure for table `t_product` */

DROP TABLE IF EXISTS `t_product`;

CREATE TABLE `t_product` (
  `product_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '产品编号',
  `category_id` INT(11) NOT NULL COMMENT '产品类别编号',
  `product_name` VARCHAR(100) NOT NULL COMMENT '产品名称',
  `introduction` TEXT DEFAULT NULL COMMENT '产品介绍',
  `show_in_shelve` TINYINT(1) DEFAULT '0' COMMENT '是否上架：1=上架/0=下架',
  `create_user` INT(11) DEFAULT NULL COMMENT '创建者',
  `create_at` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_user` INT(11) DEFAULT NULL COMMENT '更新者',
  `update_at` DATETIME DEFAULT NULL COMMENT '更新时间',
  `del_flag` INT(11) DEFAULT '0' COMMENT '活动状态：0-未删除,1-已删除',
  PRIMARY KEY (`product_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='产品表';

/*Data for the table `t_product` */

INSERT INTO `t_product` VALUES ('1', '3', '理财产品1号', '这是理财,dfkjkj1dfdfad1', '1', NULL,'2018-06-15 14:09:31',NULL, NULL,'0');
INSERT INTO `t_product` VALUES ('2', '3', '理财产品2号', '这当时的理财啊,dfkjkj1dfdfad1', '1', NULL,'2018-08-15 14:09:31',NULL, NULL,'0');
INSERT INTO `t_product` VALUES ('3', '1', '矿机产品1号', '1. 扫描玛雅矿机官方微信号二维码可获取最新资讯消息。2. 本批矿机价格已包含电源价格。3. 款项支付时间：确认订单60分钟内支付。4. 预期发货时间：2018年6月，物流运费到付。5. 已付货款项将不予退还，矿机无法出货情况除外。6. 本批次矿机最小订单量为1台，仅支持发货不支持托管。7. 玛雅保留此次预售的最终解释权。', '1', NULL,'2018-06-12 14:09:31',NULL, NULL,'0');
INSERT INTO `t_product` VALUES ('4', '1', '矿机产品2号', '这款打造的,dfkjkj1dfdfad1', '1', NULL,'2018-08-15 14:09:31',NULL, NULL,'0');
INSERT INTO `t_product` VALUES ('5', '2', '云算力产品1号', '这优惠,dfkjkj1dfdfad1', '1', NULL,'2018-06-11 14:09:31',NULL, NULL,'0');
INSERT INTO `t_product` VALUES ('6', '2', '云算力产品2号', '这价格,dfkjkj1dfdfad1', '1', NULL,'2018-08-11 14:09:31',NULL, NULL,'0');

/*Table structure for table `t_product_cloud` */

DROP TABLE IF EXISTS t_cloud_product;

CREATE TABLE t_cloud_product
(
   cloud_id             INT(11) NOT NULL AUTO_INCREMENT COMMENT '云算力明细id',
   product_id           INT(11) NOT NULL COMMENT '产品id',
   total_stock          DECIMAL(13,2) NOT NULL COMMENT '本期总算力',
   stock                DECIMAL(13,2) NOT NULL COMMENT '剩余算力',
   electricity_fees     DECIMAL(15,3) NOT NULL COMMENT '电费',
   price                DECIMAL(15,3) NOT NULL COMMENT '单价',
   sale_time            VARCHAR(50) NOT NULL COMMENT '本期售卖时间',
   model                VARCHAR(50) NOT NULL COMMENT '机型',
   rated                VARCHAR(50) NOT NULL COMMENT '额定算力',
   remark               VARCHAR(200) NOT NULL COMMENT '参考收益',
   start_step           INT COMMENT '起投单位',
   `power`              VARCHAR(50) COMMENT '电源',
   management_expense   DECIMAL(15,3) COMMENT '管理费',
   `del_flag` INT(11) DEFAULT '0' COMMENT '活动状态：0-未删除,1-已删除',
   PRIMARY KEY (cloud_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='云算力明细表';

/*Data for the table `t_cloud_product` */

INSERT INTO `t_cloud_product` VALUES ('1', '5', '1000', '300', '0.4', '570', '9月20号-10月10号', 'A9', '14T','很多','1','test',NULL,'0');
INSERT INTO `t_cloud_product` VALUES ('2', '6', '1200', '500', '0.35', '630', '9月10号-10月1号', '蚂蚁M9', '13.5T','很多','1','test',NULL, '0');

/*Table structure for table `t_financial_product` */

DROP TABLE IF EXISTS t_financial_product;

CREATE TABLE t_financial_product
(
   financial_id         INT(11) NOT NULL AUTO_INCREMENT COMMENT '理财明细id',
   product_id       	INT(11) NOT NULL COMMENT '产品id',
   threshold_amount     DECIMAL(15,3) NOT NULL COMMENT '起投金额',
   step_term            DECIMAL(15,3) NOT NULL COMMENT '投资步长',
   lock_amount          INT NOT NULL COMMENT '锁定期',
   reward_rate          DECIMAL(15,3) NOT NULL COMMENT '年化收益率',
   sales_status         INT(11) DEFAULT '0' COMMENT '状态,0:审核中,1:销售中,2:暂停销售,3:已结束',
   `del_flag` INT(11) DEFAULT '0' COMMENT '活动状态：0-未删除,1-已删除',
   PRIMARY KEY (financial_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='理财明细表';

/*Data for the table `t_financial_product */

INSERT INTO `t_financial_product` VALUES ('1', '1', '10000.0', '1000', '0', '4.2', '0', '0');
INSERT INTO `t_financial_product` VALUES ('2', '2', '20000.0', '2000', '0', '4.3', '0', '0');

/*Table structure for table `t_miner_product` */

DROP TABLE IF EXISTS t_miner_product;

CREATE TABLE t_miner_product
(
   miner_id         	INT(11) NOT NULL AUTO_INCREMENT COMMENT '矿机明细id',
   product_id     	INT(11) NOT NULL COMMENT '产品id',
   price                DECIMAL(15,3) NOT NULL COMMENT '单价',
   stock 		DECIMAL(13,2) NOT NULL COMMENT '额定算力',
   model                VARCHAR(50) NOT NULL COMMENT '机型',
   remark               VARCHAR(200) DEFAULT NULL COMMENT '参考收益',
   `power`              VARCHAR(50) DEFAULT NULL COMMENT '电源',
   electricity_fees     DECIMAL(15,3) DEFAULT NULL COMMENT '电费',
   management_expense   DECIMAL(15,3) DEFAULT NULL COMMENT '管理费',
   `del_flag` INT(11) DEFAULT '0' COMMENT '活动状态：0-未删除,1-已删除',
   PRIMARY KEY (miner_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='矿机明细表';

/*Data for the table `t_miner_product */
INSERT INTO `t_miner_product` VALUES ('1', '3', '12000.0', '13.5', '蚂蚁M9', '这样哪有', NULL, NULL, NULL, '0');
INSERT INTO `t_miner_product` VALUES ('2', '4', '23000.0', '14', 'A9', '很火', NULL, NULL, NULL, '0');

/*Table structure for table `t_proxy` */

DROP TABLE IF EXISTS `t_proxy`;

CREATE TABLE `t_proxy` (
  `proxy_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '代理编号',
  `user_id` INT(11) DEFAULT NULL COMMENT '代理人编号',
  `subordinate_id` INT(11) DEFAULT NULL COMMENT '被代理人编号',
  `level` INT(11) DEFAULT NULL COMMENT '代理级别',
  PRIMARY KEY (`proxy_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='代理表';

/*Data for the table `t_proxy` */

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `role_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` VARCHAR(50) NOT NULL COMMENT '角色名称',
  `remark` VARCHAR(100) DEFAULT NULL COMMENT '备注',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

/*Data for the table `t_role` */

INSERT  INTO `t_role`(`role_id`,`role_name`,`remark`,`create_time`) VALUES (1,'用户',NULL,'2018-06-07 16:29:40'),(2,'数据权限测试','22','2018-07-10 17:49:54'),(3,'数据权限测试','22','2018-07-10 17:50:13'),(4,'admin','admin','2018-07-11 21:55:58');

/*Table structure for table `t_role_func` */

DROP TABLE IF EXISTS `t_role_func`;

CREATE TABLE `t_role_func` (
  `role_id` INT(11) DEFAULT NULL COMMENT '角色id',
  `jurisdiction_id` INT(11) DEFAULT NULL COMMENT '权限id'
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

/*Data for the table `t_role_func` */

/*Table structure for table `t_role_menu` */

DROP TABLE IF EXISTS `t_role_menu`;

CREATE TABLE `t_role_menu` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role_id` INT(11) DEFAULT NULL COMMENT '角色ID',
  `menu_id` INT(11) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

/*Data for the table `t_role_menu` */

INSERT  INTO `t_role_menu`(`id`,`role_id`,`menu_id`) VALUES (1,4,1),(2,4,2),(3,4,3),(4,4,4);

/*Table structure for table `t_totices` */

DROP TABLE IF EXISTS `t_totices`;

CREATE TABLE `t_totices` (
  `totices_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `user_id` INT(11) NOT NULL COMMENT '用户编号',
  `title` VARCHAR(40) NOT NULL COMMENT '标题',
  `content` TEXT NOT NULL COMMENT '内容',
  `publish_date` DATETIME DEFAULT NULL COMMENT '发布日期',
  `update_date` DATETIME DEFAULT NULL COMMENT '修改日期',
  `publish_user` INT(11) DEFAULT NULL COMMENT '发布人',
  `update_user` INT(11) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`totices_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='滚动公告表';

/*Data for the table `t_totices` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_name` INT(11) NOT NULL COMMENT '用户名',
  `password` VARCHAR(50) NOT NULL COMMENT '登陆密码',
  `telphone` VARCHAR(20) DEFAULT NULL COMMENT '手机号码',
  `btc_addr` VARCHAR(50) DEFAULT NULL COMMENT 'BTC地址',
  `Alipay` VARCHAR(50) DEFAULT NULL COMMENT '支付宝账号',
  `register_time` DATE DEFAULT NULL COMMENT '注册时间',
  `update_time` DATE DEFAULT NULL COMMENT '修改时间',
  `invitation_code` VARCHAR(100) DEFAULT NULL COMMENT '邀请码',
  `status` TINYINT(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*Data for the table `t_user` */

INSERT  INTO `t_user`(`user_id`,`user_name`,`password`,`telphone`,`btc_addr`,`Alipay`,`register_time`,`update_time`,`invitation_code`,`status`) VALUES (1,'32454512','123456','15012015410','root@cnadmart.com','136000001','2016-11-11','2016-11-11','134df',1);

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role_id` INT(11) DEFAULT NULL COMMENT '角色id',
  `user_id` INT(11) DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

/*Data for the table `t_user_role` */

INSERT  INTO `t_user_role`(`id`,`role_id`,`user_id`) VALUES (1,4,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
