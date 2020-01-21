/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : activiti

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 21/01/2020 11:45:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for biz_audit
-- ----------------------------
DROP TABLE IF EXISTS `biz_audit`;
CREATE TABLE `biz_audit`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务编号',
  `result` tinyint(3) UNSIGNED NOT NULL COMMENT '审核结果 2通过 3驳回',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核意见',
  `proc_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程名称',
  `proc_def_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '路由 流程定义key',
  `applyer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '申请人',
  `auditor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批人',
  `auditor_id` bigint(20) UNSIGNED NOT NULL COMMENT '审批人编号',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `del_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 122 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '业务审核记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for biz_business
-- ----------------------------
DROP TABLE IF EXISTS `biz_business`;
CREATE TABLE `biz_business`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `proc_def_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程定义编号',
  `proc_def_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程定义key 路由标记',
  `proc_inst_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程实例编号',
  `proc_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程名称',
  `current_task` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前任务节点名称',
  `result` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '结果状态  1处理中 2通过 3驳回',
  `status` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态 1处理中 2结束',
  `table_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联表id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请标题',
  `user_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '创建用户id',
  `applyer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人',
  `apply_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '申请时间',
  `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 121 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for biz_leave
-- ----------------------------
DROP TABLE IF EXISTS `biz_leave`;
CREATE TABLE `biz_leave`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `start_date` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `duration` double(11, 2) NULL DEFAULT NULL COMMENT '时长(小时)',
  `type` tinyint(3) NULL DEFAULT NULL COMMENT '请假类型',
  `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '删除标记',
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '请假' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for biz_node
-- ----------------------------
DROP TABLE IF EXISTS `biz_node`;
CREATE TABLE `biz_node`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `node_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点ID',
  `type` tinyint(3) UNSIGNED NOT NULL COMMENT '类型 1：角色 2：部门负责人 3：用户 4：所属部门负责人',
  `auditor` bigint(20) UNSIGNED NULL DEFAULT 0 COMMENT '类型对应负责人的值',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_node_id`(`node_id`) USING BTREE COMMENT '节点id索引'
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for biz_purchase
-- ----------------------------
DROP TABLE IF EXISTS `biz_purchase`;
CREATE TABLE `biz_purchase`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `money` decimal(11, 2) UNSIGNED NOT NULL COMMENT '金额',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `del_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '报销' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
