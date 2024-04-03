CREATE TABLE `leads` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `phone_number` varchar(64) NOT NULL COMMENT '电话号码',
  `email` varchar(64) NOT NULL COMMENT '邮件',
  `description` varchar(1024) DEFAULT NULL COMMENT '留言描述',
  `version` int NOT NULL,
  `extend_info` varchar(2048),
  `is_delete` int,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci