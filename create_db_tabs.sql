-- mobilebank数据库
DROP DATABASE IF EXISTS mobilebank;
CREATE DATABASE mobilebank CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
USE mobilebank;

-- 用户信息表
DROP TABLE IF EXISTS userinfo;
CREATE TABLE userinfo (
  id int AUTO_INCREMENT PRIMARY KEY COMMENT '编号',
  account varchar(10) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  realname varchar(50) NOT NULL COMMENT '姓名',
  sex int DEFAULT '1' COMMENT '性别(0为女, 1为男)',
  telephone varchar(11) DEFAULT NULL COMMENT '手机号',
  IDCard varchar(18) NOT NULL COMMENT '身份证号',
  birthPlace varchar(50) NOT NULL COMMENT '籍贯',
  address varchar(50) DEFAULT NULL COMMENT '地址',
  creditGrade int DEFAULT '5' COMMENT '信用等级(星级5, 4, 3, 2, 1)',
  `status` int DEFAULT '1' COMMENT '帐号状态(0为注销, 1为正常)'
);
INSERT INTO userinfo VALUES (NULL, '147258369', '123456', '曹茹', 0, '18600000022', '411250200006152001', '河南省焦作市', '许昌', 5, 1);
INSERT INTO userinfo VALUES (NULL, '666666', '123456', '诸葛亮', 1, '18600000044', '402339200102222515', '江苏省苏州市', '洛阳', 4, 1);
INSERT INTO userinfo VALUES (NULL, '123456', '123456', '张飞', 1, '18600000033', '471302200008115392', '北京市', '荆州', 3, 1);

-- 账户信息表
DROP TABLE IF EXISTS payaccountinfo;
CREATE TABLE payaccountinfo (
  id int AUTO_INCREMENT PRIMARY KEY COMMENT '编号',
  userId int COMMENT '用户ID【关联】userinfo(id)',
  bankCardID varchar(19) NOT NULL COMMENT '银行卡号',
  `password` varchar(50) NOT NULL COMMENT '账户密码',
  firstBalance double NOT NULL COMMENT '账户初始余额 ',
  balance double NOT NULL COMMENT '账户目前余额',
  createDate date NOT NULL COMMENT '开户日期',
  createAddress varchar(50) DEFAULT NULL COMMENT '开户网点',
  `status` int DEFAULT '2' COMMENT '账户状态(0为解绑, 1为正常, 2为待绑定)',
  `comment` varchar(50) DEFAULT NULL COMMENT '备注'
);
INSERT INTO payaccountinfo VALUES (NULL, 3, '6003483299638003217', '123456', 4000.02, 4000.02, '2024-1-22', '河南焦作', 1, NULL);
INSERT INTO payaccountinfo VALUES (NULL, 2, '9100348290996543903', '321654', 1000.03, 500, '2024-5-12', '河南郑州', 1, NULL);
INSERT INTO payaccountinfo VALUES (NULL, 3, '9100266254891543901', '686533', 6300.03, 700.03, '2024-5-13', '河南郑州', 1, NULL);
INSERT INTO payaccountinfo VALUES (NULL, 1, '5401348290996543902', '134350', 700.03, 803, '2024-5-14', '河南郑州', 1, NULL);
INSERT INTO payaccountinfo VALUES (NULL, 3, '9102561349996543903', '727277', 712, 70, '2024-5-15', '河南郑州', 1, NULL);
INSERT INTO payaccountinfo VALUES (NULL, 2, '3400348290996543904', '654321', 5300, 880.92, '2024-5-16', '河南郑州', 1, NULL);
INSERT INTO payaccountinfo VALUES (NULL, 1, '9100348290996543905', '123456', 10000, 2300, '2024-5-17', '河南郑州', 1, NULL);
INSERT INTO payaccountinfo VALUES (NULL, 2, '9100348290956543906', '990966', 8000, 7000.03, '2024-5-18', '河南郑州', 1, NULL);
INSERT INTO payaccountinfo VALUES (NULL, 3, '9100784290996543907', '334887', 600, 7000, '2024-5-19', '河南郑州', 1, NULL);
INSERT INTO payaccountinfo VALUES (NULL, 1, '9100348290996543908', '100894', 8000, 900.03, '2024-5-20', '河南郑州', 1, NULL);
INSERT INTO payaccountinfo VALUES (NULL, 2, '9100963990996543909', '990846', 600.03, 4000, '2024-5-21', '河南郑州', 1, NULL);
INSERT INTO payaccountinfo VALUES (NULL, 3, '9100348290996543910', '706666', 3005, 540.29, '2024-5-22', '河南郑州', 1, NULL);

-- 管理员信息表
DROP TABLE IF EXISTS admininfo;
CREATE TABLE admininfo (
  id int AUTO_INCREMENT PRIMARY KEY COMMENT '编号',
  account varchar(10) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  realname varchar(50) NOT NULL COMMENT '姓名'
);
INSERT INTO admininfo VALUES (NULL, 'admin', 'admin', '李利');
INSERT INTO admininfo VALUES (NULL, '123456', '123456', '王小王');
INSERT INTO admininfo VALUES (NULL, '666666', 'ping', '安利民');

-- 角色表
DROP TABLE IF EXISTS roleinfo;
CREATE TABLE roleinfo (
	id int AUTO_INCREMENT PRIMARY KEY COMMENT '编号',
	roleName varchar(20) NOT NULL COMMENT '角色名称'
);

-- 角色-用户表
DROP TABLE IF EXISTS roleuseradmininfo;
CREATE TABLE roleuseradmininfo (
	id int AUTO_INCREMENT PRIMARY KEY COMMENT '编号',
	`table` int NOT NULL COMMENT '用户表(0为userinfo, 1为admininfo)',
	userId int NOT NULL COMMENT '用户ID【关联】userinfo(id)或admininfo(id)',
	roleId int NOT NULL COMMENT '角色ID【关联】roleinfo(id)'
);

-- 权限/菜单表 
DROP TABLE IF EXISTS menuinfo;
CREATE TABLE menuinfo (
	id int AUTO_INCREMENT PRIMARY KEY COMMENT '编号',
	menuName varchar(20) NOT NULL COMMENT '权限/菜单名称'
);

-- 授权表
DROP TABLE IF EXISTS rolemenuinfo;
CREATE TABLE rolemenuinfo (
	id int AUTO_INCREMENT PRIMARY KEY COMMENT '编号',
	roleId int NOT NULL COMMENT '角色ID【关联】roleinfo(id)',
	menuId int NOT NULL COMMENT '权限/菜单ID【关联】menuinfo(id)'
);

-- 转账信息表
DROP TABLE IF EXISTS transferinfo;
CREATE TABLE transferinfo (
  id int AUTO_INCREMENT PRIMARY KEY COMMENT '编号',
  payAccountId int COMMENT '账户ID【关联】payaccountinfo(id)',
  targetPayAccountId int COMMENT '转账对象ID【关联】payaccountinfo(id)',
  money double NOT NULL COMMENT '转账金额',
  orderNumber varchar(50) NOT NULL COMMENT '流水号',
  payDate date NOT NULL COMMENT '交易时间',
  `status` int NOT NULL COMMENT '交易状态(0为失败, 1为成功, 2为撤销)'
);

-- 支付信息表
DROP TABLE IF EXISTS payinfo;
CREATE TABLE payinfo (
  id int AUTO_INCREMENT PRIMARY KEY COMMENT '编号',
  payAccountId int COMMENT '账户ID【关联】payaccountinfo(id)',
  target varchar(50) NOT NULL COMMENT '交易对象',
  money double NOT NULL COMMENT '支付金额',
  orderNumber varchar(50) NOT NULL COMMENT '流水号',
  payDate date NOT NULL COMMENT '交易时间',
  `status` int DEFAULT NULL COMMENT '交易状态(0为失败, 1为成功, 2为撤销)'
);
INSERT INTO payinfo VALUES (NULL, 1, '孙尚香', 20, '6001202404120834020058', '2024-4-12', 1);
INSERT INTO payinfo VALUES (NULL, 2, '小王食品', 10, '6001202401021231550105', '2024-1-2', 1);
INSERT INTO payinfo VALUES (NULL, 1, '上海利民集团有限公司', 6, '6001202311081530551508', '2023-11-8', 2);

-- 用户日志表
DROP TABLE IF EXISTS userlog;
CREATE TABLE userlog (
  id int AUTO_INCREMENT PRIMARY KEY COMMENT '编号',
  userId int NOT NULL COMMENT '用户ID【关联】userinfo(id)',
  type varchar(20) NOT NULL COMMENT '操作名称',
  operationTime datetime NOT NULL COMMENT '操作时间',
  reserveTime datetime NOT NULL COMMENT '日志保留期限',
  `comment` varchar(50) DEFAULT NULL COMMENT '备注'
);

-- 管理员日志表
DROP TABLE IF EXISTS adminlog;
CREATE TABLE adminlog (
  id int AUTO_INCREMENT PRIMARY KEY COMMENT '编号',
  adminId int NOT NULL COMMENT '管理员ID【关联】admininfo(id)',
  type varchar(20) NOT NULL COMMENT '操作名称',
  operationTime datetime NOT NULL COMMENT '操作时间',
  reserveTime datetime NOT NULL COMMENT '日志保留期限',
  `comment` varchar(50) DEFAULT NULL COMMENT '备注'
);
