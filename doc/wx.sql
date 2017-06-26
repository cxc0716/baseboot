/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : wx

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-06-13 13:39:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `content`
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `text` varchar(1024) CHARACTER SET utf8 NOT NULL,
  `picUrl` varchar(1024) CHARACTER SET utf8 DEFAULT NULL,
  `sendType` int(11) NOT NULL,
  `friendFlag` int(11) NOT NULL,
  `sex` int(11) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES ('7', '1', 's', '', '100100', '100200', '100400', '2017-06-10 14:15:23', '1', '0');

-- ----------------------------
-- Table structure for `enumeration`
-- ----------------------------
DROP TABLE IF EXISTS `enumeration`;
CREATE TABLE `enumeration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `enName` varchar(50) CHARACTER SET utf8 NOT NULL,
  `displayRef` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(300) CHARACTER SET utf8 DEFAULT NULL,
  `enumerationType` int(11) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100401 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of enumeration
-- ----------------------------
INSERT INTO `enumeration` VALUES ('1200', '0', 'gender', '性别', '性别', '1', '0');
INSERT INTO `enumeration` VALUES ('1300', '0', 'job', '职位', '职位', '0', '0');
INSERT INTO `enumeration` VALUES ('1400', '0', 'userType', '用户类型', '用户类型', '1', '0');
INSERT INTO `enumeration` VALUES ('2900', '0', 'securityScope', '权限范围', '权限范围', '1', '0');
INSERT INTO `enumeration` VALUES ('3200', '0', 'yesNo', '是否', '是否', '1', '0');
INSERT INTO `enumeration` VALUES ('5300', '0', 'messageType', '消息类型', '消息类型', '0', '0');
INSERT INTO `enumeration` VALUES ('12300', '0', 'reportDataType', '数据类型', '数据类型', '0', '0');
INSERT INTO `enumeration` VALUES ('12400', '0', 'stretchingType', '伸展方式', '伸展方式', '0', '0');
INSERT INTO `enumeration` VALUES ('100100', '0', 'sendType', '类型', '类型', '0', '0');
INSERT INTO `enumeration` VALUES ('100200', '0', 'friendFlag', '好友标记', '好友标记', '0', '0');
INSERT INTO `enumeration` VALUES ('100400', '0', 'sexEnum', '性别', '性别', '0', '0');

-- ----------------------------
-- Table structure for `enumerationvalue`
-- ----------------------------
DROP TABLE IF EXISTS `enumerationvalue`;
CREATE TABLE `enumerationvalue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `valueName` varchar(50) CHARACTER SET utf8 NOT NULL,
  `displayRef` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(300) CHARACTER SET utf8 DEFAULT NULL,
  `valueCode` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `enumeration` int(11) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100403 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of enumerationvalue
-- ----------------------------
INSERT INTO `enumerationvalue` VALUES ('1200', '0', 'Male', '男', '男', null, '1200', '0');
INSERT INTO `enumerationvalue` VALUES ('1201', '0', 'Woman', '女', '女', null, '1200', '0');
INSERT INTO `enumerationvalue` VALUES ('1300', '0', 'Accountant', '会计', '会计', null, '1300', '0');
INSERT INTO `enumerationvalue` VALUES ('1301', '0', 'Cashier', '出纳', '出纳', null, '1300', '0');
INSERT INTO `enumerationvalue` VALUES ('1302', '0', 'ProjectManager', '项目经理', '项目经理', null, '1300', '0');
INSERT INTO `enumerationvalue` VALUES ('1400', '0', 'Administrator', '超级管理员', '超级管理员', null, '1400', '0');
INSERT INTO `enumerationvalue` VALUES ('1401', '0', 'Manager', '管理员', '管理员', null, '1400', '0');
INSERT INTO `enumerationvalue` VALUES ('1402', '0', 'Menual', '一般用户', '一般用户', null, '1400', '0');
INSERT INTO `enumerationvalue` VALUES ('2900', '0', 'User_Level', '用户级', '用户级', null, '2900', '0');
INSERT INTO `enumerationvalue` VALUES ('2901', '0', 'CurrentOrg_Level', '当前部门级', '当前部门级', null, '2900', '0');
INSERT INTO `enumerationvalue` VALUES ('2902', '0', 'Org_Level', '部门级', '部门级', null, '2900', '0');
INSERT INTO `enumerationvalue` VALUES ('2903', '0', 'OrgAndSuborg_Level', '部门及子部门级', '部门及子部门级', null, '2900', '0');
INSERT INTO `enumerationvalue` VALUES ('2904', '0', 'System_Level', '系统级', '系统级', null, '2900', '0');
INSERT INTO `enumerationvalue` VALUES ('3200', '0', 'Yes', '是', '是', null, '3200', '0');
INSERT INTO `enumerationvalue` VALUES ('3201', '0', 'No', '否', '否', null, '3200', '0');
INSERT INTO `enumerationvalue` VALUES ('5300', '0', 'SMS', '手机短信', '手机短信', null, '5300', '0');
INSERT INTO `enumerationvalue` VALUES ('5301', '0', 'InternalMail', '内部邮件', '内部邮件', null, '5300', '0');
INSERT INTO `enumerationvalue` VALUES ('5302', '0', 'ExternalMail', '外部邮件', '外部邮件', null, '5300', '0');
INSERT INTO `enumerationvalue` VALUES ('5303', '0', 'InteralMessage', '短消息', '短消息', null, '5300', '0');
INSERT INTO `enumerationvalue` VALUES ('5304', '0', 'QQ', 'QQ', 'QQ', null, '5300', '0');
INSERT INTO `enumerationvalue` VALUES ('5305', '0', 'MSN', 'MSN', 'MSN', null, '5300', '0');
INSERT INTO `enumerationvalue` VALUES ('12300', '0', 'Element', '元素', '元素', null, '12300', '0');
INSERT INTO `enumerationvalue` VALUES ('12301', '0', 'Collection', '集合', '集合', null, '12300', '0');
INSERT INTO `enumerationvalue` VALUES ('12400', '0', 'Vertical', '纵向', '纵向', null, '12400', '0');
INSERT INTO `enumerationvalue` VALUES ('12401', '0', 'Horizontal', '横向', '横向', null, '12400', '0');
INSERT INTO `enumerationvalue` VALUES ('100100', '0', 'friend', '好友', '好友', '1', '100100', '0');
INSERT INTO `enumerationvalue` VALUES ('100101', '0', 'wxgroup', '微信群', '微信群', '2', '100100', '0');
INSERT INTO `enumerationvalue` VALUES ('100200', '0', 'active', '正常', '正常', '1', '100200', '0');
INSERT INTO `enumerationvalue` VALUES ('100201', '0', 'inactive', '僵尸粉', '僵尸粉', '2', '100200', '0');
INSERT INTO `enumerationvalue` VALUES ('100400', '0', 'all', '全部', '全部', '0', '100400', '0');
INSERT INTO `enumerationvalue` VALUES ('100401', '0', 'boy', '男', '男', '1', '100400', '0');
INSERT INTO `enumerationvalue` VALUES ('100402', '0', 'girl', '女', '女', '2', '100400', '0');

-- ----------------------------
-- Table structure for `himenu`
-- ----------------------------
DROP TABLE IF EXISTS `himenu`;
CREATE TABLE `himenu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `menuName` varchar(100) CHARACTER SET utf8 NOT NULL,
  `displayRef` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `parentMenu` int(11) DEFAULT NULL,
  `sequence` decimal(18,2) DEFAULT NULL,
  `menuType` int(11) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of himenu
-- ----------------------------
INSERT INTO `himenu` VALUES ('1000', '0', 'organization', '部门管理', '部门管理', '0', '9999.00', null, '0');
INSERT INTO `himenu` VALUES ('2000', '0', 'security', '安全管理', '安全管理', '0', '9999.00', null, '0');
INSERT INTO `himenu` VALUES ('3000', '0', 'enumeration', '枚举管理', '枚举管理', '0', '9999.00', null, '0');
INSERT INTO `himenu` VALUES ('5000', '0', 'sysapp', '系统管理', '系统管理', '0', '9999.00', null, '0');
INSERT INTO `himenu` VALUES ('10000', '0', 'menu', '菜单管理', '菜单管理', '0', '9999.00', null, '0');
INSERT INTO `himenu` VALUES ('12000', '0', 'report', '报表管理', '报表管理', '0', '9999.00', null, '0');
INSERT INTO `himenu` VALUES ('42000', '0', 'i18n', '国际化', '国际化', '0', '9999.00', null, '0');
INSERT INTO `himenu` VALUES ('100000', '0', 'cms', '内容管理', '内容管理', '0', '9999.00', null, '0');

-- ----------------------------
-- Table structure for `hi_appsetting`
-- ----------------------------
DROP TABLE IF EXISTS `hi_appsetting`;
CREATE TABLE `hi_appsetting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `appGroup` varchar(30) CHARACTER SET utf8 NOT NULL,
  `appName` varchar(30) CHARACTER SET utf8 NOT NULL,
  `appValue` varchar(100) CHARACTER SET utf8 NOT NULL,
  `description` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_appsetting
-- ----------------------------
INSERT INTO `hi_appsetting` VALUES ('1', '0', 'HOSTING', 'WEB_HOSTING', 'http://localhost:8080', '应用服务器地址', '0');

-- ----------------------------
-- Table structure for `hi_attachment`
-- ----------------------------
DROP TABLE IF EXISTS `hi_attachment`;
CREATE TABLE `hi_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `fileName` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `fileType` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `fileSize` decimal(18,2) DEFAULT NULL,
  `attachmentType` int(11) DEFAULT NULL,
  `attachmentPath` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `attachDesc` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for `hi_authority`
-- ----------------------------
DROP TABLE IF EXISTS `hi_authority`;
CREATE TABLE `hi_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `authorityName` varchar(200) CHARACTER SET utf8 NOT NULL,
  `displayRef` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `propertedResource` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `authorityType` int(11) DEFAULT NULL,
  `menuLink` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100305 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_authority
-- ----------------------------
INSERT INTO `hi_authority` VALUES ('1000', '0', 'HIORG_LIST', 'organization.HiOrgList', '', '部门查询', '1', '1000');
INSERT INTO `hi_authority` VALUES ('1001', '0', 'HIORG_VIEW', 'organization.HiOrgView', '', '部门查看', '2', '1000');
INSERT INTO `hi_authority` VALUES ('1002', '0', 'HIORG_SAVE', 'organization.HiOrgSave', '', '部门保存', '3', '1000');
INSERT INTO `hi_authority` VALUES ('1003', '0', 'HIORG_DEL', 'organization.HiOrgDel', '', '部门删除', '4', '1000');
INSERT INTO `hi_authority` VALUES ('1004', '0', 'HIORG_LOOKUP', 'organization.HiOrgLookup', '', '部门带回', '1', '1000');
INSERT INTO `hi_authority` VALUES ('1100', '0', 'HIUSER_LIST', 'organization.HiUserList', '', '人员查询', '1', '1100');
INSERT INTO `hi_authority` VALUES ('1101', '0', 'HIUSER_VIEW', 'organization.HiUserView', '', '人员查看', '2', '1100');
INSERT INTO `hi_authority` VALUES ('1102', '0', 'HIUSER_SAVE', 'organization.HiUserSave', '', '人员保存', '3', '1100');
INSERT INTO `hi_authority` VALUES ('1103', '0', 'HIUSER_DEL', 'organization.HiUserDel', '', '人员删除', '4', '1100');
INSERT INTO `hi_authority` VALUES ('1104', '0', 'HIUSER_LOOKUP', 'organization.HiUserLookup', '', '人员带回', '1', '1100');
INSERT INTO `hi_authority` VALUES ('2100', '0', 'ROLE_LIST', 'security.RoleList', '', '角色查询', '1', '2100');
INSERT INTO `hi_authority` VALUES ('2101', '0', 'ROLE_VIEW', 'security.RoleView', '', '角色查看', '2', '2100');
INSERT INTO `hi_authority` VALUES ('2102', '0', 'ROLE_SAVE', 'security.RoleSave', '', '角色保存', '3', '2100');
INSERT INTO `hi_authority` VALUES ('2103', '0', 'ROLE_DEL', 'security.RoleDel', '', '角色删除', '4', '2100');
INSERT INTO `hi_authority` VALUES ('2104', '0', 'ROLE_LOOKUP', 'security.RoleLookup', '', '角色带回', '1', '2100');
INSERT INTO `hi_authority` VALUES ('2300', '0', 'USERAUTHORITY_LIST', 'security.UserAuthorityList', '', '用户权限查询', '1', '2300');
INSERT INTO `hi_authority` VALUES ('2301', '0', 'USERAUTHORITY_VIEW', 'security.UserAuthorityView', '', '用户权限查看', '2', '2300');
INSERT INTO `hi_authority` VALUES ('2302', '0', 'USERAUTHORITY_SAVE', 'security.UserAuthoritySave', '', '用户权限保存', '3', '2300');
INSERT INTO `hi_authority` VALUES ('2303', '0', 'USERAUTHORITY_DEL', 'security.UserAuthorityDel', '', '用户权限删除', '4', '2300');
INSERT INTO `hi_authority` VALUES ('2304', '0', 'USERAUTHORITY_LOOKUP', 'security.UserAuthorityLookup', '', '用户权限带回', '1', '2300');
INSERT INTO `hi_authority` VALUES ('2800', '0', 'PRIVILEGERESOURCE_LIST', 'security.PrivilegeResourceList', '', '权限资源查询', '1', '2800');
INSERT INTO `hi_authority` VALUES ('2801', '0', 'PRIVILEGERESOURCE_VIEW', 'security.PrivilegeResourceView', '', '权限资源查看', '2', '2800');
INSERT INTO `hi_authority` VALUES ('2802', '0', 'PRIVILEGERESOURCE_SAVE', 'security.PrivilegeResourceSave', '', '权限资源保存', '3', '2800');
INSERT INTO `hi_authority` VALUES ('2803', '0', 'PRIVILEGERESOURCE_DEL', 'security.PrivilegeResourceDel', '', '权限资源删除', '4', '2800');
INSERT INTO `hi_authority` VALUES ('2804', '0', 'PRIVILEGERESOURCE_LOOKUP', 'security.PrivilegeResourceLookup', '', '权限资源带回', '1', '2800');
INSERT INTO `hi_authority` VALUES ('2999', '0', 'SYSTEM_USER_MANAGE', '用户管理', '', '用户管理', '0', '2701');
INSERT INTO `hi_authority` VALUES ('3000', '0', 'ENUMERATION_LIST', 'enumeration.EnumerationList', '', '枚举实体查询', '1', '3000');
INSERT INTO `hi_authority` VALUES ('3001', '0', 'ENUMERATION_VIEW', 'enumeration.EnumerationView', '', '枚举实体查看', '2', '3000');
INSERT INTO `hi_authority` VALUES ('3002', '0', 'ENUMERATION_SAVE', 'enumeration.EnumerationSave', '', '枚举实体保存', '3', '3000');
INSERT INTO `hi_authority` VALUES ('3003', '0', 'ENUMERATION_DEL', 'enumeration.EnumerationDel', '', '枚举实体删除', '4', '3000');
INSERT INTO `hi_authority` VALUES ('3004', '0', 'ENUMERATION_LOOKUP', 'enumeration.EnumerationLookup', '', '枚举实体带回', '1', '3000');
INSERT INTO `hi_authority` VALUES ('3100', '0', 'ENUMERATIONVALUE_LIST', 'enumeration.EnumerationValueList', '', '枚举值查询', '1', '3100');
INSERT INTO `hi_authority` VALUES ('3101', '0', 'ENUMERATIONVALUE_VIEW', 'enumeration.EnumerationValueView', '', '枚举值查看', '2', '3100');
INSERT INTO `hi_authority` VALUES ('3102', '0', 'ENUMERATIONVALUE_SAVE', 'enumeration.EnumerationValueSave', '', '枚举值保存', '3', '3100');
INSERT INTO `hi_authority` VALUES ('3103', '0', 'ENUMERATIONVALUE_DEL', 'enumeration.EnumerationValueDel', '', '枚举值删除', '4', '3100');
INSERT INTO `hi_authority` VALUES ('3104', '0', 'ENUMERATIONVALUE_LOOKUP', 'enumeration.EnumerationValueLookup', '', '枚举值带回', '1', '3100');
INSERT INTO `hi_authority` VALUES ('4000', '0', 'JOBDETAILDEF_LIST', 'schedule.JobDetailDefList', '', '工作项定义查询', '1', '4000');
INSERT INTO `hi_authority` VALUES ('4001', '0', 'JOBDETAILDEF_VIEW', 'schedule.JobDetailDefView', '', '工作项定义查看', '2', '4000');
INSERT INTO `hi_authority` VALUES ('4002', '0', 'JOBDETAILDEF_SAVE', 'schedule.JobDetailDefSave', '', '工作项定义保存', '3', '4000');
INSERT INTO `hi_authority` VALUES ('4003', '0', 'JOBDETAILDEF_DEL', 'schedule.JobDetailDefDel', '', '工作项定义删除', '4', '4000');
INSERT INTO `hi_authority` VALUES ('4004', '0', 'JOBDETAILDEF_LOOKUP', 'schedule.JobDetailDefLookup', '', '工作项定义带回', '1', '4000');
INSERT INTO `hi_authority` VALUES ('4100', '0', 'TRIGGERDEF_LIST', 'schedule.TriggerDefList', '', '触发器查询', '1', '4100');
INSERT INTO `hi_authority` VALUES ('4101', '0', 'TRIGGERDEF_VIEW', 'schedule.TriggerDefView', '', '触发器查看', '2', '4100');
INSERT INTO `hi_authority` VALUES ('4102', '0', 'TRIGGERDEF_SAVE', 'schedule.TriggerDefSave', '', '触发器保存', '3', '4100');
INSERT INTO `hi_authority` VALUES ('4103', '0', 'TRIGGERDEF_DEL', 'schedule.TriggerDefDel', '', '触发器删除', '4', '4100');
INSERT INTO `hi_authority` VALUES ('4104', '0', 'TRIGGERDEF_LOOKUP', 'schedule.TriggerDefLookup', '', '触发器带回', '1', '4100');
INSERT INTO `hi_authority` VALUES ('5000', '0', 'APPSETTING_LIST', 'sysapp.AppSettingList', '', '应用配置查询', '1', '5000');
INSERT INTO `hi_authority` VALUES ('5001', '0', 'APPSETTING_VIEW', 'sysapp.AppSettingView', '', '应用配置查看', '2', '5000');
INSERT INTO `hi_authority` VALUES ('5002', '0', 'APPSETTING_SAVE', 'sysapp.AppSettingSave', '', '应用配置保存', '3', '5000');
INSERT INTO `hi_authority` VALUES ('5003', '0', 'APPSETTING_DEL', 'sysapp.AppSettingDel', '', '应用配置删除', '4', '5000');
INSERT INTO `hi_authority` VALUES ('5004', '0', 'APPSETTING_LOOKUP', 'sysapp.AppSettingLookup', '', '应用配置带回', '1', '5000');
INSERT INTO `hi_authority` VALUES ('5100', '0', 'MESSAGE_LIST', 'sysapp.MessageList', '', '消息查询', '1', '5100');
INSERT INTO `hi_authority` VALUES ('5101', '0', 'MESSAGE_VIEW', 'sysapp.MessageView', '', '消息查看', '2', '5100');
INSERT INTO `hi_authority` VALUES ('5102', '0', 'MESSAGE_SAVE', 'sysapp.MessageSave', '', '消息保存', '3', '5100');
INSERT INTO `hi_authority` VALUES ('5103', '0', 'MESSAGE_DEL', 'sysapp.MessageDel', '', '消息删除', '4', '5100');
INSERT INTO `hi_authority` VALUES ('5104', '0', 'MESSAGE_LOOKUP', 'sysapp.MessageLookup', '', '消息带回', '1', '5100');
INSERT INTO `hi_authority` VALUES ('5200', '0', 'MESSAGEPARAMETER_LIST', 'sysapp.MessageParameterList', '', '消息参数查询', '1', '5200');
INSERT INTO `hi_authority` VALUES ('5201', '0', 'MESSAGEPARAMETER_VIEW', 'sysapp.MessageParameterView', '', '消息参数查看', '2', '5200');
INSERT INTO `hi_authority` VALUES ('5202', '0', 'MESSAGEPARAMETER_SAVE', 'sysapp.MessageParameterSave', '', '消息参数保存', '3', '5200');
INSERT INTO `hi_authority` VALUES ('5203', '0', 'MESSAGEPARAMETER_DEL', 'sysapp.MessageParameterDel', '', '消息参数删除', '4', '5200');
INSERT INTO `hi_authority` VALUES ('5204', '0', 'MESSAGEPARAMETER_LOOKUP', 'sysapp.MessageParameterLookup', '', '消息参数带回', '1', '5200');
INSERT INTO `hi_authority` VALUES ('5400', '0', 'HILOG_LIST', 'sysapp.HiLogList', '', '系统日志查询', '1', '5400');
INSERT INTO `hi_authority` VALUES ('5401', '0', 'HILOG_VIEW', 'sysapp.HiLogView', '', '系统日志查看', '2', '5400');
INSERT INTO `hi_authority` VALUES ('5403', '0', 'HILOG_DEL', 'sysapp.HiLogDel', '', '系统日志删除', '4', '5400');
INSERT INTO `hi_authority` VALUES ('10000', '0', 'MENU_LIST', 'menu.MenuList', '', '菜单项查询', '1', '10000');
INSERT INTO `hi_authority` VALUES ('10001', '0', 'MENU_VIEW', 'menu.MenuView', '', '菜单项查看', '2', '10000');
INSERT INTO `hi_authority` VALUES ('10002', '0', 'MENU_SAVE', 'menu.MenuSave', '', '菜单项保存', '3', '10000');
INSERT INTO `hi_authority` VALUES ('10003', '0', 'MENU_DEL', 'menu.MenuDel', '', '菜单项删除', '4', '10000');
INSERT INTO `hi_authority` VALUES ('10004', '0', 'MENU_LOOKUP', 'menu.MenuLookup', '', '菜单项带回', '1', '10000');
INSERT INTO `hi_authority` VALUES ('10100', '0', 'MENULINK_LIST', 'menu.MenuLinkList', '', '菜单链接查询', '1', '10100');
INSERT INTO `hi_authority` VALUES ('10101', '0', 'MENULINK_VIEW', 'menu.MenuLinkView', '', '菜单链接查看', '2', '10100');
INSERT INTO `hi_authority` VALUES ('10102', '0', 'MENULINK_SAVE', 'menu.MenuLinkSave', '', '菜单链接保存', '3', '10100');
INSERT INTO `hi_authority` VALUES ('10103', '0', 'MENULINK_DEL', 'menu.MenuLinkDel', '', '菜单链接删除', '4', '10100');
INSERT INTO `hi_authority` VALUES ('10104', '0', 'MENULINK_LOOKUP', 'menu.MenuLinkLookup', '', '菜单链接带回', '1', '10100');
INSERT INTO `hi_authority` VALUES ('12000', '0', 'EXCELREPORTDESIGN_LIST', 'report.ExcelReportDesignList', '', 'Excel报表设计查询', '1', '12000');
INSERT INTO `hi_authority` VALUES ('12001', '0', 'EXCELREPORTDESIGN_VIEW', 'report.ExcelReportDesignView', '', 'Excel报表设计查看', '2', '12000');
INSERT INTO `hi_authority` VALUES ('12002', '0', 'EXCELREPORTDESIGN_SAVE', 'report.ExcelReportDesignSave', '', 'Excel报表设计保存', '3', '12000');
INSERT INTO `hi_authority` VALUES ('12003', '0', 'EXCELREPORTDESIGN_DEL', 'report.ExcelReportDesignDel', '', 'Excel报表设计删除', '4', '12000');
INSERT INTO `hi_authority` VALUES ('12004', '0', 'EXCELREPORTDESIGN_LOOKUP', 'report.ExcelReportDesignLookup', '', 'Excel报表设计带回', '1', '12000');
INSERT INTO `hi_authority` VALUES ('12100', '0', 'EXCELSHEET_LIST', 'report.ExcelSheetList', '', '工作表查询', '1', '12100');
INSERT INTO `hi_authority` VALUES ('12101', '0', 'EXCELSHEET_VIEW', 'report.ExcelSheetView', '', '工作表查看', '2', '12100');
INSERT INTO `hi_authority` VALUES ('12102', '0', 'EXCELSHEET_SAVE', 'report.ExcelSheetSave', '', '工作表保存', '3', '12100');
INSERT INTO `hi_authority` VALUES ('12103', '0', 'EXCELSHEET_DEL', 'report.ExcelSheetDel', '', '工作表删除', '4', '12100');
INSERT INTO `hi_authority` VALUES ('12104', '0', 'EXCELSHEET_LOOKUP', 'report.ExcelSheetLookup', '', '工作表带回', '1', '12100');
INSERT INTO `hi_authority` VALUES ('12200', '0', 'EXCELCELL_LIST', 'report.ExcelCellList', '', '单元格查询', '1', '12200');
INSERT INTO `hi_authority` VALUES ('12201', '0', 'EXCELCELL_VIEW', 'report.ExcelCellView', '', '单元格查看', '2', '12200');
INSERT INTO `hi_authority` VALUES ('12202', '0', 'EXCELCELL_SAVE', 'report.ExcelCellSave', '', '单元格保存', '3', '12200');
INSERT INTO `hi_authority` VALUES ('12203', '0', 'EXCELCELL_DEL', 'report.ExcelCellDel', '', '单元格删除', '4', '12200');
INSERT INTO `hi_authority` VALUES ('12204', '0', 'EXCELCELL_LOOKUP', 'report.ExcelCellLookup', '', '单元格带回', '1', '12200');
INSERT INTO `hi_authority` VALUES ('100300', '0', 'CONTENT_LIST', 'cms.ContentList', '', '内容管理查询', '1', '100300');
INSERT INTO `hi_authority` VALUES ('100301', '0', 'CONTENT_VIEW', 'cms.ContentView', '', '内容管理查看', '2', '100300');
INSERT INTO `hi_authority` VALUES ('100302', '0', 'CONTENT_SAVE', 'cms.ContentSave', '', '内容管理保存', '3', '100300');
INSERT INTO `hi_authority` VALUES ('100303', '0', 'CONTENT_DEL', 'cms.ContentDel', '', '内容管理删除', '4', '100300');
INSERT INTO `hi_authority` VALUES ('100304', '0', 'CONTENT_LOOKUP', 'cms.ContentLookup', '', '内容管理带回', '1', '100300');

-- ----------------------------
-- Table structure for `hi_excelcell`
-- ----------------------------
DROP TABLE IF EXISTS `hi_excelcell`;
CREATE TABLE `hi_excelcell` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `excelSheet` int(11) DEFAULT NULL,
  `cellColumn` varchar(10) CHARACTER SET utf8 NOT NULL,
  `cellRow` int(11) NOT NULL,
  `variableName` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `constant` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `isEnumeration` int(11) DEFAULT NULL,
  `reportDataType` int(11) DEFAULT NULL,
  `stretchingType` int(11) DEFAULT NULL,
  `conditionCell` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_excelcell
-- ----------------------------

-- ----------------------------
-- Table structure for `hi_excelreportdesign`
-- ----------------------------
DROP TABLE IF EXISTS `hi_excelreportdesign`;
CREATE TABLE `hi_excelreportdesign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `reportName` varchar(20) CHARACTER SET utf8 NOT NULL,
  `reportNum` varchar(10) CHARACTER SET utf8 NOT NULL,
  `template` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  `actionName` varchar(100) CHARACTER SET utf8 NOT NULL,
  `description` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_excelreportdesign
-- ----------------------------

-- ----------------------------
-- Table structure for `hi_excelsheet`
-- ----------------------------
DROP TABLE IF EXISTS `hi_excelsheet`;
CREATE TABLE `hi_excelsheet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `excelReportDesign` int(11) DEFAULT NULL,
  `sheetName` varchar(50) CHARACTER SET utf8 NOT NULL,
  `sequence` decimal(18,2) DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_excelsheet
-- ----------------------------

-- ----------------------------
-- Table structure for `hi_grouprole`
-- ----------------------------
DROP TABLE IF EXISTS `hi_grouprole`;
CREATE TABLE `hi_grouprole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `securityGroup` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_grouprole
-- ----------------------------

-- ----------------------------
-- Table structure for `hi_jobdetaildef`
-- ----------------------------
DROP TABLE IF EXISTS `hi_jobdetaildef`;
CREATE TABLE `hi_jobdetaildef` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `jobName` varchar(80) CHARACTER SET utf8 NOT NULL,
  `jobGroup` varchar(80) CHARACTER SET utf8 DEFAULT NULL,
  `jobClassName` varchar(128) CHARACTER SET utf8 NOT NULL,
  `durable` int(11) DEFAULT NULL,
  `volatiled` int(11) DEFAULT NULL,
  `shouldRecover` int(11) DEFAULT NULL,
  `description` varchar(300) CHARACTER SET utf8 DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_jobdetaildef
-- ----------------------------

-- ----------------------------
-- Table structure for `hi_language`
-- ----------------------------
DROP TABLE IF EXISTS `hi_language`;
CREATE TABLE `hi_language` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `keyStr` varchar(200) CHARACTER SET utf8 NOT NULL,
  `service` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `entity` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `isSystem` int(11) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100403 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_language
-- ----------------------------
INSERT INTO `hi_language` VALUES ('3', '5', '查询条件', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('4', '0', '新建', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('5', '0', '查询', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('6', '0', '重置', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('8', '6', '列表', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('9', '0', '操作', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('10', '0', '查找带回', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('11', '0', '删除', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('12', '0', '查看', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('13', '0', '编辑', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('14', '3', '取消全选', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('15', '0', '全选', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('16', '0', '批量删除', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('17', '2', '编辑页面', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('18', '0', '保存', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('19', '0', '关闭', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('20', '1', '查看页面', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('22', '0', '总条数', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('23', '0', '总页数', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('24', '0', '尾页', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('25', '0', '下一页', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('26', '0', '上一页', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('27', '0', '首页', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('28', '0', '到', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('29', '0', '页', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('30', '0', '跳转', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('31', '0', '全部', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('32', '0', '操作符', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('33', '0', '小于', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('34', '0', '大于', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('35', '0', '等于', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('36', '0', '小于等于', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('37', '0', '大于等于', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('38', '1', '请选择需要上传的附件', null, 'Attachment', '1', '1');
INSERT INTO `hi_language` VALUES ('39', '1', '上传', null, 'Attachment', '1', '1');
INSERT INTO `hi_language` VALUES ('40', '3', '请先选择需要上传的文件!', null, 'Attachment', '1', '1');
INSERT INTO `hi_language` VALUES ('41', '0', '权限管理', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('42', '0', '错误详细信息', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('43', '0', '您没有操作此功能的权限', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('44', '0', '返回', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('45', '0', '未找到Action', null, 'ExcelReportDesign', '1', '1');
INSERT INTO `hi_language` VALUES ('46', '0', '上传文件过大', null, 'Attachment', '1', '1');
INSERT INTO `hi_language` VALUES ('47', '0', 'Action加载失败', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('48', '0', '您是一般用户,不能分派角色', null, 'Role', '1', '1');
INSERT INTO `hi_language` VALUES ('49', '1', '您的用户类型是管理员,必须是您自己创建的角色才可以为该角色分派用户', null, 'Role', '1', '1');
INSERT INTO `hi_language` VALUES ('50', '0', '超级管理员无需授权', null, 'Role', '1', '1');
INSERT INTO `hi_language` VALUES ('51', '0', '您是一般用户,不能删除建角色', null, 'Role', '1', '1');
INSERT INTO `hi_language` VALUES ('52', '0', '您的用户类型为管理员,只能删除您自己所创建的角色', null, 'Role', '1', '1');
INSERT INTO `hi_language` VALUES ('53', '0', '角色名重复', null, 'Role', '1', '1');
INSERT INTO `hi_language` VALUES ('54', '0', '系统无法识别您的用户类型', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('55', '0', '您是一般用户,不能修改与创建角色', null, 'Role', '1', '1');
INSERT INTO `hi_language` VALUES ('56', '1', '您的用户是管理员,所以只能编辑您自己创建的角色!', null, 'Role', '1', '1');
INSERT INTO `hi_language` VALUES ('57', '0', '该页面中的数据已经被改写，请重新刷新页面后重新编辑该记录', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('58', '0', 'Action对象赋值时出错', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('59', '0', '发送邮件失败', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('61', '0', '请选择要授权的用户', null, 'Role', '1', '1');
INSERT INTO `hi_language` VALUES ('62', '0', 'HI平台', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('63', '0', '用户名', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('64', '0', '密码', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('65', '0', '验证码', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('66', '0', '你没有登陆成功', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('67', '0', '可能的原因', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('68', '0', '欢迎', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('69', '0', '登陆', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('70', '0', '退出', null, '', '1', '1');
INSERT INTO `hi_language` VALUES ('1000', '0', '部门', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('1001', '0', '部门名称', null, 'HiOrg', '0', '1');
INSERT INTO `hi_language` VALUES ('1002', '0', '部门编号', null, 'HiOrg', '0', '1');
INSERT INTO `hi_language` VALUES ('1003', '0', '部门经理', null, 'HiOrg', '0', '1');
INSERT INTO `hi_language` VALUES ('1004', '0', '上级部门', null, 'HiOrg', '0', '1');
INSERT INTO `hi_language` VALUES ('1005', '0', '创建人', null, 'HiOrg', '0', '1');
INSERT INTO `hi_language` VALUES ('1100', '0', '人员', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('1101', '0', '帐号', null, 'HiUser', '0', '1');
INSERT INTO `hi_language` VALUES ('1102', '0', '姓名', null, 'HiUser', '0', '1');
INSERT INTO `hi_language` VALUES ('1103', '0', '部门', null, 'HiUser', '0', '1');
INSERT INTO `hi_language` VALUES ('1104', '0', '性别', null, 'HiUser', '0', '1');
INSERT INTO `hi_language` VALUES ('1200', '0', '性别', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('1201', '0', '男', null, 'gender', '0', '1');
INSERT INTO `hi_language` VALUES ('1202', '0', '女', null, 'gender', '0', '1');
INSERT INTO `hi_language` VALUES ('1300', '0', '职位', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('1301', '0', '会计', null, 'job', '0', '1');
INSERT INTO `hi_language` VALUES ('1302', '0', '出纳', null, 'job', '0', '1');
INSERT INTO `hi_language` VALUES ('1303', '0', '项目经理', null, 'job', '0', '1');
INSERT INTO `hi_language` VALUES ('1400', '0', '用户类型', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('1401', '0', '超级管理员', null, 'userType', '0', '1');
INSERT INTO `hi_language` VALUES ('1402', '0', '管理员', null, 'userType', '0', '1');
INSERT INTO `hi_language` VALUES ('2000', '0', '权限', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('2001', '0', '权限名称', null, 'Authority', '0', '1');
INSERT INTO `hi_language` VALUES ('2002', '0', '属性资源', null, 'Authority', '0', '1');
INSERT INTO `hi_language` VALUES ('2003', '0', '描述', null, 'Authority', '0', '1');
INSERT INTO `hi_language` VALUES ('2004', '0', '菜单项', null, 'Authority', '0', '1');
INSERT INTO `hi_language` VALUES ('2100', '0', '角色', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('2101', '0', '角色名称', null, 'Role', '0', '1');
INSERT INTO `hi_language` VALUES ('2102', '0', '显示信息', null, 'Role', '0', '1');
INSERT INTO `hi_language` VALUES ('2103', '0', '描述', null, 'Role', '0', '1');
INSERT INTO `hi_language` VALUES ('2104', '0', '创建人', null, 'Role', '0', '1');
INSERT INTO `hi_language` VALUES ('2200', '0', '用户组', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('2201', '0', '用户组名', null, 'SecurityGroup', '0', '1');
INSERT INTO `hi_language` VALUES ('2202', '0', '显示信息', null, 'SecurityGroup', '0', '1');
INSERT INTO `hi_language` VALUES ('2203', '0', '描述', null, 'SecurityGroup', '0', '1');
INSERT INTO `hi_language` VALUES ('2300', '0', '用户权限', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('2301', '0', '用户', null, 'UserAuthority', '0', '1');
INSERT INTO `hi_language` VALUES ('2302', '0', '权限', null, 'UserAuthority', '0', '1');
INSERT INTO `hi_language` VALUES ('2303', '0', '部门', null, 'UserAuthority', '0', '1');
INSERT INTO `hi_language` VALUES ('2304', '0', '范围', null, 'UserAuthority', '0', '1');
INSERT INTO `hi_language` VALUES ('2400', '0', '用户角色', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('2401', '0', '用户', null, 'UserRole', '0', '1');
INSERT INTO `hi_language` VALUES ('2402', '0', '角色名称', null, 'UserRole', '0', '1');
INSERT INTO `hi_language` VALUES ('2500', '0', '用户与组', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('2501', '0', '用户', null, 'UserGroup', '0', '1');
INSERT INTO `hi_language` VALUES ('2502', '0', '角色名称', null, 'UserGroup', '0', '1');
INSERT INTO `hi_language` VALUES ('2600', '0', '角色权限', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('2601', '0', '角色名称', null, 'RoleAuthority', '0', '1');
INSERT INTO `hi_language` VALUES ('2602', '0', '用户', null, 'RoleAuthority', '0', '1');
INSERT INTO `hi_language` VALUES ('2603', '0', '部门', null, 'RoleAuthority', '0', '1');
INSERT INTO `hi_language` VALUES ('2604', '0', '范围', null, 'RoleAuthority', '0', '1');
INSERT INTO `hi_language` VALUES ('2700', '0', '组与角色', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('2701', '0', '用户组', null, 'GroupRole', '0', '1');
INSERT INTO `hi_language` VALUES ('2702', '0', '角色名称', null, 'GroupRole', '0', '1');
INSERT INTO `hi_language` VALUES ('2800', '0', '权限资源', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('2801', '0', '权限名称', null, 'PrivilegeResource', '0', '1');
INSERT INTO `hi_language` VALUES ('2802', '0', '业务层', null, 'PrivilegeResource', '0', '1');
INSERT INTO `hi_language` VALUES ('2900', '0', '权限范围', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('2901', '0', '用户级', null, 'securityScope', '0', '1');
INSERT INTO `hi_language` VALUES ('2902', '0', '当前部门级', null, 'securityScope', '0', '1');
INSERT INTO `hi_language` VALUES ('2903', '0', '部门级', null, 'securityScope', '0', '1');
INSERT INTO `hi_language` VALUES ('2904', '0', '部门及子部门级', null, 'securityScope', '0', '1');
INSERT INTO `hi_language` VALUES ('3000', '0', '枚举实体', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('3001', '0', '枚举名称', null, 'Enumeration', '0', '1');
INSERT INTO `hi_language` VALUES ('3002', '0', '显示信息', null, 'Enumeration', '0', '1');
INSERT INTO `hi_language` VALUES ('3003', '0', '描述', null, 'Enumeration', '0', '1');
INSERT INTO `hi_language` VALUES ('3100', '0', '枚举值', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('3101', '0', '枚举值名称', null, 'EnumerationValue', '0', '1');
INSERT INTO `hi_language` VALUES ('3102', '0', '显示信息', null, 'EnumerationValue', '0', '1');
INSERT INTO `hi_language` VALUES ('3103', '0', '描述', null, 'EnumerationValue', '0', '1');
INSERT INTO `hi_language` VALUES ('3104', '0', '枚举值编号', null, 'EnumerationValue', '0', '1');
INSERT INTO `hi_language` VALUES ('3200', '0', '是否', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('3201', '0', '是', null, 'yesNo', '0', '1');
INSERT INTO `hi_language` VALUES ('4000', '0', '工作项定义', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('4001', '0', '工作名称', null, 'JobDetailDef', '0', '1');
INSERT INTO `hi_language` VALUES ('4002', '0', '类全名', null, 'JobDetailDef', '0', '1');
INSERT INTO `hi_language` VALUES ('4003', '0', '工作描述', null, 'JobDetailDef', '0', '1');
INSERT INTO `hi_language` VALUES ('4100', '0', '触发器', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('4101', '0', '触发名称', null, 'TriggerDef', '0', '1');
INSERT INTO `hi_language` VALUES ('4102', '0', '工作项', null, 'TriggerDef', '0', '1');
INSERT INTO `hi_language` VALUES ('4103', '0', '优先级', null, 'TriggerDef', '0', '1');
INSERT INTO `hi_language` VALUES ('4104', '0', '开始时间', null, 'TriggerDef', '0', '1');
INSERT INTO `hi_language` VALUES ('4105', '0', '截止时间', null, 'TriggerDef', '0', '1');
INSERT INTO `hi_language` VALUES ('4106', '0', '表达式', null, 'TriggerDef', '0', '1');
INSERT INTO `hi_language` VALUES ('4107', '0', '激活', null, 'TriggerDef', '0', '1');
INSERT INTO `hi_language` VALUES ('5000', '0', '应用配置', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('5001', '0', '组名', null, 'AppSetting', '0', '1');
INSERT INTO `hi_language` VALUES ('5002', '0', '配置名', null, 'AppSetting', '0', '1');
INSERT INTO `hi_language` VALUES ('5003', '0', '配置值', null, 'AppSetting', '0', '1');
INSERT INTO `hi_language` VALUES ('5100', '0', '消息', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('5101', '0', '收信人', null, 'Message', '0', '1');
INSERT INTO `hi_language` VALUES ('5102', '0', '发信人', null, 'Message', '0', '1');
INSERT INTO `hi_language` VALUES ('5103', '0', '消息类型', null, 'Message', '0', '1');
INSERT INTO `hi_language` VALUES ('5104', '0', '创建时间', null, 'Message', '0', '1');
INSERT INTO `hi_language` VALUES ('5105', '0', '发送时间', null, 'Message', '0', '1');
INSERT INTO `hi_language` VALUES ('5106', '0', '已发送', null, 'Message', '0', '1');
INSERT INTO `hi_language` VALUES ('5107', '0', '描述', null, 'Message', '0', '1');
INSERT INTO `hi_language` VALUES ('5200', '0', '消息参数', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('5201', '0', '参数键', null, 'MessageParameter', '0', '1');
INSERT INTO `hi_language` VALUES ('5202', '0', '参数值', null, 'MessageParameter', '0', '1');
INSERT INTO `hi_language` VALUES ('5300', '0', '消息类型', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('5301', '0', '手机短信', null, 'messageType', '0', '1');
INSERT INTO `hi_language` VALUES ('5302', '0', '内部邮件', null, 'messageType', '0', '1');
INSERT INTO `hi_language` VALUES ('5303', '0', '外部邮件', null, 'messageType', '0', '1');
INSERT INTO `hi_language` VALUES ('5304', '0', '短消息', null, 'messageType', '0', '1');
INSERT INTO `hi_language` VALUES ('5305', '0', 'QQ', null, 'messageType', '0', '1');
INSERT INTO `hi_language` VALUES ('5306', '0', 'MSN', null, 'messageType', '0', '1');
INSERT INTO `hi_language` VALUES ('5400', '0', '系统日志', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('5401', '0', '操作人', null, 'HiLog', '0', '1');
INSERT INTO `hi_language` VALUES ('5402', '0', '操作时间', null, 'HiLog', '0', '1');
INSERT INTO `hi_language` VALUES ('5403', '0', '动作', null, 'HiLog', '0', '1');
INSERT INTO `hi_language` VALUES ('6000', '0', '附件', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('6001', '0', '文件名', null, 'Attachment', '0', '1');
INSERT INTO `hi_language` VALUES ('6002', '0', '文件类型', null, 'Attachment', '0', '1');
INSERT INTO `hi_language` VALUES ('6003', '0', '文件大小', null, 'Attachment', '0', '1');
INSERT INTO `hi_language` VALUES ('6004', '0', '附件类型', null, 'Attachment', '0', '1');
INSERT INTO `hi_language` VALUES ('6005', '0', '附件', null, 'Attachment', '0', '1');
INSERT INTO `hi_language` VALUES ('10000', '0', '菜单项', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('10001', '0', '菜单名称', null, 'Menu', '0', '1');
INSERT INTO `hi_language` VALUES ('10002', '0', '描述', null, 'Menu', '0', '1');
INSERT INTO `hi_language` VALUES ('10003', '0', '父菜单项', null, 'Menu', '0', '1');
INSERT INTO `hi_language` VALUES ('10004', '0', '序列', null, 'Menu', '0', '1');
INSERT INTO `hi_language` VALUES ('10100', '0', '菜单链接', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('10101', '0', '菜单URL', null, 'MenuLink', '0', '1');
INSERT INTO `hi_language` VALUES ('10102', '0', '描述', null, 'MenuLink', '0', '1');
INSERT INTO `hi_language` VALUES ('10103', '0', '权限名称', null, 'MenuLink', '0', '1');
INSERT INTO `hi_language` VALUES ('10104', '0', '序列', null, 'MenuLink', '0', '1');
INSERT INTO `hi_language` VALUES ('10105', '0', '菜单项名称', null, 'MenuLink', '0', '1');
INSERT INTO `hi_language` VALUES ('12000', '0', 'Excel报表设计', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('12001', '0', '报表名称', null, 'ExcelReportDesign', '0', '1');
INSERT INTO `hi_language` VALUES ('12002', '0', '报表编号', null, 'ExcelReportDesign', '0', '1');
INSERT INTO `hi_language` VALUES ('12003', '0', '创建时间', null, 'ExcelReportDesign', '0', '1');
INSERT INTO `hi_language` VALUES ('12004', '0', '激活', null, 'ExcelReportDesign', '0', '1');
INSERT INTO `hi_language` VALUES ('12005', '0', '描述', null, 'ExcelReportDesign', '0', '1');
INSERT INTO `hi_language` VALUES ('12100', '0', '工作表', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('12101', '0', '工作表名称', null, 'ExcelSheet', '0', '1');
INSERT INTO `hi_language` VALUES ('12102', '0', '序列', null, 'ExcelSheet', '0', '1');
INSERT INTO `hi_language` VALUES ('12103', '0', '描述', null, 'ExcelSheet', '0', '1');
INSERT INTO `hi_language` VALUES ('12200', '0', '单元格', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('12201', '0', '列', null, 'ExcelCell', '0', '1');
INSERT INTO `hi_language` VALUES ('12202', '0', '行', null, 'ExcelCell', '0', '1');
INSERT INTO `hi_language` VALUES ('12203', '0', '变量名', null, 'ExcelCell', '0', '1');
INSERT INTO `hi_language` VALUES ('12204', '0', '常量', null, 'ExcelCell', '0', '1');
INSERT INTO `hi_language` VALUES ('12205', '0', '枚举类型', null, 'ExcelCell', '0', '1');
INSERT INTO `hi_language` VALUES ('12206', '0', '数据类型', null, 'ExcelCell', '0', '1');
INSERT INTO `hi_language` VALUES ('12207', '0', '伸展方式', null, 'ExcelCell', '0', '1');
INSERT INTO `hi_language` VALUES ('12208', '0', '条件', null, 'ExcelCell', '0', '1');
INSERT INTO `hi_language` VALUES ('12209', '0', '描述', null, 'ExcelCell', '0', '1');
INSERT INTO `hi_language` VALUES ('12300', '0', '数据类型', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('12301', '0', '元素', null, 'reportDataType', '0', '1');
INSERT INTO `hi_language` VALUES ('12302', '0', '集合', null, 'reportDataType', '0', '1');
INSERT INTO `hi_language` VALUES ('12400', '0', '伸展方式', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('12401', '0', '纵向', null, 'stretchingType', '0', '1');
INSERT INTO `hi_language` VALUES ('42100', '0', '多语言信息', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('42101', '0', 'Key值', null, 'Language', '0', '1');
INSERT INTO `hi_language` VALUES ('42102', '0', '服务', null, 'Language', '0', '1');
INSERT INTO `hi_language` VALUES ('42103', '0', '实体', null, 'Language', '0', '1');
INSERT INTO `hi_language` VALUES ('42200', '0', '语言编码', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('42201', '0', '语言编码', null, 'LanguageCode', '0', '1');
INSERT INTO `hi_language` VALUES ('42202', '0', '描述', null, 'LanguageCode', '0', '1');
INSERT INTO `hi_language` VALUES ('42300', '0', '多语言值', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('42301', '0', '语言代码', null, 'LanguageStr', '0', '1');
INSERT INTO `hi_language` VALUES ('42302', '0', '值', null, 'LanguageStr', '0', '1');
INSERT INTO `hi_language` VALUES ('42400', '0', '时区', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('42401', '0', '时区值', null, 'Timezone', '0', '1');
INSERT INTO `hi_language` VALUES ('100100', '0', '类型', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('100101', '0', '好友', null, 'sendType', '0', '1');
INSERT INTO `hi_language` VALUES ('100102', '0', '微信群', null, 'sendType', '0', '1');
INSERT INTO `hi_language` VALUES ('100200', '0', '好友标记', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('100201', '0', '正常', null, 'friendFlag', '0', '1');
INSERT INTO `hi_language` VALUES ('100202', '0', '僵尸粉', null, 'friendFlag', '0', '1');
INSERT INTO `hi_language` VALUES ('100300', '0', '内容管理', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('100301', '0', '内容', null, 'Content', '0', '1');
INSERT INTO `hi_language` VALUES ('100302', '0', '图片', null, 'Content', '0', '1');
INSERT INTO `hi_language` VALUES ('100303', '0', '发送类型', null, 'Content', '0', '1');
INSERT INTO `hi_language` VALUES ('100304', '0', '好友标记', null, 'Content', '0', '1');
INSERT INTO `hi_language` VALUES ('100305', '0', '性别', null, 'Content', '0', '1');
INSERT INTO `hi_language` VALUES ('100306', '0', '创建时间', null, 'Content', '0', '1');
INSERT INTO `hi_language` VALUES ('100400', '0', '性别', null, null, '0', '1');
INSERT INTO `hi_language` VALUES ('100401', '0', '全部', null, 'sexEnum', '0', '1');
INSERT INTO `hi_language` VALUES ('100402', '0', '男', null, 'sexEnum', '0', '1');

-- ----------------------------
-- Table structure for `hi_languagecode`
-- ----------------------------
DROP TABLE IF EXISTS `hi_languagecode`;
CREATE TABLE `hi_languagecode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `languageCode` varchar(50) CHARACTER SET utf8 NOT NULL,
  `description` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_languagecode
-- ----------------------------
INSERT INTO `hi_languagecode` VALUES ('1', '2', 'zh_CN', '中国汉语', '1');
INSERT INTO `hi_languagecode` VALUES ('2', '0', 'en_US', '美国英语', '1');

-- ----------------------------
-- Table structure for `hi_languagestr`
-- ----------------------------
DROP TABLE IF EXISTS `hi_languagestr`;
CREATE TABLE `hi_languagestr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `language` int(11) DEFAULT NULL,
  `languageCode` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_languagestr
-- ----------------------------
INSERT INTO `hi_languagestr` VALUES ('2', '8', '3', 'zh_CN', '查询条件', '1');
INSERT INTO `hi_languagestr` VALUES ('3', '0', '4', 'zh_CN', '新建', '1');
INSERT INTO `hi_languagestr` VALUES ('4', '0', '5', 'zh_CN', '查询', '1');
INSERT INTO `hi_languagestr` VALUES ('5', '0', '6', 'zh_CN', '重置', '1');
INSERT INTO `hi_languagestr` VALUES ('7', '1', '8', 'zh_CN', '{1}列表', '1');
INSERT INTO `hi_languagestr` VALUES ('8', '1', '9', 'zh_CN', '操作', '1');
INSERT INTO `hi_languagestr` VALUES ('9', '0', '10', 'zh_CN', '查找带回', '1');
INSERT INTO `hi_languagestr` VALUES ('10', '0', '11', 'zh_CN', '删除{1}', '1');
INSERT INTO `hi_languagestr` VALUES ('11', '0', '12', 'zh_CN', '查看{1}', '1');
INSERT INTO `hi_languagestr` VALUES ('12', '0', '13', 'zh_CN', '编辑{1}', '1');
INSERT INTO `hi_languagestr` VALUES ('13', '0', '14', 'zh_CN', '取消全选', '1');
INSERT INTO `hi_languagestr` VALUES ('14', '0', '15', 'zh_CN', '全选', '1');
INSERT INTO `hi_languagestr` VALUES ('15', '0', '16', 'zh_CN', '批量删除', '1');
INSERT INTO `hi_languagestr` VALUES ('16', '1', '17', 'zh_CN', '{1}编辑页面', '1');
INSERT INTO `hi_languagestr` VALUES ('17', '0', '18', 'zh_CN', '保存', '1');
INSERT INTO `hi_languagestr` VALUES ('18', '0', '19', 'zh_CN', '关闭', '1');
INSERT INTO `hi_languagestr` VALUES ('19', '1', '20', 'zh_CN', '{1}查看页面', '1');
INSERT INTO `hi_languagestr` VALUES ('27', '0', '22', 'zh_CN', '共{1}条', '1');
INSERT INTO `hi_languagestr` VALUES ('29', '0', '23', 'zh_CN', '共{1}页', '1');
INSERT INTO `hi_languagestr` VALUES ('31', '0', '24', 'zh_CN', '尾页', '1');
INSERT INTO `hi_languagestr` VALUES ('33', '0', '25', 'zh_CN', '下一页', '1');
INSERT INTO `hi_languagestr` VALUES ('35', '0', '26', 'zh_CN', '上一页', '1');
INSERT INTO `hi_languagestr` VALUES ('37', '0', '27', 'zh_CN', '首页', '1');
INSERT INTO `hi_languagestr` VALUES ('39', '0', '28', 'zh_CN', '到', '1');
INSERT INTO `hi_languagestr` VALUES ('41', '0', '29', 'zh_CN', '页', '1');
INSERT INTO `hi_languagestr` VALUES ('43', '0', '30', 'zh_CN', '跳转', '1');
INSERT INTO `hi_languagestr` VALUES ('45', '0', '31', 'zh_CN', '全部', '1');
INSERT INTO `hi_languagestr` VALUES ('47', '0', '32', 'zh_CN', '操作符', '1');
INSERT INTO `hi_languagestr` VALUES ('49', '0', '33', 'zh_CN', '小于', '1');
INSERT INTO `hi_languagestr` VALUES ('51', '0', '34', 'zh_CN', '大于', '1');
INSERT INTO `hi_languagestr` VALUES ('53', '0', '35', 'zh_CN', '等于', '1');
INSERT INTO `hi_languagestr` VALUES ('55', '0', '36', 'zh_CN', '小于等于', '1');
INSERT INTO `hi_languagestr` VALUES ('57', '0', '37', 'zh_CN', '大于等于', '1');
INSERT INTO `hi_languagestr` VALUES ('59', '0', '38', 'zh_CN', '请选择需要上传的附件', '1');
INSERT INTO `hi_languagestr` VALUES ('60', '0', '39', 'zh_CN', '上传', '1');
INSERT INTO `hi_languagestr` VALUES ('61', '0', '40', 'zh_CN', '请先选择需要上传的文件！', '1');
INSERT INTO `hi_languagestr` VALUES ('62', '0', '41', 'zh_CN', '权限管理', '1');
INSERT INTO `hi_languagestr` VALUES ('63', '0', '42', 'zh_CN', '错误详细信息', '1');
INSERT INTO `hi_languagestr` VALUES ('64', '0', '43', 'zh_CN', '您没有操作此功能的权限', '1');
INSERT INTO `hi_languagestr` VALUES ('65', '0', '44', 'zh_CN', '返回', '1');
INSERT INTO `hi_languagestr` VALUES ('66', '0', '45', 'zh_CN', '在系统中没有与action:{1}  对应的报表设计,如果存在请确认是否处于激活状态', '1');
INSERT INTO `hi_languagestr` VALUES ('67', '0', '46', 'zh_CN', '上传文件过大！最大能上传{1}MB 的附件.', '1');
INSERT INTO `hi_languagestr` VALUES ('68', '0', '47', 'zh_CN', '{1}加载失败!', '1');
INSERT INTO `hi_languagestr` VALUES ('69', '0', '48', 'zh_CN', '您是一般用户,不能分派角色', '1');
INSERT INTO `hi_languagestr` VALUES ('70', '0', '49', 'zh_CN', '您的用户类型是管理员,必须是您自己创建的角色才可以为该角色分派用户', '1');
INSERT INTO `hi_languagestr` VALUES ('71', '0', '50', 'zh_CN', '您选择的用户：{1}是超级管理员,不需要为超级管理员授权', '1');
INSERT INTO `hi_languagestr` VALUES ('72', '0', '51', 'zh_CN', '您是一般用户,不能删除建角色', '1');
INSERT INTO `hi_languagestr` VALUES ('73', '0', '52', 'zh_CN', '您的用户类型为管理员,只能删除您自己所创建的角色', '1');
INSERT INTO `hi_languagestr` VALUES ('74', '0', '53', 'zh_CN', '角色名：{1}已经存在，不能有重复的角色名！', '1');
INSERT INTO `hi_languagestr` VALUES ('75', '1', '54', 'zh_CN', '系统无法识别您的用户类型!', '1');
INSERT INTO `hi_languagestr` VALUES ('76', '0', '55', 'zh_CN', '您是一般用户,不能修改与创建角色!', '1');
INSERT INTO `hi_languagestr` VALUES ('77', '0', '56', 'zh_CN', '您的用户是管理员,所以只能编辑您自己创建的角色!', '1');
INSERT INTO `hi_languagestr` VALUES ('78', '0', '57', 'zh_CN', '该页面中的数据已经被改写，请重新刷新页面后重新编辑该记录!', '1');
INSERT INTO `hi_languagestr` VALUES ('79', '0', '58', 'zh_CN', '在给action对象赋值时出错：{1}属性！', '1');
INSERT INTO `hi_languagestr` VALUES ('80', '0', '59', 'zh_CN', '发送邮件失败', '1');
INSERT INTO `hi_languagestr` VALUES ('82', '0', '61', 'zh_CN', '请选择要授权的用户', '1');
INSERT INTO `hi_languagestr` VALUES ('83', '0', '62', 'zh_CN', 'HI平台', '1');
INSERT INTO `hi_languagestr` VALUES ('84', '0', '63', 'zh_CN', '用户名', '1');
INSERT INTO `hi_languagestr` VALUES ('85', '0', '64', 'zh_CN', '密　码', '1');
INSERT INTO `hi_languagestr` VALUES ('86', '0', '65', 'zh_CN', '验证码', '1');
INSERT INTO `hi_languagestr` VALUES ('87', '0', '66', 'zh_CN', '你没有登陆成功，请再试一次。', '1');
INSERT INTO `hi_languagestr` VALUES ('88', '0', '67', 'zh_CN', '可能的原因: 您输入的用户名或密码错误！', '1');
INSERT INTO `hi_languagestr` VALUES ('89', '0', '68', 'zh_CN', '欢迎', '1');
INSERT INTO `hi_languagestr` VALUES ('90', '0', '69', 'zh_CN', '登陆', '1');

-- ----------------------------
-- Table structure for `hi_log`
-- ----------------------------
DROP TABLE IF EXISTS `hi_log`;
CREATE TABLE `hi_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `operator` int(11) DEFAULT NULL,
  `operateDate` datetime DEFAULT NULL,
  `action` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `actionContext` varchar(2000) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_log
-- ----------------------------

-- ----------------------------
-- Table structure for `hi_message`
-- ----------------------------
DROP TABLE IF EXISTS `hi_message`;
CREATE TABLE `hi_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `receivers` varchar(2000) CHARACTER SET utf8 NOT NULL,
  `receiverNames` varchar(2000) CHARACTER SET utf8 DEFAULT NULL,
  `sender` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `messageType` int(11) DEFAULT NULL,
  `messageText` varchar(3000) CHARACTER SET utf8 DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `sendDate` datetime DEFAULT NULL,
  `isSent` int(11) DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_message
-- ----------------------------

-- ----------------------------
-- Table structure for `hi_messageparameter`
-- ----------------------------
DROP TABLE IF EXISTS `hi_messageparameter`;
CREATE TABLE `hi_messageparameter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `parameterKey` varchar(50) CHARACTER SET utf8 NOT NULL,
  `parameterValue` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `message` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_messageparameter
-- ----------------------------

-- ----------------------------
-- Table structure for `hi_org`
-- ----------------------------
DROP TABLE IF EXISTS `hi_org`;
CREATE TABLE `hi_org` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `orgName` varchar(50) CHARACTER SET utf8 NOT NULL,
  `orgNum` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `manager` int(11) DEFAULT NULL,
  `parentOrg` int(11) DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_org
-- ----------------------------

-- ----------------------------
-- Table structure for `hi_privilegeresource`
-- ----------------------------
DROP TABLE IF EXISTS `hi_privilegeresource`;
CREATE TABLE `hi_privilegeresource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `authorityName` varchar(200) CHARACTER SET utf8 NOT NULL,
  `viewLayer` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `veiwExtAuthNames` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `businessLayer` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `bizExtAuthNames` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100305 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_privilegeresource
-- ----------------------------
INSERT INTO `hi_privilegeresource` VALUES ('1000', '0', 'HIORG_LIST', '/hiOrgList.action', null, 'org.hi.base.organization.service.HiOrgManager.getSecurityHiOrgList', 'HIORG_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('1001', '0', 'HIORG_VIEW', '/hiOrgView.action', null, 'org.hi.base.organization.service.HiOrgManager.getSecurityHiOrgById', null);
INSERT INTO `hi_privilegeresource` VALUES ('1002', '0', 'HIORG_SAVE', '/hiOrgSave.action', null, 'org.hi.base.organization.service.HiOrgManager.saveSecurityHiOrg', null);
INSERT INTO `hi_privilegeresource` VALUES ('1003', '0', 'HIORG_DEL', '/hiOrgRemove.action', null, 'org.hi.base.organization.service.HiOrgManager.removeSecurityHiOrgById', null);
INSERT INTO `hi_privilegeresource` VALUES ('1004', '0', 'HIORG_LOOKUP', '/hiOrgLookup.action', null, null, null);
INSERT INTO `hi_privilegeresource` VALUES ('1100', '0', 'HIUSER_LIST', '/hiUserList.action', null, 'org.hi.base.organization.service.HiUserManager.getSecurityHiUserList', 'HIUSER_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('1101', '0', 'HIUSER_VIEW', '/hiUserView.action', null, 'org.hi.base.organization.service.HiUserManager.getSecurityHiUserById', null);
INSERT INTO `hi_privilegeresource` VALUES ('1102', '0', 'HIUSER_SAVE', '/hiUserSave.action', null, 'org.hi.base.organization.service.HiUserManager.saveSecurityHiUser', null);
INSERT INTO `hi_privilegeresource` VALUES ('1103', '0', 'HIUSER_DEL', '/hiUserRemove.action', null, 'org.hi.base.organization.service.HiUserManager.removeSecurityHiUserById', null);
INSERT INTO `hi_privilegeresource` VALUES ('1104', '0', 'HIUSER_LOOKUP', '/hiUserLookup.action', null, null, null);
INSERT INTO `hi_privilegeresource` VALUES ('2100', '0', 'ROLE_LIST', '/roleList.action', null, 'org.hi.framework.security.service.RoleManager.getSecurityRoleList', 'ROLE_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('2101', '0', 'ROLE_VIEW', '/roleView.action', null, 'org.hi.framework.security.service.RoleManager.getSecurityRoleById', null);
INSERT INTO `hi_privilegeresource` VALUES ('2102', '0', 'ROLE_SAVE', '/roleSave.action', null, 'org.hi.framework.security.service.RoleManager.saveSecurityRole', null);
INSERT INTO `hi_privilegeresource` VALUES ('2103', '0', 'ROLE_DEL', '/roleRemove.action', null, 'org.hi.framework.security.service.RoleManager.removeSecurityRoleById', null);
INSERT INTO `hi_privilegeresource` VALUES ('2104', '0', 'ROLE_LOOKUP', '/roleLookup.action', null, null, null);
INSERT INTO `hi_privilegeresource` VALUES ('2300', '0', 'USERAUTHORITY_LIST', '/userAuthorityList.action', null, 'org.hi.framework.security.service.UserAuthorityManager.getSecurityUserAuthorityList', 'USERAUTHORITY_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('2301', '0', 'USERAUTHORITY_VIEW', '/userAuthorityView.action', null, 'org.hi.framework.security.service.UserAuthorityManager.getSecurityUserAuthorityById', null);
INSERT INTO `hi_privilegeresource` VALUES ('2302', '0', 'USERAUTHORITY_SAVE', '/userAuthoritySave.action', null, 'org.hi.framework.security.service.UserAuthorityManager.saveSecurityUserAuthority', null);
INSERT INTO `hi_privilegeresource` VALUES ('2303', '0', 'USERAUTHORITY_DEL', '/userAuthorityRemove.action', null, 'org.hi.framework.security.service.UserAuthorityManager.removeSecurityUserAuthorityById', null);
INSERT INTO `hi_privilegeresource` VALUES ('2304', '0', 'USERAUTHORITY_LOOKUP', '/userAuthorityLookup.action', null, null, null);
INSERT INTO `hi_privilegeresource` VALUES ('2800', '0', 'PRIVILEGERESOURCE_LIST', '/privilegeResourceList.action', null, 'org.hi.framework.security.service.PrivilegeResourceManager.getSecurityPrivilegeResourceList', 'PRIVILEGERESOURCE_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('2801', '0', 'PRIVILEGERESOURCE_VIEW', '/privilegeResourceView.action', null, 'org.hi.framework.security.service.PrivilegeResourceManager.getSecurityPrivilegeResourceById', null);
INSERT INTO `hi_privilegeresource` VALUES ('2802', '0', 'PRIVILEGERESOURCE_SAVE', '/privilegeResourceSave.action', null, 'org.hi.framework.security.service.PrivilegeResourceManager.saveSecurityPrivilegeResource', null);
INSERT INTO `hi_privilegeresource` VALUES ('2803', '0', 'PRIVILEGERESOURCE_DEL', '/privilegeResourceRemove.action', null, 'org.hi.framework.security.service.PrivilegeResourceManager.removeSecurityPrivilegeResourceById', null);
INSERT INTO `hi_privilegeresource` VALUES ('2804', '0', 'PRIVILEGERESOURCE_LOOKUP', '/privilegeResourceLookup.action', null, null, null);
INSERT INTO `hi_privilegeresource` VALUES ('3000', '0', 'ENUMERATION_LIST', '/enumerationList.action', null, 'org.hi.base.enumeration.service.EnumerationManager.getSecurityEnumerationList', 'ENUMERATION_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('3001', '0', 'ENUMERATION_VIEW', '/enumerationView.action', null, 'org.hi.base.enumeration.service.EnumerationManager.getSecurityEnumerationById', null);
INSERT INTO `hi_privilegeresource` VALUES ('3002', '0', 'ENUMERATION_SAVE', '/enumerationSave.action', null, 'org.hi.base.enumeration.service.EnumerationManager.saveSecurityEnumeration', null);
INSERT INTO `hi_privilegeresource` VALUES ('3003', '0', 'ENUMERATION_DEL', '/enumerationRemove.action', null, 'org.hi.base.enumeration.service.EnumerationManager.removeSecurityEnumerationById', null);
INSERT INTO `hi_privilegeresource` VALUES ('3004', '0', 'ENUMERATION_LOOKUP', '/enumerationLookup.action', null, null, null);
INSERT INTO `hi_privilegeresource` VALUES ('3100', '0', 'ENUMERATIONVALUE_LIST', '/enumerationValueList.action', null, 'org.hi.base.enumeration.service.EnumerationValueManager.getSecurityEnumerationValueList', 'ENUMERATIONVALUE_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('3101', '0', 'ENUMERATIONVALUE_VIEW', '/enumerationValueView.action', null, 'org.hi.base.enumeration.service.EnumerationValueManager.getSecurityEnumerationValueById', null);
INSERT INTO `hi_privilegeresource` VALUES ('3102', '0', 'ENUMERATIONVALUE_SAVE', '/enumerationValueSave.action', null, 'org.hi.base.enumeration.service.EnumerationValueManager.saveSecurityEnumerationValue', null);
INSERT INTO `hi_privilegeresource` VALUES ('3103', '0', 'ENUMERATIONVALUE_DEL', '/enumerationValueRemove.action', null, 'org.hi.base.enumeration.service.EnumerationValueManager.removeSecurityEnumerationValueById', null);
INSERT INTO `hi_privilegeresource` VALUES ('3104', '0', 'ENUMERATIONVALUE_LOOKUP', '/enumerationValueLookup.action', null, null, null);
INSERT INTO `hi_privilegeresource` VALUES ('4000', '0', 'JOBDETAILDEF_LIST', '/jobDetailDefList.action', null, 'org.hi.base.schedule.service.JobDetailDefManager.getSecurityJobDetailDefList', 'JOBDETAILDEF_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('4001', '0', 'JOBDETAILDEF_VIEW', '/jobDetailDefView.action', null, 'org.hi.base.schedule.service.JobDetailDefManager.getSecurityJobDetailDefById', null);
INSERT INTO `hi_privilegeresource` VALUES ('4002', '0', 'JOBDETAILDEF_SAVE', '/jobDetailDefSave.action', null, 'org.hi.base.schedule.service.JobDetailDefManager.saveSecurityJobDetailDef', null);
INSERT INTO `hi_privilegeresource` VALUES ('4003', '0', 'JOBDETAILDEF_DEL', '/jobDetailDefRemove.action', null, 'org.hi.base.schedule.service.JobDetailDefManager.removeSecurityJobDetailDefById', null);
INSERT INTO `hi_privilegeresource` VALUES ('4004', '0', 'JOBDETAILDEF_LOOKUP', '/jobDetailDefLookup.action', null, null, null);
INSERT INTO `hi_privilegeresource` VALUES ('4100', '0', 'TRIGGERDEF_LIST', '/triggerDefList.action', null, 'org.hi.base.schedule.service.TriggerDefManager.getSecurityTriggerDefList', 'TRIGGERDEF_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('4101', '0', 'TRIGGERDEF_VIEW', '/triggerDefView.action', null, 'org.hi.base.schedule.service.TriggerDefManager.getSecurityTriggerDefById', null);
INSERT INTO `hi_privilegeresource` VALUES ('4102', '0', 'TRIGGERDEF_SAVE', '/triggerDefSave.action', null, 'org.hi.base.schedule.service.TriggerDefManager.saveSecurityTriggerDef', null);
INSERT INTO `hi_privilegeresource` VALUES ('4103', '0', 'TRIGGERDEF_DEL', '/triggerDefRemove.action', null, 'org.hi.base.schedule.service.TriggerDefManager.removeSecurityTriggerDefById', null);
INSERT INTO `hi_privilegeresource` VALUES ('4104', '0', 'TRIGGERDEF_LOOKUP', '/triggerDefLookup.action', null, null, null);
INSERT INTO `hi_privilegeresource` VALUES ('5000', '0', 'APPSETTING_LIST', '/appSettingList.action', null, 'org.hi.base.sysapp.service.AppSettingManager.getSecurityAppSettingList', 'APPSETTING_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('5001', '0', 'APPSETTING_VIEW', '/appSettingView.action', null, 'org.hi.base.sysapp.service.AppSettingManager.getSecurityAppSettingById', null);
INSERT INTO `hi_privilegeresource` VALUES ('5002', '0', 'APPSETTING_SAVE', '/appSettingSave.action', null, 'org.hi.base.sysapp.service.AppSettingManager.saveSecurityAppSetting', null);
INSERT INTO `hi_privilegeresource` VALUES ('5003', '0', 'APPSETTING_DEL', '/appSettingRemove.action', null, 'org.hi.base.sysapp.service.AppSettingManager.removeSecurityAppSettingById', null);
INSERT INTO `hi_privilegeresource` VALUES ('5004', '0', 'APPSETTING_LOOKUP', '/appSettingLookup.action', null, null, null);
INSERT INTO `hi_privilegeresource` VALUES ('5100', '0', 'MESSAGE_LIST', '/messageList.action', null, 'org.hi.base.sysapp.service.MessageManager.getSecurityMessageList', 'MESSAGE_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('5101', '0', 'MESSAGE_VIEW', '/messageView.action', null, 'org.hi.base.sysapp.service.MessageManager.getSecurityMessageById', null);
INSERT INTO `hi_privilegeresource` VALUES ('5102', '0', 'MESSAGE_SAVE', '/messageSave.action', null, 'org.hi.base.sysapp.service.MessageManager.saveSecurityMessage', null);
INSERT INTO `hi_privilegeresource` VALUES ('5103', '0', 'MESSAGE_DEL', '/messageRemove.action', null, 'org.hi.base.sysapp.service.MessageManager.removeSecurityMessageById', null);
INSERT INTO `hi_privilegeresource` VALUES ('5104', '0', 'MESSAGE_LOOKUP', '/messageLookup.action', null, null, null);
INSERT INTO `hi_privilegeresource` VALUES ('5200', '0', 'MESSAGEPARAMETER_LIST', '/messageParameterList.action', null, 'org.hi.base.sysapp.service.MessageParameterManager.getSecurityMessageParameterList', 'MESSAGEPARAMETER_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('5201', '0', 'MESSAGEPARAMETER_VIEW', '/messageParameterView.action', null, 'org.hi.base.sysapp.service.MessageParameterManager.getSecurityMessageParameterById', null);
INSERT INTO `hi_privilegeresource` VALUES ('5202', '0', 'MESSAGEPARAMETER_SAVE', '/messageParameterSave.action', null, 'org.hi.base.sysapp.service.MessageParameterManager.saveSecurityMessageParameter', null);
INSERT INTO `hi_privilegeresource` VALUES ('5203', '0', 'MESSAGEPARAMETER_DEL', '/messageParameterRemove.action', null, 'org.hi.base.sysapp.service.MessageParameterManager.removeSecurityMessageParameterById', null);
INSERT INTO `hi_privilegeresource` VALUES ('5204', '0', 'MESSAGEPARAMETER_LOOKUP', '/messageParameterLookup.action', null, null, null);
INSERT INTO `hi_privilegeresource` VALUES ('5400', '0', 'HILOG_LIST', '/hiLogList.action', null, 'org.hi.base.sysapp.service.HiLogManager.getSecurityHiLogList', 'HILOG_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('5401', '0', 'HILOG_VIEW', '/hiLogView.action', null, 'org.hi.base.sysapp.service.HiLogManager.getSecurityHiLogById', null);
INSERT INTO `hi_privilegeresource` VALUES ('5403', '0', 'HILOG_DEL', '/hiLogRemove.action', null, 'org.hi.base.sysapp.service.HiLogManager.removeSecurityHiLogById', null);
INSERT INTO `hi_privilegeresource` VALUES ('10000', '0', 'MENU_LIST', '/menuList.action', null, 'org.hi.base.menu.service.MenuManager.getSecurityMenuList', 'MENU_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('10001', '0', 'MENU_VIEW', '/menuView.action', null, 'org.hi.base.menu.service.MenuManager.getSecurityMenuById', null);
INSERT INTO `hi_privilegeresource` VALUES ('10002', '0', 'MENU_SAVE', '/menuSave.action', null, 'org.hi.base.menu.service.MenuManager.saveSecurityMenu', null);
INSERT INTO `hi_privilegeresource` VALUES ('10003', '0', 'MENU_DEL', '/menuRemove.action', null, 'org.hi.base.menu.service.MenuManager.removeSecurityMenuById', null);
INSERT INTO `hi_privilegeresource` VALUES ('10004', '0', 'MENU_LOOKUP', '/menuLookup.action', null, null, null);
INSERT INTO `hi_privilegeresource` VALUES ('10100', '0', 'MENULINK_LIST', '/menuLinkList.action', null, 'org.hi.base.menu.service.MenuLinkManager.getSecurityMenuLinkList', 'MENULINK_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('10101', '0', 'MENULINK_VIEW', '/menuLinkView.action', null, 'org.hi.base.menu.service.MenuLinkManager.getSecurityMenuLinkById', null);
INSERT INTO `hi_privilegeresource` VALUES ('10102', '0', 'MENULINK_SAVE', '/menuLinkSave.action', null, 'org.hi.base.menu.service.MenuLinkManager.saveSecurityMenuLink', null);
INSERT INTO `hi_privilegeresource` VALUES ('10103', '0', 'MENULINK_DEL', '/menuLinkRemove.action', null, 'org.hi.base.menu.service.MenuLinkManager.removeSecurityMenuLinkById', null);
INSERT INTO `hi_privilegeresource` VALUES ('10104', '0', 'MENULINK_LOOKUP', '/menuLinkLookup.action', null, null, null);
INSERT INTO `hi_privilegeresource` VALUES ('12000', '0', 'EXCELREPORTDESIGN_LIST', '/excelReportDesignList.action', null, 'org.hi.base.report.excel.service.ExcelReportDesignManager.getSecurityExcelReportDesignList', 'EXCELREPORTDESIGN_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('12001', '0', 'EXCELREPORTDESIGN_VIEW', '/excelReportDesignView.action', null, 'org.hi.base.report.excel.service.ExcelReportDesignManager.getSecurityExcelReportDesignById', null);
INSERT INTO `hi_privilegeresource` VALUES ('12002', '0', 'EXCELREPORTDESIGN_SAVE', '/excelReportDesignSave.action', null, 'org.hi.base.report.excel.service.ExcelReportDesignManager.saveSecurityExcelReportDesign', null);
INSERT INTO `hi_privilegeresource` VALUES ('12003', '0', 'EXCELREPORTDESIGN_DEL', '/excelReportDesignRemove.action', null, 'org.hi.base.report.excel.service.ExcelReportDesignManager.removeSecurityExcelReportDesignById', null);
INSERT INTO `hi_privilegeresource` VALUES ('12004', '0', 'EXCELREPORTDESIGN_LOOKUP', '/excelReportDesignLookup.action', null, null, null);
INSERT INTO `hi_privilegeresource` VALUES ('12100', '0', 'EXCELSHEET_LIST', '/excelSheetList.action', null, 'org.hi.base.report.excel.service.ExcelSheetManager.getSecurityExcelSheetList', 'EXCELSHEET_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('12101', '0', 'EXCELSHEET_VIEW', '/excelSheetView.action', null, 'org.hi.base.report.excel.service.ExcelSheetManager.getSecurityExcelSheetById', null);
INSERT INTO `hi_privilegeresource` VALUES ('12102', '0', 'EXCELSHEET_SAVE', '/excelSheetSave.action', null, 'org.hi.base.report.excel.service.ExcelSheetManager.saveSecurityExcelSheet', null);
INSERT INTO `hi_privilegeresource` VALUES ('12103', '0', 'EXCELSHEET_DEL', '/excelSheetRemove.action', null, 'org.hi.base.report.excel.service.ExcelSheetManager.removeSecurityExcelSheetById', null);
INSERT INTO `hi_privilegeresource` VALUES ('12104', '0', 'EXCELSHEET_LOOKUP', '/excelSheetLookup.action', null, null, null);
INSERT INTO `hi_privilegeresource` VALUES ('12200', '0', 'EXCELCELL_LIST', '/excelCellList.action', null, 'org.hi.base.report.excel.service.ExcelCellManager.getSecurityExcelCellList', 'EXCELCELL_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('12201', '0', 'EXCELCELL_VIEW', '/excelCellView.action', null, 'org.hi.base.report.excel.service.ExcelCellManager.getSecurityExcelCellById', null);
INSERT INTO `hi_privilegeresource` VALUES ('12202', '0', 'EXCELCELL_SAVE', '/excelCellSave.action', null, 'org.hi.base.report.excel.service.ExcelCellManager.saveSecurityExcelCell', null);
INSERT INTO `hi_privilegeresource` VALUES ('12203', '0', 'EXCELCELL_DEL', '/excelCellRemove.action', null, 'org.hi.base.report.excel.service.ExcelCellManager.removeSecurityExcelCellById', null);
INSERT INTO `hi_privilegeresource` VALUES ('12204', '0', 'EXCELCELL_LOOKUP', '/excelCellLookup.action', null, null, null);
INSERT INTO `hi_privilegeresource` VALUES ('100300', '0', 'CONTENT_LIST', '/contentList.action', null, 'org.hi.cms.service.ContentManager.getSecurityContentList', 'CONTENT_LOOKUP');
INSERT INTO `hi_privilegeresource` VALUES ('100301', '0', 'CONTENT_VIEW', '/contentView.action', null, 'org.hi.cms.service.ContentManager.getSecurityContentById', null);
INSERT INTO `hi_privilegeresource` VALUES ('100302', '0', 'CONTENT_SAVE', '/contentSave.action', null, 'org.hi.cms.service.ContentManager.saveSecurityContent', null);
INSERT INTO `hi_privilegeresource` VALUES ('100303', '0', 'CONTENT_DEL', '/contentRemove.action', null, 'org.hi.cms.service.ContentManager.removeSecurityContentById', null);
INSERT INTO `hi_privilegeresource` VALUES ('100304', '0', 'CONTENT_LOOKUP', '/contentLookup.action', null, null, null);

-- ----------------------------
-- Table structure for `hi_role`
-- ----------------------------
DROP TABLE IF EXISTS `hi_role`;
CREATE TABLE `hi_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `roleName` varchar(200) CHARACTER SET utf8 NOT NULL,
  `displayRef` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_role
-- ----------------------------

-- ----------------------------
-- Table structure for `hi_roleauthority`
-- ----------------------------
DROP TABLE IF EXISTS `hi_roleauthority`;
CREATE TABLE `hi_roleauthority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `role` int(11) DEFAULT NULL,
  `authority` int(11) DEFAULT NULL,
  `org` int(11) DEFAULT NULL,
  `scope` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_roleauthority
-- ----------------------------

-- ----------------------------
-- Table structure for `hi_securitygroup`
-- ----------------------------
DROP TABLE IF EXISTS `hi_securitygroup`;
CREATE TABLE `hi_securitygroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `groupName` varchar(200) CHARACTER SET utf8 NOT NULL,
  `displayRef` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_securitygroup
-- ----------------------------

-- ----------------------------
-- Table structure for `hi_timezone`
-- ----------------------------
DROP TABLE IF EXISTS `hi_timezone`;
CREATE TABLE `hi_timezone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `timezone` int(11) NOT NULL,
  `description` varchar(3000) CHARACTER SET utf8 NOT NULL,
  `creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_timezone
-- ----------------------------

-- ----------------------------
-- Table structure for `hi_triggerdef`
-- ----------------------------
DROP TABLE IF EXISTS `hi_triggerdef`;
CREATE TABLE `hi_triggerdef` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `triggerName` varchar(80) CHARACTER SET utf8 NOT NULL,
  `triggerGroup` varchar(80) CHARACTER SET utf8 DEFAULT NULL,
  `jobDetail` int(11) DEFAULT NULL,
  `volatiled` int(11) DEFAULT NULL,
  `nextFireTime` datetime DEFAULT NULL,
  `prevFireTime` datetime DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `triggerStats` int(11) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `misfireInstr` int(11) DEFAULT NULL,
  `cronExpression` varchar(80) CHARACTER SET utf8 NOT NULL,
  `enabled` int(11) DEFAULT NULL,
  `timeZone` int(11) DEFAULT NULL,
  `description` varchar(300) CHARACTER SET utf8 DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_triggerdef
-- ----------------------------

-- ----------------------------
-- Table structure for `hi_user`
-- ----------------------------
DROP TABLE IF EXISTS `hi_user`;
CREATE TABLE `hi_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `userName` varchar(30) CHARACTER SET utf8 NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `country` int(11) DEFAULT NULL,
  `timeZone` int(11) DEFAULT NULL,
  `accountEnabled` int(11) DEFAULT NULL,
  `accountLocked` int(11) DEFAULT NULL,
  `expiredDate` datetime DEFAULT NULL,
  `credentialsExpired` int(11) DEFAULT NULL,
  `fullName` varchar(30) CHARACTER SET utf8 NOT NULL,
  `org` int(11) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `address` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `mobile` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `zip` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `SSN` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `mail` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `userMgrType` int(11) DEFAULT NULL,
  `notifyMode` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_user
-- ----------------------------
INSERT INTO `hi_user` VALUES ('1', '52', 'sa', 'c12e01f2a13ff5587e1e9e4aedb8242d', null, null, null, null, null, null, 'administrator', '1', null, 'ss12', '12', '', '', null, '', '1400', null, null, null, '0');

-- ----------------------------
-- Table structure for `hi_userauthority`
-- ----------------------------
DROP TABLE IF EXISTS `hi_userauthority`;
CREATE TABLE `hi_userauthority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `securityUser` int(11) DEFAULT NULL,
  `authority` int(11) DEFAULT NULL,
  `org` int(11) DEFAULT NULL,
  `scope` int(11) DEFAULT NULL,
  `roleAuthority` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_userauthority
-- ----------------------------

-- ----------------------------
-- Table structure for `hi_usergroup`
-- ----------------------------
DROP TABLE IF EXISTS `hi_usergroup`;
CREATE TABLE `hi_usergroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `securityUser` int(11) DEFAULT NULL,
  `securityGroup` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_usergroup
-- ----------------------------

-- ----------------------------
-- Table structure for `hi_userrole`
-- ----------------------------
DROP TABLE IF EXISTS `hi_userrole`;
CREATE TABLE `hi_userrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `securityUser` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hi_userrole
-- ----------------------------

-- ----------------------------
-- Table structure for `menulink`
-- ----------------------------
DROP TABLE IF EXISTS `menulink`;
CREATE TABLE `menulink` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `linkUrl` varchar(200) CHARACTER SET utf8 NOT NULL,
  `displayRef` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `authority` int(11) DEFAULT NULL,
  `sequence` decimal(18,2) DEFAULT NULL,
  `menu` int(11) DEFAULT NULL,
  `menuLinkType` int(11) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100301 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of menulink
-- ----------------------------
INSERT INTO `menulink` VALUES ('1000', '0', '/hiOrgList.action', '部门', '部门', '1000', '9999.00', '1000', '0', '0');
INSERT INTO `menulink` VALUES ('1100', '0', '/hiUserList.action', '人员', '人员', '1100', '9999.00', '1000', '0', '0');
INSERT INTO `menulink` VALUES ('1200', '0', '/personalSettingView.action', '个人设置', '个人设置', '1200', '9999.00', '1000', '0', '0');
INSERT INTO `menulink` VALUES ('2000', '0', '/authorityList.action', '权限', '权限', '2000', '9999.00', '2000', '1', '0');
INSERT INTO `menulink` VALUES ('2100', '0', '/roleList.action', '角色', '角色', '2100', '9999.00', '2000', '0', '0');
INSERT INTO `menulink` VALUES ('2300', '0', '/userAuthorityList.action', '用户权限', '用户权限', '2300', '9999.00', '2000', '0', '0');
INSERT INTO `menulink` VALUES ('2701', '0', '/securityUserList.action', '用户管理', '用户管理', '2999', '9999.00', '2000', '0', '0');
INSERT INTO `menulink` VALUES ('2800', '0', '/privilegeResourceList.action', '权限资源', '权限资源', '2800', '9999.00', '2000', '1', '0');
INSERT INTO `menulink` VALUES ('3000', '0', '/enumerationList.action', '枚举实体', '枚举实体', '3000', '9999.00', '3000', '0', '0');
INSERT INTO `menulink` VALUES ('3100', '0', '/enumerationValueList.action', '枚举值', '枚举值', '3100', '9999.00', '3000', '0', '0');
INSERT INTO `menulink` VALUES ('4100', '0', '/triggerDefList.action', '触发器', '触发器', '4100', '9999.00', '5000', '1', '0');
INSERT INTO `menulink` VALUES ('5000', '0', '/appSettingList.action', '应用配置', '应用配置', '5000', '9999.00', '5000', '0', '0');
INSERT INTO `menulink` VALUES ('5100', '0', '/messageList.action', '消息', '消息', '5100', '9999.00', '5000', '1', '0');
INSERT INTO `menulink` VALUES ('5400', '0', '/hiLogList.action', '系统日志', '系统日志', '5400', '9999.00', '5000', '0', '0');
INSERT INTO `menulink` VALUES ('10000', '0', '/menuList.action', '菜单项', '菜单项', '10000', '9999.00', '10000', '0', '0');
INSERT INTO `menulink` VALUES ('10100', '0', '/menuLinkList.action', '菜单链接', '菜单链接', '10100', '9999.00', '10000', '0', '0');
INSERT INTO `menulink` VALUES ('12000', '0', '/excelReportDesignList.action', 'Excel报表设计', 'Excel报表设计', '12000', '9999.00', '12000', '0', '0');
INSERT INTO `menulink` VALUES ('42100', '0', '/languageList.action', '多语言信息', '多语言信息', '42100', '9999.00', '42000', '1', '0');
INSERT INTO `menulink` VALUES ('42200', '0', '/languageCodeList.action', '语言编码', '语言编码', '42200', '9999.00', '42000', '1', '0');
INSERT INTO `menulink` VALUES ('42400', '0', '/timezoneList.action', '时区', '时区', '42400', '9999.00', '42000', '1', '0');
INSERT INTO `menulink` VALUES ('100300', '0', '/contentList.action', '内容管理', '内容管理', '100300', '9999.00', '100000', '0', '0');
