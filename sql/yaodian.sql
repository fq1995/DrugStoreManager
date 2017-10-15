/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : yaodian

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2017-10-14 10:56:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_dosageform
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dosageform`;
CREATE TABLE `tbl_dosageform` (
  `DOSAGEFORM_ID` varchar(32) NOT NULL,
  `DOSAGEFORM` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`DOSAGEFORM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_dosageform
-- ----------------------------
INSERT INTO `tbl_dosageform` VALUES ('1b1999eeea93485fb939109516e738c2', '软膏');
INSERT INTO `tbl_dosageform` VALUES ('387c8362d40b427984e22626349514b3', '栓剂');
INSERT INTO `tbl_dosageform` VALUES ('392c168075ff4a469cdca5fbe927f334', '膜.贴片');
INSERT INTO `tbl_dosageform` VALUES ('47ec354b561e4683832eb2373299bcad', '糖浆');
INSERT INTO `tbl_dosageform` VALUES ('672d571ab90c4c40838a6db535121984', '口服液');
INSERT INTO `tbl_dosageform` VALUES ('689e595cd4aa49be805541550b325f01', '咀嚼片');
INSERT INTO `tbl_dosageform` VALUES ('6910bddbefff4426aee4b1c7fa0461ae', '颗粒剂');
INSERT INTO `tbl_dosageform` VALUES ('9212bdf64ad44f6fafd8bdce005bce8a', '胶囊');
INSERT INTO `tbl_dosageform` VALUES ('943d7e6e52514018bd5a794a50600ae5', '包');
INSERT INTO `tbl_dosageform` VALUES ('962e2b372bcc4bcf9f67ef04268eb82e', '千克');
INSERT INTO `tbl_dosageform` VALUES ('966a5d71faee409fb577f695234433fd', '眼药水');
INSERT INTO `tbl_dosageform` VALUES ('985fdb4284c84ac7b3697456873fe172', '气雾剂');
INSERT INTO `tbl_dosageform` VALUES ('ac8b5940151b4b689f9599b6bfad5a7c', '泡腾片');
INSERT INTO `tbl_dosageform` VALUES ('ad258b2cfa394277b1169d36ce2c734b', '粉');
INSERT INTO `tbl_dosageform` VALUES ('b214816c6b224634a5642c61f104af9f', '克');
INSERT INTO `tbl_dosageform` VALUES ('cb8bb2c2148e41c389cfbcaad8c98054', '散剂');
INSERT INTO `tbl_dosageform` VALUES ('cd33daa054884296aeee28409360247a', '口含片');
INSERT INTO `tbl_dosageform` VALUES ('cdbb086903a3498e8b708bac1077bd5d', '片剂');
INSERT INTO `tbl_dosageform` VALUES ('d13a4e3dea3e4b48b9d1db770d697ba5', '乳膏');
INSERT INTO `tbl_dosageform` VALUES ('d31a199d26154ed8adcb0a78868dd9ba', '丸');

-- ----------------------------
-- Table structure for tbl_drug
-- ----------------------------
DROP TABLE IF EXISTS `tbl_drug`;
CREATE TABLE `tbl_drug` (
  `DRUG_ID` varchar(32) NOT NULL,
  `DOSAGEFORM_ID` varchar(32) DEFAULT NULL,
  `UNITNAME_ID` varchar(32) DEFAULT NULL,
  `CATEGORY_ID` varchar(32) DEFAULT NULL,
  `DRUG_CODE` int(11) NOT NULL,
  `DRUG_NAME` varchar(20) DEFAULT NULL,
  `MANUFACTURER` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `ApprovalNumber` varchar(32) DEFAULT NULL,
  `MODIFIER` varchar(10) DEFAULT NULL,
  `MODIFY_TIME` date DEFAULT NULL,
  `MEMO` varchar(100) DEFAULT NULL,
  `STATUS` char(1) DEFAULT NULL,
  `SALEPEICE` decimal(10,2) DEFAULT NULL,
  `MEMBERPRICE` decimal(10,2) DEFAULT NULL,
  `old_name` varchar(100) DEFAULT NULL,
  `new_name` varchar(100) DEFAULT NULL,
  `STOCKNUMBER` int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`DRUG_ID`),
  UNIQUE KEY `AK_Key_2` (`DRUG_CODE`,`DOSAGEFORM_ID`,`UNITNAME_ID`,`CATEGORY_ID`),
  KEY `FK_Reference_12` (`DOSAGEFORM_ID`),
  KEY `FK_Reference_13` (`UNITNAME_ID`),
  KEY `FK_Reference_21` (`CATEGORY_ID`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`DOSAGEFORM_ID`) REFERENCES `tbl_dosageform` (`DOSAGEFORM_ID`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`UNITNAME_ID`) REFERENCES `tbl_drugunit` (`UNITNAME_ID`),
  CONSTRAINT `FK_Reference_21` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `tbl_drugcategory` (`CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_drug
-- ----------------------------
INSERT INTO `tbl_drug` VALUES ('0731fa642c404d73849edc1a62a23b50', '9212bdf64ad44f6fafd8bdce005bce8a', '30fc216763bb41a88ecf2cc8ffe47b8d', 'ebf3daf3180345409cf88e27d7d1d175', '1027', '一洲感冒清', '海南一洲制药厂', '国药准字Z46020025', '李四', '2016-11-05', '', '1', '21.00', '18.90', '一洲感冒清.png', '201611051959413680.png', '200');
INSERT INTO `tbl_drug` VALUES ('089bfa6cb5014252964d83bf3d8b42da', 'cdbb086903a3498e8b708bac1077bd5d', '30fc216763bb41a88ecf2cc8ffe47b8d', '54b8bd9ac2104eecb1c3190d7d112114', '1022', '泻利停', '哈药集团制药六厂', '国药准字H23023427', '张三', '2017-03-21', '', '1', '23.00', '20.70', '泻利停.png', '201611060849277758.png', '200');
INSERT INTO `tbl_drug` VALUES ('093b0a2d54b046b4adeaa759ee57b0ed', 'cd33daa054884296aeee28409360247a', '30fc216763bb41a88ecf2cc8ffe47b8d', '434cb2839830435e8313bb4cf788143d', '1040', '西瓜霜含片', '桂林三金药业股份有限公司', '国药准字Z45021646', '李四', '2017-03-22', '', '1', '5.00', '4.50', '西瓜霜含片.png', '201703201003176316.png', '200');
INSERT INTO `tbl_drug` VALUES ('1718d22a5f9d41a2bbbe03bcdd427831', 'ad258b2cfa394277b1169d36ce2c734b', '30fc216763bb41a88ecf2cc8ffe47b8d', 'f12d4b871d574a3aa78ee98a670ad942', '1035', '云南白药粉', '万源（福州）药业有限公司', '国药准字Z53020798', '小王', '2017-03-21', '', '1', '18.90', '17.00', '云南白药粉.png', '201703101515082352.png', '200');
INSERT INTO `tbl_drug` VALUES ('198ba2a8ece54f68ba2dd422e0b2b66a', '9212bdf64ad44f6fafd8bdce005bce8a', '30fc216763bb41a88ecf2cc8ffe47b8d', '54b8bd9ac2104eecb1c3190d7d112114', '1052', '法莫替丁胶囊', '修正药业集团股份有限公司', '国药准字H20050294', '张三', '2017-03-22', '', '1', '11.00', '9.90', null, 'zanwu.png', null);
INSERT INTO `tbl_drug` VALUES ('2bc3cb368dba4f8b87e87847bb56cf2f', 'cdbb086903a3498e8b708bac1077bd5d', '2bd550b99fd74988844db5195e713a4c', 'c467ef5465434ea09eb6527497d5046d', '1044', '优降糖', '上海医药(集团)有限公司信谊制药总厂', '国药准字H32025808', '李四', '2017-03-20', '', '1', '18.00', '16.20', null, 'zanwu.png', '200');
INSERT INTO `tbl_drug` VALUES ('2f5c5c92397e44009fe53a51bd589e86', 'cdbb086903a3498e8b708bac1077bd5d', '2bd550b99fd74988844db5195e713a4c', '54b8bd9ac2104eecb1c3190d7d112114', '1016', '维酶素片', '北海阳光药业有限公司', '国药准字H45021183', '张三', '2016-11-05', '', '1', '15.00', '13.50', '维酶素片.png', '201611051946403786.png', '100');
INSERT INTO `tbl_drug` VALUES ('346cbdd663fc40649f0a464d8b9d57e0', '47ec354b561e4683832eb2373299bcad', '192f6bdf72054caeb8e51b3d32efc2f0', '24a4c5c3662a40d0b964f9490e277223', '1001', '保济丸', '广州羊城药业股份有限公司', '国药准字Z44020610', '张三', '2017-03-22', '', null, '18.00', '16.20', null, null, '100');
INSERT INTO `tbl_drug` VALUES ('37f372b439bb47679ed19e21b15ebea9', 'cdbb086903a3498e8b708bac1077bd5d', '2bd550b99fd74988844db5195e713a4c', '90fdbe8961a34d22a7d2042365f47630', '1018', '炎痛喜康片', '山西云鹏制药', '国药准字H14020802', '张三', '2017-03-21', '', '1', '12.00', '10.80', '炎痛喜康片.png', '201611051952223072.png', '200');
INSERT INTO `tbl_drug` VALUES ('3d366366288f42ad86c3c8fd2a749358', 'cdbb086903a3498e8b708bac1077bd5d', '33ebdc54e6134b7e9757902a2a49653e', 'f12d4b871d574a3aa78ee98a670ad942', '1048', '三七胶囊', '华康药业', '国药准字Z20026188', '李四', '2017-03-22', '', '1', '18.00', '16.20', null, 'zanwu.png', '100');
INSERT INTO `tbl_drug` VALUES ('473b109124cd4e9daf00e3de031a9e61', 'cdbb086903a3498e8b708bac1077bd5d', '30fc216763bb41a88ecf2cc8ffe47b8d', '54b8bd9ac2104eecb1c3190d7d112114', '1025', '胃舒平', '山西云鹏制药有限公司', '国药准字H14020777', '李四', '2017-03-22', '', null, '25.50', '22.90', null, 'zanwu.png', '270');
INSERT INTO `tbl_drug` VALUES ('48671c120c9f43aaab5486597a34ef3e', 'cdbb086903a3498e8b708bac1077bd5d', '2bd550b99fd74988844db5195e713a4c', 'c467ef5465434ea09eb6527497d5046d', '1024', '唐必呋', '海南普利制药', '国药准字Z20026188', '王五', '2017-03-22', '', '1', '18.00', '16.20', '唐必呋.png', '201703222058177158.png', '100');
INSERT INTO `tbl_drug` VALUES ('499dc39236e84e6d9efdd2cce49044a5', '672d571ab90c4c40838a6db535121984', '30fc216763bb41a88ecf2cc8ffe47b8d', '24a4c5c3662a40d0b964f9490e277223', '1038', '三蛇胆川贝糖浆', '广西半宙制药股份有限公司', '国药准字Z45020847', '张三', '2017-03-19', '', '1', '22.00', '19.80', null, 'zanwu.png', '200');
INSERT INTO `tbl_drug` VALUES ('4b44d8a3acbd4a9080c97cb8ebf58239', '9212bdf64ad44f6fafd8bdce005bce8a', '30fc216763bb41a88ecf2cc8ffe47b8d', 'f12d4b871d574a3aa78ee98a670ad942', '1036', '永龙去痛胶囊', '万源（福州）药业有限公司', '国药准字H32020263', '小王', '2017-03-21', '', '1', '9.50', '8.60', null, 'zanwu.png', '20');
INSERT INTO `tbl_drug` VALUES ('4c3f9bc466814c01bf0bad53aac53d39', 'cdbb086903a3498e8b708bac1077bd5d', '30fc216763bb41a88ecf2cc8ffe47b8d', '54b8bd9ac2104eecb1c3190d7d112114', '1042', '维酶素片', '张家港市制药厂', '国药准字H32025808', '李四', '2017-03-21', '', '1', '22.00', '19.80', null, 'zanwu.png', '200');
INSERT INTO `tbl_drug` VALUES ('4dc48169b5cb4181b1d94dddeb6f8868', '672d571ab90c4c40838a6db535121984', '30fc216763bb41a88ecf2cc8ffe47b8d', '24a4c5c3662a40d0b964f9490e277223', '1047', '小儿咳喘灵口服液', '郑州羚锐制药有限公司', '国药准字z61021325', '李四', '2017-03-22', '', '1', '31.50', '28.35', null, 'zanwu.png', '100');
INSERT INTO `tbl_drug` VALUES ('55740192f515473e8563c3647badaf88', 'cdbb086903a3498e8b708bac1077bd5d', '2bd550b99fd74988844db5195e713a4c', '14de0c5273be49cc9523c430976c0b2f', '1012', '扑尔敏', '山东莒南', '国药准字H33020466', '张三', '2016-11-05', '', '1', '12.00', '10.80', '扑尔敏.png', '201611051939358261.png', '100');
INSERT INTO `tbl_drug` VALUES ('5c47ae53aa184596bc7f8e3ba69b00f6', 'd13a4e3dea3e4b48b9d1db770d697ba5', '30fc216763bb41a88ecf2cc8ffe47b8d', '77a9a7b577c746a2997fcc4c07c8a859', '1017', '妇舒康', '台州', '国药准字H23023427', '张三', '2017-03-21', '', '1', '12.00', '10.80', '妇舒康.png', '201611051951092175.png', '200');
INSERT INTO `tbl_drug` VALUES ('5c6f2d0d662242f9adb226df5e0588f3', 'cdbb086903a3498e8b708bac1077bd5d', '2bd550b99fd74988844db5195e713a4c', 'ea5c21153ec3443c8cd978dab6ee5b0e', '1029', 'VC片', '湖北华中药业有限公司', '国药准字H42020614', '李四', '2016-11-05', '', '1', '21.00', '18.90', 'vc片.png', '201611052248519850.png', '200');
INSERT INTO `tbl_drug` VALUES ('5f163ec3ac4a4c47a655dfe5f15da978', 'cdbb086903a3498e8b708bac1077bd5d', '2bd550b99fd74988844db5195e713a4c', 'ebf3daf3180345409cf88e27d7d1d175', '1046', '烟酸片', '南京白敬宇制药厂', '苏卫药准字（1982）第25900号', '李四', '2017-03-22', '', null, '18.00', '16.20', null, 'zanwu.png', '462');
INSERT INTO `tbl_drug` VALUES ('62684de9112a4069bad0eace49103dd7', '672d571ab90c4c40838a6db535121984', '30fc216763bb41a88ecf2cc8ffe47b8d', 'f12d4b871d574a3aa78ee98a670ad942', '1050', '复方大青叶合剂', '宝鸡鑫中天制药有限公司', '国药准字z61021325', '李四', '2017-03-22', '', '1', '10.00', '9.00', null, 'zanwu.png', null);
INSERT INTO `tbl_drug` VALUES ('6bffe091dcae4df89809ea36a21e275e', 'cdbb086903a3498e8b708bac1077bd5d', '2bd550b99fd74988844db5195e713a4c', '14de0c5273be49cc9523c430976c0b2f', '1028', '眩晕停', '湖南千金湘江药业股份有限公司', '国药准字H43020325', '李四', '2016-11-05', '', '1', '23.00', '20.70', '眩晕停.png', '201611052004113426.png', '200');
INSERT INTO `tbl_drug` VALUES ('6e8b3d717a7c44739249becee9ef36f6', '9212bdf64ad44f6fafd8bdce005bce8a', '30fc216763bb41a88ecf2cc8ffe47b8d', 'f12d4b871d574a3aa78ee98a670ad942', '1053', '生力胶囊', '云南龙润药业有限公司', '国药准字Z53021635', '李四', '2017-03-22', '', '1', '22.00', '19.80', null, 'zanwu.png', '200');
INSERT INTO `tbl_drug` VALUES ('7d21e4a9fd6a4179b9c73f536cbc885f', 'cdbb086903a3498e8b708bac1077bd5d', '192f6bdf72054caeb8e51b3d32efc2f0', 'ebf3daf3180345409cf88e27d7d1d175', '1014', '阿昔洛韦片', '南京瑞尔医药有限公司', '国药准字H19994080', '张三', '2017-03-21', '', '1', '12.00', '10.80', '阿昔洛韦片.png', '201611051943314378.png', '100');
INSERT INTO `tbl_drug` VALUES ('7fb0854fc10b494ca29ca3ab6b3573de', 'cb8bb2c2148e41c389cfbcaad8c98054', '2bd550b99fd74988844db5195e713a4c', 'f12d4b871d574a3aa78ee98a670ad942', '1031', '锡类散', '杭州胡庆余堂药业有限公司', '国药准字Z33020132', '李四', '2016-11-07', '', '1', '23.00', '20.70', '锡类散.png', '201611070815051409.png', '200');
INSERT INTO `tbl_drug` VALUES ('8046fda2ed324e3682292abb52e0816f', '9212bdf64ad44f6fafd8bdce005bce8a', '30fc216763bb41a88ecf2cc8ffe47b8d', '54b8bd9ac2104eecb1c3190d7d112114', '1026', '猴头健胃灵胶囊', '湖南新汇制药有限公司', '国药准字Z43020067', '李四', '2016-11-05', '', '1', '23.00', '20.70', '猴头健胃灵胶囊.png', '201611051958429920.png', '100');
INSERT INTO `tbl_drug` VALUES ('837500b2d6ac44f0ae5595e09addfc73', '1b1999eeea93485fb939109516e738c2', '2bd550b99fd74988844db5195e713a4c', 'f2c885a1a6364c2da265e94c6608ce0c', '1037', '酸痛宁', '河南羚锐制药股份有限公司', '国药准字H42020614', '张三', '2017-03-19', '', '1', '13.00', '11.70', null, 'zanwu.png', '200');
INSERT INTO `tbl_drug` VALUES ('8ff8eb8541694720bbe6ff6b6d94e272', 'cdbb086903a3498e8b708bac1077bd5d', '30fc216763bb41a88ecf2cc8ffe47b8d', '90fdbe8961a34d22a7d2042365f47630', '1008', '复方新诺明片', '浙江得恩制药有限公司', '国药准字H33020326', '张三', '2016-11-05', '', '1', '15.00', '13.50', '复方新诺明片.png', '201611051904183135.png', '100');
INSERT INTO `tbl_drug` VALUES ('90afb0687de94660abaf4c304530d4e9', 'cdbb086903a3498e8b708bac1077bd5d', '2bd550b99fd74988844db5195e713a4c', 'ea5c21153ec3443c8cd978dab6ee5b0e', '1015', 'VB6片', '安徽国正', '国药准字H42020613', '李四', '2017-03-21', '', '1', '17.00', '15.30', 'VB6片.png', '201611051938120800.png', '200');
INSERT INTO `tbl_drug` VALUES ('982c0cd5d4b74e198461fd8d289876b3', 'cdbb086903a3498e8b708bac1077bd5d', '30fc216763bb41a88ecf2cc8ffe47b8d', 'f12d4b871d574a3aa78ee98a670ad942', '1039', '地锦草片', '河北安国药业集团有限公司', '国药准字z13022085', '李四', '2017-03-21', '', '1', '21.00', '18.90', null, 'zanwu.png', '200');
INSERT INTO `tbl_drug` VALUES ('9b2a14f0deaa49fa952a28079e8de088', 'd31a199d26154ed8adcb0a78868dd9ba', '2bd550b99fd74988844db5195e713a4c', 'd3de060cc6d94820aeb665518cc2942c', '1033', '壮骨关节丸', '三九医药股份有限公司', '国药准字Z44023377', '小王', '2017-03-22', '', '1', '31.50', '28.40', '壮骨关节丸.png', '201703101518554585.png', '100');
INSERT INTO `tbl_drug` VALUES ('9be3feca8cc740fb8ea9d863328782fb', 'cdbb086903a3498e8b708bac1077bd5d', '2bd550b99fd74988844db5195e713a4c', 'ea5c21153ec3443c8cd978dab6ee5b0e', '1019', '地巴唑片', '上海天平胃舒平制药厂', '国药准字H31020655', '张三', '2017-03-21', '', '1', '22.00', '19.80', '地巴唑片.png', '201611051954177606.png', '200');
INSERT INTO `tbl_drug` VALUES ('a13213e8d4244fc5ada60c3173730e8a', '392c168075ff4a469cdca5fbe927f334', '30fc216763bb41a88ecf2cc8ffe47b8d', 'f2c885a1a6364c2da265e94c6608ce0c', '1051', '一贴消', '西安强生药业', '国药准字H31020795', '李四', '2017-03-22', '', '1', '23.00', null, null, 'zanwu.png', '200');
INSERT INTO `tbl_drug` VALUES ('aa61175b07504a989f753d6462d83f3a', '47ec354b561e4683832eb2373299bcad', '30fc216763bb41a88ecf2cc8ffe47b8d', '90fdbe8961a34d22a7d2042365f47630', '1003', '食母生片', '江西红星制药厂', '国药准字H19993487', '李四', '2016-11-05', '', '1', '16.00', '14.40', '食母生片.png', '201611051740109906.png', '100');
INSERT INTO `tbl_drug` VALUES ('af3bc498cdfa43a38ad3c2bc4d720ed8', 'cdbb086903a3498e8b708bac1077bd5d', '30fc216763bb41a88ecf2cc8ffe47b8d', 'ebf3daf3180345409cf88e27d7d1d175', '1005', '灭滴灵片', '浙江得恩德制药有限公司', '国药准字H32021796', '张三', '2017-03-22', '', '1', '12.00', '10.80', '灭滴灵片.png', '201611051740501575.png', '200');
INSERT INTO `tbl_drug` VALUES ('b1fd8870f0434770bdcf60af2abb0a9b', '9212bdf64ad44f6fafd8bdce005bce8a', '30fc216763bb41a88ecf2cc8ffe47b8d', '24a4c5c3662a40d0b964f9490e277223', '1002', '速效伤风胶囊', '四川蜀中制药有限公司', '国药准字H34023213', '张三', '2017-03-21', '', '1', '34.00', '30.60', '速效伤风胶囊.png', '201611051739181047.png', '100');
INSERT INTO `tbl_drug` VALUES ('b654926f9360490582bbd3cbfd59f0b3', 'd13a4e3dea3e4b48b9d1db770d697ba5', '30fc216763bb41a88ecf2cc8ffe47b8d', 'f2c885a1a6364c2da265e94c6608ce0c', '1034', '阿昔洛韦软膏', '湖北康正药业有限公司', '国药准字Z41022128', '小王', '2017-03-22', '', '1', '31.50', '28.40', null, 'zanwu.png', '328');
INSERT INTO `tbl_drug` VALUES ('b8097269b65a428ca720bc5f0723757c', '672d571ab90c4c40838a6db535121984', '2bd550b99fd74988844db5195e713a4c', '90fdbe8961a34d22a7d2042365f47630', '1007', '奉宫酒', '丹阳市药业有限责任公司', '国药准字H42020613', '张三', '2017-03-21', '', '1', '25.00', '22.50', '奉宫酒.png', '201611051819371713.png', '100');
INSERT INTO `tbl_drug` VALUES ('bfcdc10c47e543da932dd1e808349cc6', '6910bddbefff4426aee4b1c7fa0461ae', '30fc216763bb41a88ecf2cc8ffe47b8d', 'ebf3daf3180345409cf88e27d7d1d175', '1021', '达力芬', '深圳市制药厂', '国药准字H20020512', '张三', '2017-03-21', '', '1', '23.00', '20.70', '达力芬.png', '201611060845594341.png', '200');
INSERT INTO `tbl_drug` VALUES ('d2bee3a6eb604d73bbf35662aa96ed93', 'cdbb086903a3498e8b708bac1077bd5d', '2bd550b99fd74988844db5195e713a4c', 'e197050359464db695013ced876c00de', '1045', '双克', '山西云鹏', '国药准字H14020796', '王五', '2017-03-22', '', null, '16.50', '14.80', null, 'zanwu.png', '200');
INSERT INTO `tbl_drug` VALUES ('da116b026d5c4b8aa8d039f051bedda6', 'cdbb086903a3498e8b708bac1077bd5d', '2bd550b99fd74988844db5195e713a4c', 'ea5c21153ec3443c8cd978dab6ee5b0e', '1010', 'VB1片', '福建力菲克', '国药准字H19994080', '张三', '2016-11-05', '', '1', '25.00', '22.50', 'VB1片.png', '201611051920369263.png', '200');
INSERT INTO `tbl_drug` VALUES ('db71f0ca4332442a96b1a4f3549638e6', 'cdbb086903a3498e8b708bac1077bd5d', '2bd550b99fd74988844db5195e713a4c', '54b8bd9ac2104eecb1c3190d7d112114', '1023', '果导片', '山西临汾地区新星制药厂', '国药准字H14020528', '李四', '2016-11-06', '', '1', '23.00', '20.70', '果导片.png', '201611060844340381.png', '100');
INSERT INTO `tbl_drug` VALUES ('e84df59ed24c404a9577bb0d6bc91c93', 'cdbb086903a3498e8b708bac1077bd5d', '30fc216763bb41a88ecf2cc8ffe47b8d', 'f2c885a1a6364c2da265e94c6608ce0c', '1049', '二甲双胍片', '浙江亚太药业股份有限公司', '国药准字H33020106', '李四', '2017-03-22', '', '1', '12.00', '10.80', null, 'zanwu.png', null);
INSERT INTO `tbl_drug` VALUES ('e9af19d4efb14b0d936bb0eeb7aae04c', 'cb8bb2c2148e41c389cfbcaad8c98054', '30fc216763bb41a88ecf2cc8ffe47b8d', 'f2c885a1a6364c2da265e94c6608ce0c', '1009', '冰硼散', '太极集团浙江东方制药有限公司', '国药准字Z33021003', '张三', '2016-11-05', '', '1', '29.70', '26.70', '冰硼散.png', '201611051915072764.png', '200');
INSERT INTO `tbl_drug` VALUES ('f9f6ecabbf944c5f89e240ef80ce2f09', 'cdbb086903a3498e8b708bac1077bd5d', '30fc216763bb41a88ecf2cc8ffe47b8d', '54b8bd9ac2104eecb1c3190d7d112114', '1032', '胃必治', '沈阳同联药业有限公司', '国药准字H10870006', '小王', '2017-03-21', '', '1', '22.00', '19.80', null, 'zanwu.png', '100');
INSERT INTO `tbl_drug` VALUES ('fbd60e24ba174719a3f2872e5c9052c2', 'd31a199d26154ed8adcb0a78868dd9ba', '30fc216763bb41a88ecf2cc8ffe47b8d', 'f12d4b871d574a3aa78ee98a670ad942', '1006', '喉炎丸', '成都九芝堂金鼎药业有限公司', '国药准字Z51021078', '李四', '2017-03-21', '', '1', '41.00', '36.90', '喉炎丸.png', '201611051744292319.png', '100');

-- ----------------------------
-- Table structure for tbl_drugbuy
-- ----------------------------
DROP TABLE IF EXISTS `tbl_drugbuy`;
CREATE TABLE `tbl_drugbuy` (
  `DRUGBUY_CODE` varchar(255) DEFAULT NULL,
  `DRUGBUY_ID` varchar(32) NOT NULL,
  `DRUG_ID` varchar(32) DEFAULT NULL,
  `MODIFIER` varchar(10) DEFAULT NULL,
  `MODIFY_TIME` date DEFAULT NULL,
  `MOUNT` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DRUGBUY_ID`),
  KEY `DRUG_ID` (`DRUG_ID`),
  CONSTRAINT `tbl_drugbuy_ibfk_1` FOREIGN KEY (`DRUG_ID`) REFERENCES `tbl_drug` (`DRUG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_drugbuy
-- ----------------------------
INSERT INTO `tbl_drugbuy` VALUES ('1000201703201', '5b321d28a6cd44f1b453bfe14ad6d509', '4b44d8a3acbd4a9080c97cb8ebf58239', null, '2017-03-20', null);
INSERT INTO `tbl_drugbuy` VALUES ('1000201703201', 'a77877eea46244dab902d94040f3d576', '4b44d8a3acbd4a9080c97cb8ebf58239', null, '2017-03-20', null);
INSERT INTO `tbl_drugbuy` VALUES ('1000201702101', 'bc1adad3aef647b58ae187e8fac2d51c', '48671c120c9f43aaab5486597a34ef3e', null, '2017-02-10', null);

-- ----------------------------
-- Table structure for tbl_drugcategory
-- ----------------------------
DROP TABLE IF EXISTS `tbl_drugcategory`;
CREATE TABLE `tbl_drugcategory` (
  `CATEGORY_ID` varchar(32) NOT NULL,
  `CATEGORY` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_drugcategory
-- ----------------------------
INSERT INTO `tbl_drugcategory` VALUES ('14de0c5273be49cc9523c430976c0b2f', '皮肤');
INSERT INTO `tbl_drugcategory` VALUES ('24a4c5c3662a40d0b964f9490e277223', '呼吸系统药');
INSERT INTO `tbl_drugcategory` VALUES ('434cb2839830435e8313bb4cf788143d', '五官科药');
INSERT INTO `tbl_drugcategory` VALUES ('54b8bd9ac2104eecb1c3190d7d112114', '胃肠');
INSERT INTO `tbl_drugcategory` VALUES ('77a9a7b577c746a2997fcc4c07c8a859', '妇产科药');
INSERT INTO `tbl_drugcategory` VALUES ('90fdbe8961a34d22a7d2042365f47630', '感冒');
INSERT INTO `tbl_drugcategory` VALUES ('c467ef5465434ea09eb6527497d5046d', '内分泌系统药');
INSERT INTO `tbl_drugcategory` VALUES ('d3de060cc6d94820aeb665518cc2942c', '保健食品');
INSERT INTO `tbl_drugcategory` VALUES ('de58db8cf803491c87a8e06cbb981f3f', '药械');
INSERT INTO `tbl_drugcategory` VALUES ('e197050359464db695013ced876c00de', '心脑血管');
INSERT INTO `tbl_drugcategory` VALUES ('ea5c21153ec3443c8cd978dab6ee5b0e', '维生素类');
INSERT INTO `tbl_drugcategory` VALUES ('ebf3daf3180345409cf88e27d7d1d175', '抗菌素');
INSERT INTO `tbl_drugcategory` VALUES ('f12d4b871d574a3aa78ee98a670ad942', '中成药');
INSERT INTO `tbl_drugcategory` VALUES ('f2c885a1a6364c2da265e94c6608ce0c', '外用药');

-- ----------------------------
-- Table structure for tbl_drugpurchase
-- ----------------------------
DROP TABLE IF EXISTS `tbl_drugpurchase`;
CREATE TABLE `tbl_drugpurchase` (
  `PURCHASE_ID` varchar(32) NOT NULL,
  `PURCHASE_CODE` varchar(32) NOT NULL,
  `DRUG_ID` varchar(32) DEFAULT NULL,
  `SUPPLIER_ID` varchar(32) DEFAULT NULL,
  `USER_ID` varchar(32) DEFAULT NULL,
  `DRUGNAME` varchar(20) DEFAULT NULL,
  `AMOUNT` int(11) DEFAULT NULL,
  `PRODUCTION_DATE` date DEFAULT NULL,
  `VALIDITY_DATE` date DEFAULT NULL,
  `PURCHASEPRICE` decimal(10,2) DEFAULT NULL,
  `SALEPEICE` decimal(10,2) DEFAULT NULL,
  `PURCHASEDATE` date DEFAULT NULL,
  `MEMBERPRICE` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`PURCHASE_ID`),
  KEY `AK_Key_2` (`PURCHASE_CODE`,`DRUG_ID`,`SUPPLIER_ID`,`USER_ID`),
  KEY `FK_Reference_14` (`DRUG_ID`),
  KEY `FK_Reference_15` (`SUPPLIER_ID`),
  KEY `FK_Reference_17` (`USER_ID`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`DRUG_ID`) REFERENCES `tbl_drug` (`DRUG_ID`),
  CONSTRAINT `FK_Reference_15` FOREIGN KEY (`SUPPLIER_ID`) REFERENCES `tbl_supplier` (`SUPPLIER_ID`),
  CONSTRAINT `FK_Reference_17` FOREIGN KEY (`USER_ID`) REFERENCES `tbl_users` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_drugpurchase
-- ----------------------------
INSERT INTO `tbl_drugpurchase` VALUES ('0dbcd9747224431ead186189a15529d9', '1003', '55740192f515473e8563c3647badaf88', 'bea77385a38a4b62b481b34cbe607409', '5ccced34dcb44c3a901ec3b330e88ee1', null, '10', '2016-02-27', '2016-01-27', '12.00', null, '2016-10-27', null);
INSERT INTO `tbl_drugpurchase` VALUES ('0ecbc56e574741979bc2368de1114055', '1004', '2f5c5c92397e44009fe53a51bd589e86', 'cdf0d9c2f35a4d7a86365372d3ed9cd6', 'f767850333754e1c929eabb0a18af9ed', null, '30', '2016-09-20', '2017-04-01', '23.00', null, '2016-11-06', null);
INSERT INTO `tbl_drugpurchase` VALUES ('28108be3d3034f8b8c7fc93d3ed98721', '1008', 'da116b026d5c4b8aa8d039f051bedda6', 'bea77385a38a4b62b481b34cbe607409', 'f2bc9dec5ccb4ab3a4bef7e32068bdf4', null, '80', '2016-09-20', '2018-10-01', '2.00', null, '2016-11-22', null);
INSERT INTO `tbl_drugpurchase` VALUES ('40475add042541b78fcbac5268f5ac65', '1011', '2bc3cb368dba4f8b87e87847bb56cf2f', '070685898f5249aab322186f86ff6fa2', null, null, '30', '2014-03-20', '2018-03-21', '12.00', '18.00', '2017-03-20', '16.20');
INSERT INTO `tbl_drugpurchase` VALUES ('57fea081770e469589ba1dd9d719046c', '1018', '9b2a14f0deaa49fa952a28079e8de088', 'b61a65e75062468996463f6b64a59f49', null, null, '100', '2017-01-24', '2018-03-15', '21.00', '31.50', '2017-03-22', '28.40');
INSERT INTO `tbl_drugpurchase` VALUES ('5838987449534523a60a3299b40f6e4b', '1005', '0731fa642c404d73849edc1a62a23b50', 'bea77385a38a4b62b481b34cbe607409', '5ccced34dcb44c3a901ec3b330e88ee1', null, '40', '2016-09-05', '2017-07-01', '12.00', null, '2016-10-30', null);
INSERT INTO `tbl_drugpurchase` VALUES ('667f9e3f98fa40978f8c6bd91ddd4454', '1015', '3d366366288f42ad86c3c8fd2a749358', 'a27b311cf25442fb8328946c79a25ea4', null, null, '220', '2015-03-25', '2018-03-22', '11.00', '16.50', '2017-03-22', '14.80');
INSERT INTO `tbl_drugpurchase` VALUES ('6cebdc23f48d4c7ba340a1308d76b181', '1016', '3d366366288f42ad86c3c8fd2a749358', 'b61a65e75062468996463f6b64a59f49', null, null, '220', '2015-03-25', '2018-03-22', '12.00', '18.00', '2017-03-22', '16.20');
INSERT INTO `tbl_drugpurchase` VALUES ('6eda44a8292f43c09ba4f1e67a87bcd7', '1014', '4dc48169b5cb4181b1d94dddeb6f8868', 'cdf0d9c2f35a4d7a86365372d3ed9cd6', null, null, '200', '2017-02-14', '2018-03-22', '21.00', '31.50', '2017-03-22', '28.40');
INSERT INTO `tbl_drugpurchase` VALUES ('6f86cee716c945e5b44b318bb991c00e', '1009', '473b109124cd4e9daf00e3de031a9e61', 'dfc36074c9a34dae9e8b8621587b1cad', '91a05f91c28b4a16aef1ccdbb3233861', null, '70', '2016-10-24', '2017-04-18', '17.00', '25.50', '2016-10-24', '22.90');
INSERT INTO `tbl_drugpurchase` VALUES ('7794772ab0f94a18a6a76b026aae8cb4', '1012', 'd2bee3a6eb604d73bbf35662aa96ed93', 'b61a65e75062468996463f6b64a59f49', null, null, '1', '2015-03-21', '2017-04-27', '22.00', '33.00', '2015-03-21', '29.70');
INSERT INTO `tbl_drugpurchase` VALUES ('8c04b39c674a4c59ba93216dd82be672', '1019', 'b654926f9360490582bbd3cbfd59f0b3', 'bea77385a38a4b62b481b34cbe607409', null, null, '130', '2016-11-23', '2018-03-08', '21.00', '31.50', '2017-03-22', '28.40');
INSERT INTO `tbl_drugpurchase` VALUES ('92375af6c96d43c8b3eb36a005cd5901', '1007', '90afb0687de94660abaf4c304530d4e9', 'a27b311cf25442fb8328946c79a25ea4', '63408a229238460ea6783fa9b92bd205', null, '70', '2016-10-03', '2018-04-01', '23.00', null, '2016-11-30', null);
INSERT INTO `tbl_drugpurchase` VALUES ('cb60f114c9f54ecb9a8ab3af75ad2d62', '1013', '5f163ec3ac4a4c47a655dfe5f15da978', 'a27b311cf25442fb8328946c79a25ea4', null, null, '220', '2016-03-24', '2017-03-31', '12.00', '18.00', '2016-03-24', '16.20');
INSERT INTO `tbl_drugpurchase` VALUES ('ee5fe6f0a1684286aa8f645a35470494', '1006', '48671c120c9f43aaab5486597a34ef3e', 'cdf0d9c2f35a4d7a86365372d3ed9cd6', 'f767850333754e1c929eabb0a18af9ed', null, '70', '2016-10-17', '2018-08-01', '12.00', null, '2016-11-22', null);
INSERT INTO `tbl_drugpurchase` VALUES ('f791a8d8ce034d82b6c800e9e71d1bb6', '1017', 'd2bee3a6eb604d73bbf35662aa96ed93', 'a27b311cf25442fb8328946c79a25ea4', null, null, '200', '2017-03-01', '2018-03-15', '11.00', '16.50', '2017-03-22', '14.80');
INSERT INTO `tbl_drugpurchase` VALUES ('ff3430a52a75482ab92bf73e7ae77fe1', '1002', '346cbdd663fc40649f0a464d8b9d57e0', 'dfc36074c9a34dae9e8b8621587b1cad', 'd3900eb8ac794b6792dde684ce091609', null, '40', '2016-10-18', '2017-03-31', '12.00', '18.00', '2016-10-18', '16.20');

-- ----------------------------
-- Table structure for tbl_drugsales
-- ----------------------------
DROP TABLE IF EXISTS `tbl_drugsales`;
CREATE TABLE `tbl_drugsales` (
  `SALES_ID` varchar(32) NOT NULL,
  `SALES_CODE` varchar(32) DEFAULT NULL,
  `DRUG_ID` varchar(32) DEFAULT NULL,
  `USER_ID` varchar(32) DEFAULT NULL,
  `SALEPEICE` decimal(10,2) DEFAULT NULL,
  `MEMBERPRICE` decimal(10,2) DEFAULT NULL,
  `SalesVolume` int(11) DEFAULT NULL,
  `SalesDATE` date DEFAULT NULL,
  `TOTALAMOUNT` float DEFAULT NULL,
  PRIMARY KEY (`SALES_ID`),
  KEY `AK_Key_1` (`SALES_CODE`,`DRUG_ID`,`USER_ID`),
  KEY `FK_Reference_16` (`USER_ID`),
  KEY `FK_Reference_20` (`DRUG_ID`),
  CONSTRAINT `FK_Reference_16` FOREIGN KEY (`USER_ID`) REFERENCES `tbl_users` (`USER_ID`),
  CONSTRAINT `FK_Reference_20` FOREIGN KEY (`DRUG_ID`) REFERENCES `tbl_drug` (`DRUG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_drugsales
-- ----------------------------
INSERT INTO `tbl_drugsales` VALUES ('0c59bc5a8bd74ebe8157a327fa3da41b', '1006', '48671c120c9f43aaab5486597a34ef3e', 'f2bc9dec5ccb4ab3a4bef7e32068bdf4', '12.00', '10.00', '10', '2016-02-06', '120');
INSERT INTO `tbl_drugsales` VALUES ('11da47286f0e4c7b9ff63c01fa24beee', '1009', '473b109124cd4e9daf00e3de031a9e61', '5ccced34dcb44c3a901ec3b330e88ee1', '21.00', '20.00', '1', '2016-10-12', '21');
INSERT INTO `tbl_drugsales` VALUES ('1b7727ab8e99407d935c6ea134104bab', '1016', '089bfa6cb5014252964d83bf3d8b42da', 'f2bc9dec5ccb4ab3a4bef7e32068bdf4', '23.00', '20.70', '3', '2017-03-19', '69');
INSERT INTO `tbl_drugsales` VALUES ('2ea38a5980124b85aa972aaec2aea801', '1020', 'b654926f9360490582bbd3cbfd59f0b3', 'da94873627924883afd9c6616f266561', '31.50', '28.40', '2', '2017-03-22', '63');
INSERT INTO `tbl_drugsales` VALUES ('42a0233e251e46eb8859495c5e3ac822', '1002', 'aa61175b07504a989f753d6462d83f3a', '52d4b1f3daaa44fcaa5c8812ec63e42c', '14.00', '12.00', '1', '2016-10-29', '14');
INSERT INTO `tbl_drugsales` VALUES ('4454c71f5f6c414691b060f2f7eaec9d', '1019', 'e9af19d4efb14b0d936bb0eeb7aae04c', 'f2bc9dec5ccb4ab3a4bef7e32068bdf4', '29.70', '26.70', '4', '2017-03-20', '118.8');
INSERT INTO `tbl_drugsales` VALUES ('50487c6ba4c442f6ae2481b345f695c8', '1010', '90afb0687de94660abaf4c304530d4e9', 'd3900eb8ac794b6792dde684ce091609', '17.00', '17.00', '1', '2017-03-19', '17');
INSERT INTO `tbl_drugsales` VALUES ('5e0b5eea21ce4606bec4ce482e3ca390', '1011', '7d21e4a9fd6a4179b9c73f536cbc885f', 'f2bc9dec5ccb4ab3a4bef7e32068bdf4', '12.00', '12.00', '1', '2017-03-19', '12');
INSERT INTO `tbl_drugsales` VALUES ('724d83a69b6a420d8930b1821c394125', '1007', '48671c120c9f43aaab5486597a34ef3e', '63408a229238460ea6783fa9b92bd205', '12.00', '10.00', '1', '2016-10-31', '12');
INSERT INTO `tbl_drugsales` VALUES ('72a9eaa8b61c4382a38bb322e88e8d99', '1015', '1718d22a5f9d41a2bbbe03bcdd427831', 'f2bc9dec5ccb4ab3a4bef7e32068bdf4', '18.90', '17.00', '1', '2017-03-19', '18.9');
INSERT INTO `tbl_drugsales` VALUES ('7cff9ab14bad44e0a36e83cb11efa5a5', '1012', '0731fa642c404d73849edc1a62a23b50', 'd3900eb8ac794b6792dde684ce091609', '21.00', '18.90', '2', '2017-03-19', '42');
INSERT INTO `tbl_drugsales` VALUES ('8834044e6671488f91d710047ffff41d', '1014', '1718d22a5f9d41a2bbbe03bcdd427831', '91a05f91c28b4a16aef1ccdbb3233861', '18.90', '17.00', '2', '2017-03-19', '37.8');
INSERT INTO `tbl_drugsales` VALUES ('8fb2c55ac31240dc94d7b83002bb41bf', '1008', '37f372b439bb47679ed19e21b15ebea9', '63408a229238460ea6783fa9b92bd205', '13.00', '12.00', '2', '2016-10-04', '24');
INSERT INTO `tbl_drugsales` VALUES ('aa9faf646e524a3f9e630e9f4a367034', '1017', 'b654926f9360490582bbd3cbfd59f0b3', 'd3900eb8ac794b6792dde684ce091609', '22.50', '20.30', '3', '2017-03-19', '67.5');
INSERT INTO `tbl_drugsales` VALUES ('d08fb5e544334080a14568456f64b40b', '1004', '37f372b439bb47679ed19e21b15ebea9', 'f2bc9dec5ccb4ab3a4bef7e32068bdf4', '12.00', '11.00', '1', '2016-10-30', '12');
INSERT INTO `tbl_drugsales` VALUES ('d1aa9b80c19843cca0735a8f47f5ba7c', '1001', '346cbdd663fc40649f0a464d8b9d57e0', '5ccced34dcb44c3a901ec3b330e88ee1', '12.00', '10.80', '2', '2017-03-19', '24');
INSERT INTO `tbl_drugsales` VALUES ('e8f086884dde4eac8890052063a455e9', '1003', '346cbdd663fc40649f0a464d8b9d57e0', '63408a229238460ea6783fa9b92bd205', '21.00', '22.00', '1', '2016-10-31', '21');
INSERT INTO `tbl_drugsales` VALUES ('f025494cc89843969e4640174ea347fd', '1013', '1718d22a5f9d41a2bbbe03bcdd427831', '91a05f91c28b4a16aef1ccdbb3233861', '21.00', '18.90', '1', '2017-03-19', '21');
INSERT INTO `tbl_drugsales` VALUES ('f3211d30bf2841ada5067b0c707697cf', '1005', '55740192f515473e8563c3647badaf88', 'd3900eb8ac794b6792dde684ce091609', '13.00', '10.00', '1', '2016-11-29', '13');
INSERT INTO `tbl_drugsales` VALUES ('f85970fa206e4e0c898486981f3ceb21', '1018', '1718d22a5f9d41a2bbbe03bcdd427831', 'f2bc9dec5ccb4ab3a4bef7e32068bdf4', '18.90', '17.00', '3', '2017-03-19', '56.7');

-- ----------------------------
-- Table structure for tbl_drugunit
-- ----------------------------
DROP TABLE IF EXISTS `tbl_drugunit`;
CREATE TABLE `tbl_drugunit` (
  `UNITNAME_ID` varchar(32) NOT NULL,
  `UNITNAME` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`UNITNAME_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_drugunit
-- ----------------------------
INSERT INTO `tbl_drugunit` VALUES ('192f6bdf72054caeb8e51b3d32efc2f0', '支');
INSERT INTO `tbl_drugunit` VALUES ('2bd550b99fd74988844db5195e713a4c', '瓶');
INSERT INTO `tbl_drugunit` VALUES ('2d8e61aeb94843e5a29765908fd251b5', '克');
INSERT INTO `tbl_drugunit` VALUES ('30fc216763bb41a88ecf2cc8ffe47b8d', '盒');
INSERT INTO `tbl_drugunit` VALUES ('33ebdc54e6134b7e9757902a2a49653e', '袋');
INSERT INTO `tbl_drugunit` VALUES ('5b5c40aef6c44c0e90cd02e6a48d2d42', '千克');
INSERT INTO `tbl_drugunit` VALUES ('b924e5ecc1534ac8a3700dcc3effa242', '包');
INSERT INTO `tbl_drugunit` VALUES ('fbe3a8e489e54231b16b0f56243c71f3', '胶囊');

-- ----------------------------
-- Table structure for tbl_employee
-- ----------------------------
DROP TABLE IF EXISTS `tbl_employee`;
CREATE TABLE `tbl_employee` (
  `EMP_ID` varchar(32) NOT NULL,
  `EMP_CODE` varchar(32) NOT NULL,
  `EMP_NAME` varchar(10) DEFAULT NULL,
  `SEX` char(1) DEFAULT NULL,
  `AGE` int(11) DEFAULT NULL,
  `TEL` char(11) DEFAULT NULL,
  `TITLE` varchar(20) DEFAULT NULL,
  `STARTDATE` date DEFAULT NULL,
  PRIMARY KEY (`EMP_ID`),
  KEY `AK_Key_2` (`EMP_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_employee
-- ----------------------------
INSERT INTO `tbl_employee` VALUES ('19ba1ca1c68741efabda407f30f94b17', '1002', '李四', '1', '23', '13254543345', '店长', '2011-10-25');
INSERT INTO `tbl_employee` VALUES ('320235316835476e8a0ca2219b6a300d', '1005', '陈晓军', '1', '23', '18865507365', '店员', '2016-10-27');
INSERT INTO `tbl_employee` VALUES ('6d8f164f71ff4c6ba40808071d7fd53c', '1004', '赵六', '1', '23', '13245454545', '店员', '2016-10-01');
INSERT INTO `tbl_employee` VALUES ('b3dfbcca95004eca8eb7ebb5edb85e03', '1003', '王五', '1', '34', '13165645145', '仓库管理员', '2016-02-28');
INSERT INTO `tbl_employee` VALUES ('c8662a36d59940d1ad9317bb0632a1bd', '1001', '张三', '1', '23', '18832432433', '店员', '2013-10-18');
INSERT INTO `tbl_employee` VALUES ('e0e80213b1e54526bdddd6aec0192b2e', '1006', '关羽', '1', '34', '13143243233', '销售员', '2016-02-24');

-- ----------------------------
-- Table structure for tbl_inventories
-- ----------------------------
DROP TABLE IF EXISTS `tbl_inventories`;
CREATE TABLE `tbl_inventories` (
  `STOCK_ID` varchar(32) NOT NULL,
  `STOCK_CODE` varchar(32) DEFAULT NULL,
  `DRUG_ID` varchar(32) DEFAULT NULL,
  `STOCKNUMBER` int(11) DEFAULT NULL,
  `STOCKLIMIT` int(11) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  PRIMARY KEY (`STOCK_ID`),
  KEY `AK_Key_2` (`STOCK_CODE`,`DRUG_ID`),
  KEY `FK_Reference_24` (`DRUG_ID`),
  CONSTRAINT `FK_Reference_24` FOREIGN KEY (`DRUG_ID`) REFERENCES `tbl_drug` (`DRUG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_inventories
-- ----------------------------
INSERT INTO `tbl_inventories` VALUES ('0bb74ce04ca044b88523c7099bc823c3', '1019', '093b0a2d54b046b4adeaa759ee57b0ed', '200', '100', '2017-03-22');
INSERT INTO `tbl_inventories` VALUES ('141cc318b1ea4401b4750c79c493ebc7', '1006', 'f9f6ecabbf944c5f89e240ef80ce2f09', '100', '50', '2017-03-21');
INSERT INTO `tbl_inventories` VALUES ('17f262be9d6d498ca339c7a947547501', '1010', '4b44d8a3acbd4a9080c97cb8ebf58239', '20', '50', '2017-03-21');
INSERT INTO `tbl_inventories` VALUES ('1e93319bfd50479e9021e6dafd7779aa', '1004', '9be3feca8cc740fb8ea9d863328782fb', '200', '50', '2017-03-21');
INSERT INTO `tbl_inventories` VALUES ('211233e1b5b9454aa8baf93cfaadac5a', '1008', 'b654926f9360490582bbd3cbfd59f0b3', '200', '50', '2017-03-21');
INSERT INTO `tbl_inventories` VALUES ('24059a0ad5a44034b02470bc04cfe7a4', '1014', '4c3f9bc466814c01bf0bad53aac53d39', '200', '50', '2017-03-21');
INSERT INTO `tbl_inventories` VALUES ('37da482020d7498d8150c0ad4329b8fd', '1009', '1718d22a5f9d41a2bbbe03bcdd427831', '200', '50', '2017-03-21');
INSERT INTO `tbl_inventories` VALUES ('41e5ee3213944f12b00b94a4b1bc498d', '1015', '3d366366288f42ad86c3c8fd2a749358', '200', '100', '2017-03-22');
INSERT INTO `tbl_inventories` VALUES ('47d1289bd7c34ca69bd6b0701dc0b336', '1018', 'af3bc498cdfa43a38ad3c2bc4d720ed8', '200', '100', '2017-03-22');
INSERT INTO `tbl_inventories` VALUES ('4dd7143bf7e84bbe9fb93c2ff6ec6084', '1016', '346cbdd663fc40649f0a464d8b9d57e0', '200', '100', '2017-03-22');
INSERT INTO `tbl_inventories` VALUES ('6f8cf0c4fd2a4d629308d6eeab43c0a9', '1001', '7d21e4a9fd6a4179b9c73f536cbc885f', '100', '60', '2017-03-21');
INSERT INTO `tbl_inventories` VALUES ('6fb17be214bb454d9f8f8952bf423e63', '1011', '982c0cd5d4b74e198461fd8d289876b3', '200', '40', '2017-03-21');
INSERT INTO `tbl_inventories` VALUES ('806793f3c48b40cbbb0eca4ce4ab825b', '1001', 'af3bc498cdfa43a38ad3c2bc4d720ed8', '200', '50', '2017-03-21');
INSERT INTO `tbl_inventories` VALUES ('83de8defc9b94dde9336f584b8e0f858', '1021', '6e8b3d717a7c44739249becee9ef36f6', '200', '100', '2017-03-22');
INSERT INTO `tbl_inventories` VALUES ('8973dcabee424c8fb3315cba7a11acf7', '1001', 'b1fd8870f0434770bdcf60af2abb0a9b', '100', '20', '2017-03-21');
INSERT INTO `tbl_inventories` VALUES ('94a44b742c0c4b0e82d8b77cf3671c22', '1017', 'af3bc498cdfa43a38ad3c2bc4d720ed8', '200', '50', '2017-03-22');
INSERT INTO `tbl_inventories` VALUES ('9b164e9e4d3948edb5761cb7b119104e', '1005', '48671c120c9f43aaab5486597a34ef3e', '100', '50', '2017-03-22');
INSERT INTO `tbl_inventories` VALUES ('a14c7bf0bad54d6eaf788944499ba4a2', '1020', 'a13213e8d4244fc5ada60c3173730e8a', '200', '100', '2017-03-22');
INSERT INTO `tbl_inventories` VALUES ('b0679bf72c2549b7b3486249b12dd2f2', '1002', '90afb0687de94660abaf4c304530d4e9', '200', '70', '2017-03-21');
INSERT INTO `tbl_inventories` VALUES ('b535581607a54f87aa2368465473bb70', '1007', '9b2a14f0deaa49fa952a28079e8de088', '100', '60', '2017-03-21');
INSERT INTO `tbl_inventories` VALUES ('c9b0f6349c0c44ab906c46c60b4ad974', '1001', 'b8097269b65a428ca720bc5f0723757c', '100', '60', '2017-03-21');
INSERT INTO `tbl_inventories` VALUES ('ce4a7c1ad4ae4ca1a0e624b1529271ca', '1012', '093b0a2d54b046b4adeaa759ee57b0ed', '200', '60', '2017-03-21');
INSERT INTO `tbl_inventories` VALUES ('d30a4c69ff58478ab163f57ca1ff4c02', '1002', '5c47ae53aa184596bc7f8e3ba69b00f6', '200', '60', '2017-03-21');
INSERT INTO `tbl_inventories` VALUES ('dc1326c8eb9c4235b3a8c9f2b9944066', '1002', '089bfa6cb5014252964d83bf3d8b42da', '200', '50', '2017-03-21');
INSERT INTO `tbl_inventories` VALUES ('e486b2ec885c44a18d18c579c28d3be7', '1003', 'bfcdc10c47e543da932dd1e808349cc6', '200', '60', '2017-03-21');
INSERT INTO `tbl_inventories` VALUES ('f9ea09400d8c4fb780ad07c45101bbaf', '1003', '37f372b439bb47679ed19e21b15ebea9', '200', '60', '2017-03-21');
INSERT INTO `tbl_inventories` VALUES ('f9f69818ed424c73a97213a60a4b7806', '1001', 'fbd60e24ba174719a3f2872e5c9052c2', '100', '60', '2017-03-21');

-- ----------------------------
-- Table structure for tbl_member
-- ----------------------------
DROP TABLE IF EXISTS `tbl_member`;
CREATE TABLE `tbl_member` (
  `MEMBER_ID` varchar(32) NOT NULL,
  `MEMBER_CODE` varchar(32) DEFAULT NULL,
  `MEMBER_NAME` varchar(10) DEFAULT NULL,
  `SEX` char(1) DEFAULT NULL,
  `AGE` varchar(3) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `MEMBER_LEVEL` char(10) DEFAULT NULL,
  `INTEGRATION` int(11) DEFAULT NULL,
  `SUPPLIERTEL` char(20) DEFAULT NULL,
  `ADDRESS` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`MEMBER_ID`),
  KEY `AK_Key_2` (`MEMBER_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_member
-- ----------------------------
INSERT INTO `tbl_member` VALUES ('039ee25cf6b54446a22fa589beb719fa', '1003', '李逵', '1', '34', '12', '387', '18142343243', '梁山');
INSERT INTO `tbl_member` VALUES ('117a31634e8c42ccb270f718e76334ec', '1007', '秦明', '1', '34', '10', '322', '18756787675', '清风寨');
INSERT INTO `tbl_member` VALUES ('3a0f382ffa32485691025ae13ece5970', '1008', '呼延灼', '1', '33', '3', '120', '15557878968', '青州');
INSERT INTO `tbl_member` VALUES ('55f1f96d0cb04cd69c186c6f4bb79c4b', '1002', '卢俊义', '1', '34', '5', '70', '15834234324', '北京城外');
INSERT INTO `tbl_member` VALUES ('754b2438e64049b8b56f393ad99932e0', '1006', '林冲', '1', '23', '3', '33', '13576765765', '东京');
INSERT INTO `tbl_member` VALUES ('78618ac677674340b8974bf697261379', '1005', '关胜', '1', '45', '3', '34', '1346565645', '大名府');
INSERT INTO `tbl_member` VALUES ('a490069dc4b144358ad1d4eecf662950', '1001', '宋江', '1', '23', '1', '12', '18865507178', '山东郓城县');
INSERT INTO `tbl_member` VALUES ('f92458c719424cc8939e52c7775b4573', '1004', '公孙胜', '1', '34', '3', '33', '13243543534', '河北蓟州人');

-- ----------------------------
-- Table structure for tbl_supplier
-- ----------------------------
DROP TABLE IF EXISTS `tbl_supplier`;
CREATE TABLE `tbl_supplier` (
  `SUPPLIER_ID` varchar(32) NOT NULL,
  `SUPPLIER_CODE` varchar(32) NOT NULL,
  `SUPPLIER` varchar(20) NOT NULL,
  `NAME` varchar(5) DEFAULT NULL,
  `TEL` char(11) DEFAULT NULL,
  `STATUS` char(1) DEFAULT NULL,
  PRIMARY KEY (`SUPPLIER_ID`),
  KEY `AK_Key_2` (`SUPPLIER_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_supplier
-- ----------------------------
INSERT INTO `tbl_supplier` VALUES ('070685898f5249aab322186f86ff6fa2', '1004', '北京国药控股医药有限公司', '小李', '15576767543', '1');
INSERT INTO `tbl_supplier` VALUES ('a27b311cf25442fb8328946c79a25ea4', '1005', '长沙双鹤医药有限公司', '小赵', '17854545444', '1');
INSERT INTO `tbl_supplier` VALUES ('b61a65e75062468996463f6b64a59f49', '1006', '武汉市马应龙医药有限公司', '小孙', '18757658892', '1');
INSERT INTO `tbl_supplier` VALUES ('bea77385a38a4b62b481b34cbe607409', '1001', '安徽华源医药股份有限公司', '张飞', '13154654654', '1');
INSERT INTO `tbl_supplier` VALUES ('cdf0d9c2f35a4d7a86365372d3ed9cd6', '1003', '杭州华东医药股份有限公司', '小刘', '13245455654', '1');
INSERT INTO `tbl_supplier` VALUES ('dfc36074c9a34dae9e8b8621587b1cad', '1002', '上海九洲通医药有限公司', '小王', '13143434344', '1');

-- ----------------------------
-- Table structure for tbl_users
-- ----------------------------
DROP TABLE IF EXISTS `tbl_users`;
CREATE TABLE `tbl_users` (
  `USER_ID` varchar(32) NOT NULL,
  `USER_CODE` int(11) DEFAULT NULL,
  `username` varchar(4) DEFAULT NULL,
  `PASSWORD` varchar(20) DEFAULT NULL,
  `addtime` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `nickname` varchar(40) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `AK_Key_2` (`USER_CODE`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_users
-- ----------------------------
INSERT INTO `tbl_users` VALUES ('15c54ab46f7f464cace4544fe31bcf5d', '1010', '王小二', '123456', '2017-02-22', '1', 'hihi', '111111@qq.com');
INSERT INTO `tbl_users` VALUES ('1a5bf4c926224a9cac0ad9645eda7b36', '1012', '张伯伦', '123456', '2017-03-20', '1', '波轮', 'qwe@qq.com');
INSERT INTO `tbl_users` VALUES ('52d4b1f3daaa44fcaa5c8812ec63e42c', '1003', '李四', '123456', '2016-10-31', '1', 'sousou', '23213@126.com');
INSERT INTO `tbl_users` VALUES ('5ccced34dcb44c3a901ec3b330e88ee1', '1004', '张三', '123456', '2016-10-31', '1', 'goto111', '23123@163.com');
INSERT INTO `tbl_users` VALUES ('63408a229238460ea6783fa9b92bd205', '1007', '关羽', '123456', '2016-10-28', '0', '2316wxc', 'sadss@163.com');
INSERT INTO `tbl_users` VALUES ('91a05f91c28b4a16aef1ccdbb3233861', '1006', '刘备', '123456', '2016-10-19', '1', 'ww2232', 'cwc21@189.com');
INSERT INTO `tbl_users` VALUES ('d3900eb8ac794b6792dde684ce091609', '1008', '赵六', '123456', '2016-11-02', '1', 'qwiod', '342323@163.com');
INSERT INTO `tbl_users` VALUES ('da94873627924883afd9c6616f266561', '1011', '李华', '123456', '2017-03-20', '1', 'xiaohua', '12345@163.com');
INSERT INTO `tbl_users` VALUES ('f2bc9dec5ccb4ab3a4bef7e32068bdf4', '1001', '张三', '123456', '2016-10-18', '1', 'mailk', '2132@126.com');
INSERT INTO `tbl_users` VALUES ('f767850333754e1c929eabb0a18af9ed', '1009', '王五', '123456', '2016-11-02', '1', 'wqdsd', '21321@sina.com');
