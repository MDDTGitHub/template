/*
 Navicat MySQL Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 17/07/2020 19:02:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb
-- ----------------------------
DROP TABLE IF EXISTS `tb`;
CREATE TABLE `tb`  (
  `姓名` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `课程` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `分数` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb
-- ----------------------------
INSERT INTO `tb` VALUES ('张三', '语文', 74);
INSERT INTO `tb` VALUES ('张三', '数学', 83);
INSERT INTO `tb` VALUES ('张三', '物理', 93);
INSERT INTO `tb` VALUES ('李四', '语文', 74);
INSERT INTO `tb` VALUES ('李四', '数学', 84);
INSERT INTO `tb` VALUES ('李四', '物理', 94);

-- ----------------------------
-- Table structure for test_permission
-- ----------------------------
DROP TABLE IF EXISTS `test_permission`;
CREATE TABLE `test_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_permission
-- ----------------------------
INSERT INTO `test_permission` VALUES (1, 'admin:*', '管理员权限');
INSERT INTO `test_permission` VALUES (2, 'user:*', '用户权限');
INSERT INTO `test_permission` VALUES (3, 'test:*', '测试权限');

-- ----------------------------
-- Table structure for test_role
-- ----------------------------
DROP TABLE IF EXISTS `test_role`;
CREATE TABLE `test_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_role
-- ----------------------------
INSERT INTO `test_role` VALUES (1, 'admin', '管理员');
INSERT INTO `test_role` VALUES (2, 'user', '普通用户');
INSERT INTO `test_role` VALUES (3, 'test', '测试用户');

-- ----------------------------
-- Table structure for test_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `test_role_permission`;
CREATE TABLE `test_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `permission_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_role_permission
-- ----------------------------
INSERT INTO `test_role_permission` VALUES (1, 1, 1);
INSERT INTO `test_role_permission` VALUES (2, 1, 2);
INSERT INTO `test_role_permission` VALUES (3, 2, 2);
INSERT INTO `test_role_permission` VALUES (4, 3, 3);

-- ----------------------------
-- Table structure for test_user
-- ----------------------------
DROP TABLE IF EXISTS `test_user`;
CREATE TABLE `test_user`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '删除状态',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `updated` datetime(0) NULL DEFAULT NULL COMMENT '修改日期',
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_user
-- ----------------------------
INSERT INTO `test_user` VALUES (1, 'admin', 'C52C26CBC27A2AAC1B8D95FD72B93168', '超级管理员', '12345678910', 0, '123456789@111.com', '2020-04-26 10:17:40', '2020-04-26 10:17:44', '我是一个超级管理员，巴拉巴拉巴拉。。。', 'http://localhost:8082/image/head/yzbzz_yh.gif');
INSERT INTO `test_user` VALUES (2, 'user', 'C52C26CBC27A2AAC1B8D95FD72B93168', '用户', '12345678910', 0, '123456789@111.com', '2020-06-03 16:53:38', '2020-06-03 16:53:40', NULL, NULL);
INSERT INTO `test_user` VALUES (3, 'test', 'C52C26CBC27A2AAC1B8D95FD72B93168', '测试', '12345678910', 0, '123456789@111.com', '2020-06-03 16:54:06', '2020-06-03 16:54:10', NULL, NULL);

-- ----------------------------
-- Table structure for test_user_role
-- ----------------------------
DROP TABLE IF EXISTS `test_user_role`;
CREATE TABLE `test_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_user_role
-- ----------------------------
INSERT INTO `test_user_role` VALUES (1, 1, 1);
INSERT INTO `test_user_role` VALUES (2, 2, 2);
INSERT INTO `test_user_role` VALUES (3, 3, 3);

SET FOREIGN_KEY_CHECKS = 1;
