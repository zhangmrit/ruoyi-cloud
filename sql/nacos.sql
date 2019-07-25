/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : nacos

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 25/07/2019 21:25:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (1, 'ruoyi-auth-dev.yml', 'DEFAULT_GROUP', 'spring:\r\n  redis:\r\n    database: 1\r\n    host: 192.168.0.185\r\n    port: 6379\r\n    password:      \r\n    timeout: 6000ms  \r\n    lettuce:\r\n      pool:\r\n        max-active: 1000  \r\n        max-wait: -1ms     \r\n        max-idle: 10     \r\n        min-idle: 5       ', '93655372645a71ad603259e7dba81a43', '2019-06-21 19:10:20', '2019-06-28 16:42:48', NULL, '127.0.0.1', '', '', '', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (3, 'ruoyi-gateway-dev.yml', 'DEFAULT_GROUP', 'spring:\r\n redis:\r\n   database: 1\r\n   host: 192.168.0.185\r\n   port: 6379\r\n   password:      # 密码（默认为空）\r\n   timeout: 6000ms  # 连接超时时长（毫秒）\r\n   lettuce:\r\n     pool:\r\n       max-active: 1000  # 连接池最大连接数（使用负值表示没有限制）\r\n       max-wait: -1ms      # 连接池最大阻塞等待时间（使用负值表示没有限制）\r\n       max-idle: 10      # 连接池中的最大空闲连接\r\n       min-idle: 5       # 连接池中的最小空闲连接', '01479c9861c53b798b95f3df59acc5ba', '2019-06-24 11:01:34', '2019-06-28 15:29:56', NULL, '127.0.0.1', '', '', '', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (4, 'ruoyi-gateway-prod.yml', 'DEFAULT_GROUP', 'spring: \r\n  redis:\r\n    database: 1\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password:      \r\n    timeout: 6000ms  \r\n    lettuce:\r\n      pool:\r\n        max-active: 1000  \r\n        max-wait: -1ms      \r\n        max-idle: 10      \r\n        min-idle: 5      ', 'ca1f5dd577be7b2fba38f0d03bd90ac8', '2019-06-24 11:04:26', '2019-06-24 11:18:08', NULL, '127.0.0.1', '', '', '', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (6, 'ruoyi-system-dev.yml', 'DEFAULT_GROUP', '# 数据源配置\r\nspring:\r\n  redis:\r\n    database: 1\r\n    host: 192.168.0.185\r\n    port: 6379\r\n    password:      # 密码（默认为空）\r\n    timeout: 6000ms  # 连接超时时长（毫秒）\r\n    lettuce:\r\n      pool:\r\n        max-active: 1000  # 连接池最大连接数（使用负值表示没有限制）\r\n        max-wait: -1ms      # 连接池最大阻塞等待时间（使用负值表示没有限制）\r\n        max-idle: 10      # 连接池中的最大空闲连接\r\n        min-idle: 5       # 连接池中的最小空闲连接\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driverClassName: com.mysql.cj.jdbc.Driver\r\n    druid:\r\n      # 主库数据源\r\n      master:\r\n        url: jdbc:mysql://localhost:3306/ry_cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n        username: root\r\n        password: root\r\n      # 从库数据源\r\n      slave:\r\n        #从数据源开关/默认关闭\r\n        enabled: false\r\n        url: jdbc:mysql://localhost:3306/ry_cloud_read?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n        username: root\r\n        password: root\r\n      # 初始连接数\r\n      initialSize: 5\r\n      # 最小连接池数量\r\n      minIdle: 10\r\n      # 最大连接池数量\r\n      maxActive: 20\r\n      # 配置获取连接等待超时的时间\r\n      maxWait: 60000\r\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      timeBetweenEvictionRunsMillis: 60000\r\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000\r\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\r\n      maxEvictableIdleTimeMillis: 900000\r\n      # 配置检测连接是否有效\r\n      validationQuery: SELECT 1 FROM DUAL\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n      webStatFilter:\r\n          enabled: true\r\n      statViewServlet:\r\n          enabled: true\r\n          # 设置白名单，不填则允许所有访问\r\n          allow:\r\n          url-pattern: /monitor/druid/*\r\n      filter:\r\n          stat:\r\n              enabled: true\r\n              # 慢SQL记录\r\n              log-slow-sql: true\r\n              slow-sql-millis: 1000\r\n              merge-sql: true\r\n          wall:\r\n              config:\r\n                  multi-statement-allow: true', 'b90c0295df7b34950ccae3f9005b98d6', '2019-06-24 11:28:38', '2019-07-25 21:25:32', NULL, '127.0.0.1', '', '', '', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (7, 'ruoyi-system-prod.yml', 'DEFAULT_GROUP', '# 数据源配置\r\nspring:\r\n  redis:\r\n    database: 1\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password:      # 密码（默认为空）\r\n    timeout: 6000ms  # 连接超时时长（毫秒）\r\n    lettuce:\r\n      pool:\r\n        max-active: 1000  # 连接池最大连接数（使用负值表示没有限制）\r\n        max-wait: -1ms      # 连接池最大阻塞等待时间（使用负值表示没有限制）\r\n        max-idle: 10      # 连接池中的最大空闲连接\r\n        min-idle: 5       # 连接池中的最小空闲连接\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driverClassName: com.mysql.cj.jdbc.Driver\r\n    druid:\r\n      # 主库数据源\r\n      master:\r\n        url: jdbc:mysql://localhost:3306/ry?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n        username: root\r\n        password: root\r\n      # 从库数据源\r\n      slave:\r\n        #从数据源开关/默认关闭\r\n        enabled: false\r\n        url: jdbc:mysql://localhost:3306/wind?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n        username: root\r\n        password: root\r\n      # 初始连接数\r\n      initialSize: 5\r\n      # 最小连接池数量\r\n      minIdle: 10\r\n      # 最大连接池数量\r\n      maxActive: 20\r\n      # 配置获取连接等待超时的时间\r\n      maxWait: 60000\r\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      timeBetweenEvictionRunsMillis: 60000\r\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000\r\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\r\n      maxEvictableIdleTimeMillis: 900000\r\n      # 配置检测连接是否有效\r\n      validationQuery: SELECT 1 FROM DUAL\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n      webStatFilter:\r\n          enabled: true\r\n      statViewServlet:\r\n          enabled: true\r\n          # 设置白名单，不填则允许所有访问\r\n          allow:\r\n          url-pattern: /monitor/druid/*\r\n      filter:\r\n          stat:\r\n              enabled: true\r\n              # 慢SQL记录\r\n              log-slow-sql: true\r\n              slow-sql-millis: 1000\r\n              merge-sql: true\r\n          wall:\r\n              config:\r\n                  multi-statement-allow: true', '008f0cec24930e75b987886519048048', '2019-06-24 11:29:29', '2019-06-24 11:29:29', NULL, '127.0.0.1', '', '', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (8, 'ruoyi-monitor-dev.yml', 'DEFAULT_GROUP', 'spring:\r\n  #安全配置\r\n  security:\r\n    user:\r\n      name: admin\r\n      password: admin\r\n  boot:\r\n    admin:\r\n      ui:\r\n        title: 服务监控中心\r\n        brand: \"<img src=\'assets/img/icon-spring-boot-admin.svg\'><span>应用监控管理</span>\"   ', 'a801dc2ced6ee4f7086693474fa0dbf3', '2019-06-24 12:15:08', '2019-06-24 12:15:08', NULL, '127.0.0.1', '', '', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (9, 'ruoyi-auth-prod.yml', 'DEFAULT_GROUP', '# 数据源配置\r\nspring:\r\n  redis:\r\n    database: 1\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password:      # 密码（默认为空）\r\n    timeout: 6000ms  # 连接超时时长（毫秒）\r\n    lettuce:\r\n      pool:\r\n        max-active: 1000  # 连接池最大连接数（使用负值表示没有限制）\r\n        max-wait: -1ms      # 连接池最大阻塞等待时间（使用负值表示没有限制）\r\n        max-idle: 10      # 连接池中的最大空闲连接\r\n        min-idle: 5       # 连接池中的最小空闲连接', 'c8c59183f3d1587c54e6b31c510a2a4d', '2019-06-24 12:15:47', '2019-06-24 12:15:47', NULL, '127.0.0.1', '', '', NULL, NULL, NULL, 'yaml', NULL);

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint(64) UNSIGNED NOT NULL,
  `nid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (3, 10, 'ruoyi-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring: \r\n redis:\r\n   database: 1\r\n   host: 192.168.0.185\r\n   port: 6379\r\n   password:      # 密码（默认为空）\r\n   timeout: 6000ms  # 连接超时时长（毫秒）\r\n   lettuce:\r\n     pool:\r\n       max-active: 1000  # 连接池最大连接数（使用负值表示没有限制）\r\n       max-wait: -1ms      # 连接池最大阻塞等待时间（使用负值表示没有限制）\r\n       max-idle: 10      # 连接池中的最大空闲连接\r\n       min-idle: 5       # 连接池中的最小空闲连接', '7d6a1969a1d3fd431896456ece275c0b', '2010-05-05 00:00:00', '2019-06-28 15:28:31', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 11, 'ruoyi-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\r\n sentinel:\r\n  transport: \r\n   port: 8527\r\n   dashboard: localhost:8880\r\n redis:\r\n   database: 1\r\n   host: 192.168.0.185\r\n   port: 6379\r\n   password:      # 密码（默认为空）\r\n   timeout: 6000ms  # 连接超时时长（毫秒）\r\n   lettuce:\r\n     pool:\r\n       max-active: 1000  # 连接池最大连接数（使用负值表示没有限制）\r\n       max-wait: -1ms      # 连接池最大阻塞等待时间（使用负值表示没有限制）\r\n       max-idle: 10      # 连接池中的最大空闲连接\r\n       min-idle: 5       # 连接池中的最小空闲连接', 'ac3fd455e62abc718bf5fb2a47f6d2e9', '2010-05-05 00:00:00', '2019-06-28 15:29:56', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 12, 'ruoyi-auth-dev.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  redis:\r\n    database: 1\r\n    host: 192.168.0.185\r\n    port: 6379\r\n    password:      \r\n    timeout: 6000ms  \r\n    lettuce:\r\n      pool:\r\n        max-active: 1000  \r\n        max-wait: -1ms     \r\n        max-idle: 10     \r\n        min-idle: 5       ', '93655372645a71ad603259e7dba81a43', '2010-05-05 00:00:00', '2019-06-28 16:06:15', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 13, 'ruoyi-auth-dev.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  cloud:\r\n    sentinel:\r\n      transport:\r\n        port: 8527\r\n        dashboard: localhost:8889\r\n  redis:\r\n    database: 1\r\n    host: 192.168.0.185\r\n    port: 6379\r\n    password:      \r\n    timeout: 6000ms  \r\n    lettuce:\r\n      pool:\r\n        max-active: 1000  \r\n        max-wait: -1ms     \r\n        max-idle: 10     \r\n        min-idle: 5       ', 'c15d7577a922345c8379cf4b1c620e4a', '2010-05-05 00:00:00', '2019-06-28 16:07:52', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 14, 'ruoyi-auth-dev.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  cloud:\r\n    sentinel:\r\n      transport:\r\n        port: 8527\r\n        dashboard: 192.168.0.10:8889\r\n  redis:\r\n    database: 1\r\n    host: 192.168.0.185\r\n    port: 6379\r\n    password:      \r\n    timeout: 6000ms  \r\n    lettuce:\r\n      pool:\r\n        max-active: 1000  \r\n        max-wait: -1ms     \r\n        max-idle: 10     \r\n        min-idle: 5       ', '3f518ea9e8e5edffb7bc2a7c3737a945', '2010-05-05 00:00:00', '2019-06-28 16:42:48', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (6, 15, 'ruoyi-system-dev.yml', 'DEFAULT_GROUP', '', '# 数据源配置\r\nspring:\r\n  redis:\r\n    database: 1\r\n    host: 192.168.0.185\r\n    port: 6379\r\n    password:      # 密码（默认为空）\r\n    timeout: 6000ms  # 连接超时时长（毫秒）\r\n    lettuce:\r\n      pool:\r\n        max-active: 1000  # 连接池最大连接数（使用负值表示没有限制）\r\n        max-wait: -1ms      # 连接池最大阻塞等待时间（使用负值表示没有限制）\r\n        max-idle: 10      # 连接池中的最大空闲连接\r\n        min-idle: 5       # 连接池中的最小空闲连接\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driverClassName: com.mysql.cj.jdbc.Driver\r\n    druid:\r\n      # 主库数据源\r\n      master:\r\n        url: jdbc:mysql://localhost:3306/ry?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n        username: root\r\n        password: root\r\n      # 从库数据源\r\n      slave:\r\n        #从数据源开关/默认关闭\r\n        enabled: false\r\n        url: jdbc:mysql://localhost:3306/wind?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n        username: root\r\n        password: root\r\n      # 初始连接数\r\n      initialSize: 5\r\n      # 最小连接池数量\r\n      minIdle: 10\r\n      # 最大连接池数量\r\n      maxActive: 20\r\n      # 配置获取连接等待超时的时间\r\n      maxWait: 60000\r\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      timeBetweenEvictionRunsMillis: 60000\r\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000\r\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\r\n      maxEvictableIdleTimeMillis: 900000\r\n      # 配置检测连接是否有效\r\n      validationQuery: SELECT 1 FROM DUAL\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n      webStatFilter:\r\n          enabled: true\r\n      statViewServlet:\r\n          enabled: true\r\n          # 设置白名单，不填则允许所有访问\r\n          allow:\r\n          url-pattern: /monitor/druid/*\r\n      filter:\r\n          stat:\r\n              enabled: true\r\n              # 慢SQL记录\r\n              log-slow-sql: true\r\n              slow-sql-millis: 1000\r\n              merge-sql: true\r\n          wall:\r\n              config:\r\n                  multi-statement-allow: true', 'ed57d57da718aac62684e3cee90732b9', '2010-05-05 00:00:00', '2019-07-03 19:03:27', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (6, 16, 'ruoyi-system-dev.yml', 'DEFAULT_GROUP', '', '# 数据源配置\r\nspring:\r\n  redis:\r\n    database: 1\r\n    host: 192.168.0.185\r\n    port: 6379\r\n    password:      # 密码（默认为空）\r\n    timeout: 6000ms  # 连接超时时长（毫秒）\r\n    lettuce:\r\n      pool:\r\n        max-active: 1000  # 连接池最大连接数（使用负值表示没有限制）\r\n        max-wait: -1ms      # 连接池最大阻塞等待时间（使用负值表示没有限制）\r\n        max-idle: 10      # 连接池中的最大空闲连接\r\n        min-idle: 5       # 连接池中的最小空闲连接\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driverClassName: com.mysql.cj.jdbc.Driver\r\n    druid:\r\n      # 主库数据源\r\n      master:\r\n        url: jdbc:mysql://localhost:3306/ry_cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n        username: root\r\n        password: root\r\n      # 从库数据源\r\n      slave:\r\n        #从数据源开关/默认关闭\r\n        enabled: false\r\n        url: jdbc:mysql://localhost:3306/wind?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n        username: root\r\n        password: root\r\n      # 初始连接数\r\n      initialSize: 5\r\n      # 最小连接池数量\r\n      minIdle: 10\r\n      # 最大连接池数量\r\n      maxActive: 20\r\n      # 配置获取连接等待超时的时间\r\n      maxWait: 60000\r\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      timeBetweenEvictionRunsMillis: 60000\r\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000\r\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\r\n      maxEvictableIdleTimeMillis: 900000\r\n      # 配置检测连接是否有效\r\n      validationQuery: SELECT 1 FROM DUAL\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n      webStatFilter:\r\n          enabled: true\r\n      statViewServlet:\r\n          enabled: true\r\n          # 设置白名单，不填则允许所有访问\r\n          allow:\r\n          url-pattern: /monitor/druid/*\r\n      filter:\r\n          stat:\r\n              enabled: true\r\n              # 慢SQL记录\r\n              log-slow-sql: true\r\n              slow-sql-millis: 1000\r\n              merge-sql: true\r\n          wall:\r\n              config:\r\n                  multi-statement-allow: true', '0dc74abd6b83e59b8269c8b2cb8d0966', '2010-05-05 00:00:00', '2019-07-25 21:11:44', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (6, 17, 'ruoyi-system-dev.yml', 'DEFAULT_GROUP', '', '# 数据源配置\r\nspring:\r\n  redis:\r\n    database: 1\r\n    host: 192.168.0.185\r\n    port: 6379\r\n    password:      # 密码（默认为空）\r\n    timeout: 6000ms  # 连接超时时长（毫秒）\r\n    lettuce:\r\n      pool:\r\n        max-active: 1000  # 连接池最大连接数（使用负值表示没有限制）\r\n        max-wait: -1ms      # 连接池最大阻塞等待时间（使用负值表示没有限制）\r\n        max-idle: 10      # 连接池中的最大空闲连接\r\n        min-idle: 5       # 连接池中的最小空闲连接\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driverClassName: com.mysql.cj.jdbc.Driver\r\n    druid:\r\n      # 主库数据源\r\n      master:\r\n        url: jdbc:mysql://localhost:3306/ry_cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n        username: root\r\n        password: root\r\n      # 从库数据源\r\n      slave:\r\n        #从数据源开关/默认关闭\r\n        enabled: false\r\n        url: jdbc:mysql://localhost:3306/ry?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n        username: root\r\n        password: root\r\n      # 初始连接数\r\n      initialSize: 5\r\n      # 最小连接池数量\r\n      minIdle: 10\r\n      # 最大连接池数量\r\n      maxActive: 20\r\n      # 配置获取连接等待超时的时间\r\n      maxWait: 60000\r\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      timeBetweenEvictionRunsMillis: 60000\r\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000\r\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\r\n      maxEvictableIdleTimeMillis: 900000\r\n      # 配置检测连接是否有效\r\n      validationQuery: SELECT 1 FROM DUAL\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n      webStatFilter:\r\n          enabled: true\r\n      statViewServlet:\r\n          enabled: true\r\n          # 设置白名单，不填则允许所有访问\r\n          allow:\r\n          url-pattern: /monitor/druid/*\r\n      filter:\r\n          stat:\r\n              enabled: true\r\n              # 慢SQL记录\r\n              log-slow-sql: true\r\n              slow-sql-millis: 1000\r\n              merge-sql: true\r\n          wall:\r\n              config:\r\n                  multi-statement-allow: true', '16024ddd9460323c096999a050b7dc9a', '2010-05-05 00:00:00', '2019-07-25 21:14:55', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (6, 18, 'ruoyi-system-dev.yml', 'DEFAULT_GROUP', '', '# 数据源配置\r\nspring:\r\n  redis:\r\n    database: 1\r\n    host: 192.168.0.185\r\n    port: 6379\r\n    password:      # 密码（默认为空）\r\n    timeout: 6000ms  # 连接超时时长（毫秒）\r\n    lettuce:\r\n      pool:\r\n        max-active: 1000  # 连接池最大连接数（使用负值表示没有限制）\r\n        max-wait: -1ms      # 连接池最大阻塞等待时间（使用负值表示没有限制）\r\n        max-idle: 10      # 连接池中的最大空闲连接\r\n        min-idle: 5       # 连接池中的最小空闲连接\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driverClassName: com.mysql.cj.jdbc.Driver\r\n    druid:\r\n      # 主库数据源\r\n      master:\r\n        url: jdbc:mysql://localhost:3306/ry_cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n        username: root\r\n        password: root\r\n      # 从库数据源\r\n      slave:\r\n        #从数据源开关/默认关闭\r\n        enabled: true\r\n        url: jdbc:mysql://localhost:3306/ry?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n        username: root\r\n        password: root\r\n      # 初始连接数\r\n      initialSize: 5\r\n      # 最小连接池数量\r\n      minIdle: 10\r\n      # 最大连接池数量\r\n      maxActive: 20\r\n      # 配置获取连接等待超时的时间\r\n      maxWait: 60000\r\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      timeBetweenEvictionRunsMillis: 60000\r\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000\r\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\r\n      maxEvictableIdleTimeMillis: 900000\r\n      # 配置检测连接是否有效\r\n      validationQuery: SELECT 1 FROM DUAL\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n      webStatFilter:\r\n          enabled: true\r\n      statViewServlet:\r\n          enabled: true\r\n          # 设置白名单，不填则允许所有访问\r\n          allow:\r\n          url-pattern: /monitor/druid/*\r\n      filter:\r\n          stat:\r\n              enabled: true\r\n              # 慢SQL记录\r\n              log-slow-sql: true\r\n              slow-sql-millis: 1000\r\n              merge-sql: true\r\n          wall:\r\n              config:\r\n                  multi-statement-allow: true', '5bbbf878d5a8b725226ad7a82653ddf9', '2010-05-05 00:00:00', '2019-07-25 21:18:21', NULL, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (6, 19, 'ruoyi-system-dev.yml', 'DEFAULT_GROUP', '', '# 数据源配置\r\nspring:\r\n  redis:\r\n    database: 1\r\n    host: 192.168.0.185\r\n    port: 6379\r\n    password:      # 密码（默认为空）\r\n    timeout: 6000ms  # 连接超时时长（毫秒）\r\n    lettuce:\r\n      pool:\r\n        max-active: 1000  # 连接池最大连接数（使用负值表示没有限制）\r\n        max-wait: -1ms      # 连接池最大阻塞等待时间（使用负值表示没有限制）\r\n        max-idle: 10      # 连接池中的最大空闲连接\r\n        min-idle: 5       # 连接池中的最小空闲连接\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driverClassName: com.mysql.cj.jdbc.Driver\r\n    druid:\r\n      # 主库数据源\r\n      master:\r\n        url: jdbc:mysql://localhost:3306/ry_cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n        username: root\r\n        password: root\r\n      # 从库数据源\r\n      slave:\r\n        #从数据源开关/默认关闭\r\n        enabled: true\r\n        url: jdbc:mysql://localhost:3306/ry_cloud_read?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n        username: root\r\n        password: root\r\n      # 初始连接数\r\n      initialSize: 5\r\n      # 最小连接池数量\r\n      minIdle: 10\r\n      # 最大连接池数量\r\n      maxActive: 20\r\n      # 配置获取连接等待超时的时间\r\n      maxWait: 60000\r\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      timeBetweenEvictionRunsMillis: 60000\r\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000\r\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\r\n      maxEvictableIdleTimeMillis: 900000\r\n      # 配置检测连接是否有效\r\n      validationQuery: SELECT 1 FROM DUAL\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n      webStatFilter:\r\n          enabled: true\r\n      statViewServlet:\r\n          enabled: true\r\n          # 设置白名单，不填则允许所有访问\r\n          allow:\r\n          url-pattern: /monitor/druid/*\r\n      filter:\r\n          stat:\r\n              enabled: true\r\n              # 慢SQL记录\r\n              log-slow-sql: true\r\n              slow-sql-millis: 1000\r\n              merge-sql: true\r\n          wall:\r\n              config:\r\n                  multi-statement-allow: true', '1e8fbde3383d5cbb21afe72b9b367c3b', '2010-05-05 00:00:00', '2019-07-25 21:25:32', NULL, '127.0.0.1', 'U', '');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
