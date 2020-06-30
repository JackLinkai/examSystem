/*
 Navicat Premium Data Transfer

 Source Server         : 119.3.15.128_3306
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 119.3.15.128:3306
 Source Schema         : exam

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 30/06/2020 15:40:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ClassRoom
-- ----------------------------
DROP TABLE IF EXISTS `ClassRoom`;
CREATE TABLE `ClassRoom`  (
  `RoomID` bigint(255) NOT NULL AUTO_INCREMENT,
  `RoomName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `GradeID` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`RoomID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ClassRoom
-- ----------------------------
INSERT INTO `ClassRoom` VALUES (1, '18级1班', 2);

-- ----------------------------
-- Table structure for Exam
-- ----------------------------
DROP TABLE IF EXISTS `Exam`;
CREATE TABLE `Exam`  (
  `ExamID` bigint(255) NOT NULL AUTO_INCREMENT,
  `StudentID` bigint(255) DEFAULT NULL,
  `PaperID` bigint(255) DEFAULT NULL,
  `StartOn` datetime(0) DEFAULT NULL,
  `EndOn` datetime(0) DEFAULT NULL,
  `IsMark` bigint(255) DEFAULT NULL,
  `TotalScore` double(5, 2) DEFAULT NULL,
  PRIMARY KEY (`ExamID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Exam
-- ----------------------------
INSERT INTO `Exam` VALUES (1, 1, 1, '2020-06-14 00:00:00', '2020-06-14 00:00:00', 1, 0.00);

-- ----------------------------
-- Table structure for ExamItem
-- ----------------------------
DROP TABLE IF EXISTS `ExamItem`;
CREATE TABLE `ExamItem`  (
  `ItemID` bigint(255) NOT NULL AUTO_INCREMENT,
  `ExamID` bigint(255) DEFAULT NULL,
  `QuestionID` bigint(255) DEFAULT NULL,
  `StuAnswer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `StdAnswer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `StdScore` double(5, 2) DEFAULT NULL,
  `MarkResult` bigint(255) DEFAULT NULL,
  `GainScore` double(5, 2) DEFAULT NULL,
  PRIMARY KEY (`ItemID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ExamItem
-- ----------------------------
INSERT INTO `ExamItem` VALUES (1, 1, 4, 'BD', 'B,D', 5.00, NULL, NULL);
INSERT INTO `ExamItem` VALUES (2, 1, 1, 'BD', 'F', 5.00, NULL, NULL);
INSERT INTO `ExamItem` VALUES (3, 1, 2, 'F', 'F', 5.00, 1, 5.00);
INSERT INTO `ExamItem` VALUES (4, 1, 3, 'E', 'F', 5.00, NULL, NULL);
INSERT INTO `ExamItem` VALUES (5, 1, 4, 'BD', 'BD', 5.00, 1, 5.00);

-- ----------------------------
-- Table structure for Grade
-- ----------------------------
DROP TABLE IF EXISTS `Grade`;
CREATE TABLE `Grade`  (
  `GradeId` bigint(255) NOT NULL AUTO_INCREMENT,
  `GradeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CreateOn` datetime(0) DEFAULT NULL,
  `CreateBy` bigint(255) DEFAULT NULL,
  `UpdateOn` datetime(0) DEFAULT NULL,
  `UpdateBy` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`GradeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Grade
-- ----------------------------
INSERT INTO `Grade` VALUES (1, '17', '2020-06-13 00:00:00', 0, '2020-06-13 00:00:00', 0);
INSERT INTO `Grade` VALUES (2, '18', '2020-06-13 00:00:00', 0, '2020-06-13 00:00:00', 0);
INSERT INTO `Grade` VALUES (3, '19', '2020-06-13 00:00:00', NULL, '2020-06-13 00:00:00', NULL);
INSERT INTO `Grade` VALUES (4, '20', '2020-06-13 00:00:00', NULL, '2020-06-13 00:00:00', NULL);

-- ----------------------------
-- Table structure for Manager
-- ----------------------------
DROP TABLE IF EXISTS `Manager`;
CREATE TABLE `Manager`  (
  `managerId` bigint(255) NOT NULL AUTO_INCREMENT,
  `LoginName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `LoginPwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `isDisabled` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`managerId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Manager
-- ----------------------------
INSERT INTO `Manager` VALUES (1, 'root', 'root', '赵六', 0);

-- ----------------------------
-- Table structure for Paper
-- ----------------------------
DROP TABLE IF EXISTS `Paper`;
CREATE TABLE `Paper`  (
  `PaperID` bigint(255) NOT NULL AUTO_INCREMENT,
  `PaperName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `TotalScore` double(5, 2) DEFAULT NULL,
  `PerScore` double(5, 2) DEFAULT NULL,
  `QuestionNum` bigint(255) DEFAULT NULL,
  `ExamMinute` bigint(255) DEFAULT NULL,
  `StartOn` datetime(0) DEFAULT NULL,
  `EndOn` datetime(0) DEFAULT NULL,
  `HasGenerate` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`PaperID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Paper
-- ----------------------------
INSERT INTO `Paper` VALUES (1, 'Java第一次测试', 20.00, 4.00, 5, 30, '2020-06-15 10:35:00', '2020-06-15 11:00:00', 0);

-- ----------------------------
-- Table structure for Paperitem
-- ----------------------------
DROP TABLE IF EXISTS `Paperitem`;
CREATE TABLE `Paperitem`  (
  `ItemID` bigint(255) NOT NULL AUTO_INCREMENT,
  `PaperID` bigint(255) DEFAULT NULL,
  `QuestionID` bigint(255) DEFAULT NULL,
  `Answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Score` double(5, 2) DEFAULT NULL,
  PRIMARY KEY (`ItemID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Paperitem
-- ----------------------------
INSERT INTO `Paperitem` VALUES (1, 1, 4, 'B,D', 5.00);
INSERT INTO `Paperitem` VALUES (2, 1, 3, 'F', 5.00);
INSERT INTO `Paperitem` VALUES (3, 1, 2, 'F', 5.00);
INSERT INTO `Paperitem` VALUES (4, 1, 1, 'F', 5.00);

-- ----------------------------
-- Table structure for Question
-- ----------------------------
DROP TABLE IF EXISTS `Question`;
CREATE TABLE `Question`  (
  `QuestionID` bigint(255) NOT NULL AUTO_INCREMENT,
  `QType` bigint(255) DEFAULT NULL,
  `Question` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ItemA` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ItemB` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ItemC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ItemD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ItemE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ItemF` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SubjectID` bigint(255) DEFAULT NULL,
  `Tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`QuestionID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Question
-- ----------------------------
INSERT INTO `Question` VALUES (1, 1, 'Java中的==符号和=符号意义相同', '', '', '', '', '对', '错', 'F', 1, '');
INSERT INTO `Question` VALUES (2, 1, 'Java中一个类可以有多个main方法', '', '', '', '', '对', '错', 'F', 1, '');
INSERT INTO `Question` VALUES (3, 1, 'Java的Object类的equals方法由==实现', '', '', '', '', '对', '错', 'F', 1, '');
INSERT INTO `Question` VALUES (4, 3, '以下Java的数据变量命名错误的是', 'array1', '2L', 'ToArr123', 'true', NULL, NULL, 'BD', 1, '');
INSERT INTO `Question` VALUES (5, 2, '关于final, finally, finalize，下面说法正确的是', 'final 用于声明属性，方法和类，分别表示属性不可变，方法不可覆盖，类不可继承', 'finally是异常处理语句结构的一部分，表示不可覆盖', 'finalize是Object类的一个方法，调用他可以执行垃圾回收机制回收对象', 'finally表示属性不可变，方法不可覆盖，类不可继承', NULL, NULL, 'a', 1, '');

-- ----------------------------
-- Table structure for Student
-- ----------------------------
DROP TABLE IF EXISTS `Student`;
CREATE TABLE `Student`  (
  `StudentID` bigint(255) NOT NULL AUTO_INCREMENT,
  `LoginName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `LoginPwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `IsDisabled` bigint(255) DEFAULT NULL,
  `RoomID` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`StudentID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Student
-- ----------------------------
INSERT INTO `Student` VALUES (1, '10010', '10010', '王五', 0, 1);

-- ----------------------------
-- Table structure for Subject
-- ----------------------------
DROP TABLE IF EXISTS `Subject`;
CREATE TABLE `Subject`  (
  `SubjectID` bigint(255) NOT NULL AUTO_INCREMENT,
  `SubjectName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Status` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`SubjectID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Subject
-- ----------------------------
INSERT INTO `Subject` VALUES (1, 'Java', 0);

-- ----------------------------
-- Table structure for Teacher
-- ----------------------------
DROP TABLE IF EXISTS `Teacher`;
CREATE TABLE `Teacher`  (
  `teacherId` bigint(255) NOT NULL AUTO_INCREMENT,
  `LoginName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `LoginPwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `isDisabled` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`teacherId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Teacher
-- ----------------------------
INSERT INTO `Teacher` VALUES (1, '12138', '12138', '张三', 0);

SET FOREIGN_KEY_CHECKS = 1;
