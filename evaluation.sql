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

 Date: 29/11/2021 10:12:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for evaluation
-- ----------------------------
DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE `evaluation`  (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `evaluationtext` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `imaurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of evaluation
-- ----------------------------
INSERT INTO `evaluation` VALUES (1, '啊实打实的', 'y51288033@outlook.com', NULL);
INSERT INTO `evaluation` VALUES (2, 'asdsadasdas', '51288033@outlook.com', '222.png');
INSERT INTO `evaluation` VALUES (3, 'TEST111122334556', '51288033@qq.com', '1999.png');
INSERT INTO `evaluation` VALUES (4, '1112233', '220951@qq.com', '222222222.png');
INSERT INTO `evaluation` VALUES (5, '111121132123', '220951@qq.com', NULL);

SET FOREIGN_KEY_CHECKS = 1;
