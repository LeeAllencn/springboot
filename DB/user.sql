/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50546
Source Host           : localhost:3306
Source Database       : db_springboot

Target Server Type    : MYSQL
Target Server Version : 50546
File Encoding         : 65001

Date: 2021-03-04 19:19:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `sex` tinyint(4) NOT NULL COMMENT '性别：0-女；1-男',
  `telephone` varchar(11) NOT NULL COMMENT '电话号码',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除：0-未删除；1-已删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Bob', '123456', '1', '12345678900', '0', '2021-01-30 16:13:45', '2021-01-30 16:13:42');
INSERT INTO `user` VALUES ('2', 'Amy', '123789', '0', '12378914566', '0', '2021-01-30 16:15:09', '2021-01-30 16:15:14');
INSERT INTO `user` VALUES ('3', 'Sam', '112233', '1', '12312312345', '1', '2021-01-30 16:15:45', '2021-01-30 16:15:48');
