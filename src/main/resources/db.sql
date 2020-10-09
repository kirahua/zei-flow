/*
 Navicat Premium Data Transfer

 Source Server         : 110.86.16.170test
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 110.86.16.170:6606
 Source Schema         : jms_uat

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 09/10/2020 13:41:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for flow_audit
-- ----------------------------
DROP TABLE IF EXISTS `flow_audit`;
CREATE TABLE `flow_audit`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `flow_code`   varchar(50) COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '流程编号',
    `flow_key`    varchar(20) COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '流程key',
    `flow_name`   varchar(50) COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '流程名称',
    `instance_id` varchar(50) COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '流程实例id',
    `service_id`  bigint(20)                               DEFAULT NULL COMMENT '业务id',
    `variables`   varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '路线参数',
    `remark`      varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '摘要',
    `flow_state`  tinyint(4)                               DEFAULT '0' COMMENT '流程状态（0:审批中 1:审批通过 2:审批未通过）',
    `create_user` bigint(10)                               DEFAULT NULL COMMENT '创建人id',
    `create_time` datetime                                 DEFAULT NULL COMMENT '创建时间',
    `update_user` bigint(10)                               DEFAULT NULL COMMENT '更新人id',
    `update_time` datetime                                 DEFAULT NULL COMMENT '更新时间',
    `version`     int(10)                                  DEFAULT '0' COMMENT '版本号',
    `is_delete`   tinyint(10)                              DEFAULT '0' COMMENT '是否删除(0:否 1：是)',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 551
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='审批表';

-- ----------------------------
-- Table structure for flow_audit_detail
-- ----------------------------
DROP TABLE IF EXISTS `flow_audit_detail`;
CREATE TABLE `flow_audit_detail`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `task_id`           varchar(50) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '任务实例id',
    `instance_id`       varchar(50) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '流程实例id',
    `current_role`      varchar(20) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '当前待审批角色',
    `next_role`         varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '下一节点待审批角色',
    `next_user`         varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '下一节点待审批人',
    `current_task_key`  varchar(50) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '当前节点key',
    `current_task_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '当前节点名',
    `next_task_key`     varchar(50) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '下一节点key',
    `next_task_name`    varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '下一节点名',
    `audit_user_id`     bigint(20)                              DEFAULT NULL COMMENT '审批人id',
    `audit_time`        datetime                                DEFAULT NULL COMMENT '审批时间',
    `audit_state`       tinyint(4)                              DEFAULT '0' COMMENT '审批状态0：待审批 1：审批通过 2：审批不通过',
    `create_user`       bigint(10)                              DEFAULT NULL COMMENT '创建人id',
    `create_time`       datetime                                DEFAULT NULL COMMENT '创建时间',
    `update_user`       bigint(10)                              DEFAULT NULL COMMENT '更新人id',
    `update_time`       datetime                                DEFAULT NULL COMMENT '更新时间',
    `version`           int(10)                                 DEFAULT '0' COMMENT '版本号',
    `is_delete`         tinyint(10)                             DEFAULT '0' COMMENT '是否删除(0:否 1：是)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1375
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='流程节点表';

-- ----------------------------
-- Table structure for flow_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `flow_evaluation`;
CREATE TABLE `flow_evaluation`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `instance_id`       varchar(50) COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '流程实例id',
    `task_id`           varchar(50) COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '节点实例id',
    `evaluate_user_id`  bigint(10)                               DEFAULT NULL COMMENT '评论人id',
    `evaluate_time`     datetime                                 DEFAULT NULL COMMENT '评论时间',
    `evaluate_content`  varchar(2000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评论内容',
    `evaluate_file_url` varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '评论文件url',
    `evaluate_type`     tinyint(4)                               DEFAULT '1' COMMENT '是否节点评论（1：是 2：否）',
    `create_user`       bigint(10)                               DEFAULT NULL COMMENT '创建人id',
    `create_time`       datetime                                 DEFAULT NULL COMMENT '创建时间',
    `update_user`       bigint(10)                               DEFAULT NULL COMMENT '更新人id',
    `update_time`       datetime                                 DEFAULT NULL COMMENT '更新时间',
    `version`           int(10)                                  DEFAULT '0' COMMENT '版本号',
    `is_delete`         tinyint(10)                              DEFAULT '0' COMMENT '是否删除(0:否 1：是)',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1306
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='流程评论表';

-- ----------------------------
-- Table structure for flow_instance
-- ----------------------------
DROP TABLE IF EXISTS `flow_instance`;
CREATE TABLE `flow_instance`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `flow_key`       varchar(20)  DEFAULT NULL COMMENT '流程key',
    `task_key`       varchar(20)  DEFAULT NULL COMMENT '节点key',
    `task_name`      varchar(50)  DEFAULT NULL COMMENT '节点名称',
    `task_roles`     varchar(100) DEFAULT NULL COMMENT '可执行角色',
    `task_condition` varchar(100) DEFAULT NULL COMMENT '条件参数',
    `next_task_key`  varchar(20)  DEFAULT NULL COMMENT '下一节点key',
    `do_url`         varchar(200) DEFAULT NULL COMMENT '特殊执行url',
    `create_user`    bigint(20)   DEFAULT NULL COMMENT '创建人',
    `create_time`    datetime     DEFAULT NULL COMMENT '创建时间',
    `update_user`    bigint(20)   DEFAULT NULL COMMENT '更新人',
    `update_time`    datetime     DEFAULT NULL COMMENT '更新时间',
    `version`        int(11)      DEFAULT NULL COMMENT '版本号',
    `is_delete`      tinyint(4)   DEFAULT '0' COMMENT '是否移除（0：否 -1：是）',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 72
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for flow_model
-- ----------------------------
DROP TABLE IF EXISTS `flow_model`;
CREATE TABLE `flow_model`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT,
    `flow_key`      varchar(20) DEFAULT NULL COMMENT '流程key',
    `flow_name`     varchar(50) DEFAULT NULL COMMENT '流程名称',
    `auto_audit`    tinyint(4)  DEFAULT '0' COMMENT '相同角色是否自动审批（0：否 1：是）',
    `create_user`   bigint(20)  DEFAULT NULL COMMENT '创建人',
    `create_time`   datetime    DEFAULT NULL COMMENT '创建时间',
    `update_user`   bigint(20)  DEFAULT NULL COMMENT '更新人',
    `update_time`   datetime    DEFAULT NULL COMMENT '更新时间',
    `version`       int(11)     DEFAULT NULL COMMENT '版本号',
    `is_delete`     tinyint(4)  DEFAULT '0' COMMENT '是否移除（0：否 -1：是）',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 16
  DEFAULT CHARSET = utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
