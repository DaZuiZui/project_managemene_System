/*
 Navicat Premium Data Transfer

 Source Server         : 101.35.95.141
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : 101.35.95.141:3306
 Source Schema         : project1

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 29/11/2021 10:12:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phoneNumber` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (11, 'y51288033', 'wasd123', 'y5128803', 'wasd@qq.com', '123', 'user');
INSERT INTO `user` VALUES (12, 'root', 'admin', 'wasd', 'root', '10086', 'admin');
INSERT INTO `user` VALUES (13, '51288033@outlook.com', 'wasd123', 'demo', '51288033@outlook.com', '1111111', 'user');
INSERT INTO `user` VALUES (14, '51288033@qq.com', 'wasd123..WASD^', 'TEST1', '51288033@qq.com', '1008611', 'user');
INSERT INTO `user` VALUES (15, '10086@qq.com', 'WASD123..wasd^', 'y5128803', '10086@qq.com', '100861199', 'user');
INSERT INTO `user` VALUES (16, '220951@qq.com', 'wasd123wasd', 'test1', '220951@qq.com', '220951', 'user');

SET FOREIGN_KEY_CHECKS = 1;
