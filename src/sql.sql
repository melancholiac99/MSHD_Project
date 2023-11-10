/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`my_code_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `my_code_db`;
DROP TABLE IF EXISTS `tb_code_show`;

CREATE TABLE `tb_code_show` (
  `code_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键代码id',
  `province` varchar(50) NOT NULL DEFAULT '' COMMENT '省级行政区域',
  `PL_city` varchar(50) NOT NULL DEFAULT '' COMMENT '地级行政区域',
  `district` varchar(50) NOT NULL DEFAULT '' COMMENT '区级',
  `town` varchar(200) NOT NULL DEFAULT '' COMMENT '乡镇级',
  `community` varchar(200) NOT NULL DEFAULT '' COMMENT '社区街道',
  `username` varchar(100) NOT NULL DEFAULT '' COMMENT '用户名',
  `source` varchar(50) NOT NULL DEFAULT '' COMMENT '数据来源',
  `supporter` varchar(50) NOT NULL DEFAULT '' '载体',
  `disaster_info` varchar(200) NOT NULL DEFAULT '' '灾情信息',
  `is_file` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否有文件 0-没有 1-有',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  `code_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否审核通过 0-未审核 1-审核通过',
  PRIMARY KEY (`code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_blog_comment` */

insert  into `tb_code_show`(`code_id`,`province`,`PL_city`,`district`,`town`,`community`,`username`,`source`,`supporter`,`disaster_inftb_code_showo`,`is_deleted`,`is_file`,`code_status`) values (10011001,'北京市','市辖区','东城区','东华门街道','多福巷社区居委会','Zhou','业务报送数据 - 前方地震应急指挥部','文字','震情-震情信息-地理位置',0,0,1);
