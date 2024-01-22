SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `sex` tinyint UNSIGNED NOT NULL COMMENT '性别:0-女;1-男',
  `telephone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `is_deleted` tinyint UNSIGNED NULL DEFAULT 0 COMMENT '是否删除:1 表示删除;0 表示未删除',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username_is_deleted`(`username` ASC, `is_deleted` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Bob', '123456', 1, '12345678900', 0, '2024-01-09 10:18:49', '2024-01-09 10:18:49');
INSERT INTO `user` VALUES (2, 'Amy', '123789', 0, '12378914566', 0, '2024-01-09 10:18:49', '2024-01-09 10:18:49');
INSERT INTO `user` VALUES (3, 'Sam', '112233', 1, '12312312345', 0, '2024-01-09 10:18:49', '2024-01-09 10:18:49');

SET FOREIGN_KEY_CHECKS = 1;
