/*
 Navicat MySQL Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : music

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 08/02/2019 14:38:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_list_music
-- ----------------------------
DROP TABLE IF EXISTS `tb_list_music`;
CREATE TABLE `tb_list_music`  (
  `lid` int(11) NOT NULL COMMENT '外键 歌单id 参考musiclists表中的list_id',
  `mid` int(11) NOT NULL COMMENT '外键 歌曲id 参考musics表中的music_id',
  UNIQUE INDEX `去重`(`lid`, `mid`) USING BTREE,
  INDEX `mid`(`mid`) USING BTREE,
  CONSTRAINT `tb_list_music_ibfk_1` FOREIGN KEY (`lid`) REFERENCES `tb_musiclists` (`list_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_list_music_ibfk_2` FOREIGN KEY (`mid`) REFERENCES `tb_musics` (`music_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '123' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_list_music
-- ----------------------------
INSERT INTO `tb_list_music` VALUES (1, 29);

-- ----------------------------
-- Table structure for tb_musiclists
-- ----------------------------
DROP TABLE IF EXISTS `tb_musiclists`;
CREATE TABLE `tb_musiclists`  (
  `list_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '歌单id标识，自动递增',
  `list_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '歌单名',
  `list_desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '歌单描述',
  `list_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '自动根据数据创建时间写将时间写入到数据中',
  `list_uid` int(11) NOT NULL COMMENT '歌单所属用户id。外键。参考users表中的user_id ',
  PRIMARY KEY (`list_id`) USING BTREE,
  INDEX `list_uid`(`list_uid`) USING BTREE,
  CONSTRAINT `tb_musiclists_ibfk_1` FOREIGN KEY (`list_uid`) REFERENCES `tb_users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_musiclists
-- ----------------------------
INSERT INTO `tb_musiclists` VALUES (1, 'a', 'dd', '2019-02-05 21:19:16', 103);
INSERT INTO `tb_musiclists` VALUES (13, '测试歌单', '测试歌单', '2019-02-05 22:40:16', 104);

-- ----------------------------
-- Table structure for tb_musics
-- ----------------------------
DROP TABLE IF EXISTS `tb_musics`;
CREATE TABLE `tb_musics`  (
  `music_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '音乐id，自动递增',
  `music_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '音乐名',
  `music_art` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '艺术家',
  `music_album` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专辑',
  `music_path` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '路径',
  PRIMARY KEY (`music_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_musics
-- ----------------------------
INSERT INTO `tb_musics` VALUES (28, '三月雨', '泠鸢', '5sing', 'mp3/001.mp3');
INSERT INTO `tb_musics` VALUES (29, '恋爱循环', '花泽香菜', '物语', 'mp3/002.mp3');
INSERT INTO `tb_musics` VALUES (30, '盛夏的果实', '莫文蔚', '未知', 'mp3/003.mp3');
INSERT INTO `tb_musics` VALUES (33, '断桥残雪', '许嵩', '线上', 'https://api.mlwei.com/music/api/wy/?key=523077333&cache=1&type=url&id=167937');

-- ----------------------------
-- Table structure for tb_users
-- ----------------------------
DROP TABLE IF EXISTS `tb_users`;
CREATE TABLE `tb_users`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id标识，自动递增',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登陆账号，不可重复',
  `user_pwd` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `user_nick` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `user_sex` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `user_pic` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像路径',
  `user_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_name唯一`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_users
-- ----------------------------
INSERT INTO `tb_users` VALUES (103, '测试账户123', '123123123', '测试账户123昵称', '男', '1547741849850.jpg', '个性签名123');
INSERT INTO `tb_users` VALUES (104, '123', '123', '测试账户2', '男', '1547742452751.jpg', '个性签名内容待定2');
INSERT INTO `tb_users` VALUES (107, '发射点发射点发射点发送', '123123123', '123123123', '男', '1547778966031.jpg', '222222');

SET FOREIGN_KEY_CHECKS = 1;
