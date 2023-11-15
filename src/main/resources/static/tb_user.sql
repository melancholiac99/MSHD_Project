/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`my_code_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `my_code_db`;
DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
                                `user_id` INT NOT NULL AUTO_INCREMENT COMMENT '用户id',
                                `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
                                `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
                                `status` varchar(50) NOT NULL DEFAULT '' COMMENT '状态',
                                `regist_time` varchar(200) NOT NULL DEFAULT '' COMMENT '注册时间',
                                PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;