/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : library

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-04-21 17:20:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `bookID` int(11) NOT NULL AUTO_INCREMENT,
  `bookName` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `amount` int(255) DEFAULT NULL,
  `bookIntroduce` varchar(1000) DEFAULT NULL,
  `authorIntroduce` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`bookID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '时间简史', '霍金', '2016年', '0', '123', '123');
INSERT INTO `book` VALUES ('2', '123', '123', '123', '0', '123', '123');
INSERT INTO `book` VALUES ('3', '123', '123', '123', '121', '123', '123');

-- ----------------------------
-- Table structure for booking
-- ----------------------------
DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking` (
  `bookingID` int(11) NOT NULL AUTO_INCREMENT,
  `bookID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `bookingTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bookingID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of booking
-- ----------------------------
INSERT INTO `booking` VALUES ('3', '1', '3', '2019-04-11');
INSERT INTO `booking` VALUES ('4', '3', '3', '2019-04-26');

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow` (
  `borrowID` int(11) NOT NULL AUTO_INCREMENT,
  `bookID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `startTime` date DEFAULT NULL,
  `endTime` date DEFAULT NULL,
  PRIMARY KEY (`borrowID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES ('6', '1', '1', '2018-11-15', '2018-12-15');
INSERT INTO `borrow` VALUES ('7', '1', '6', '2018-11-16', '2018-12-16');
INSERT INTO `borrow` VALUES ('8', '2', '6', '2018-11-16', '2018-12-16');
INSERT INTO `borrow` VALUES ('9', '3', '3', '2019-04-21', '2019-05-21');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `collectID` int(11) NOT NULL AUTO_INCREMENT,
  `bookID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  PRIMARY KEY (`collectID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('2', '1', '1');
INSERT INTO `collect` VALUES ('3', '1', '1');
INSERT INTO `collect` VALUES ('4', '1', '6');
INSERT INTO `collect` VALUES ('6', '2', '6');
INSERT INTO `collect` VALUES ('7', '3', '3');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `managerID` int(11) NOT NULL AUTO_INCREMENT,
  `managerName` varchar(255) DEFAULT NULL,
  `managerPassword` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`managerID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('4', '1', '1');
INSERT INTO `manager` VALUES ('5', '2', '2');
INSERT INTO `manager` VALUES ('9', '5', '5');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) DEFAULT NULL,
  `userPassword` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', '222', '123456');
INSERT INTO `user` VALUES ('4', '333', '1111111111');
INSERT INTO `user` VALUES ('6', '123', '1111111111');
