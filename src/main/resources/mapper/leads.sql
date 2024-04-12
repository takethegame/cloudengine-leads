 CREATE TABLE `leads` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `name` varchar(128) NOT NULL COMMENT '姓名',
  `phone_number` varchar(64) NOT NULL COMMENT '电话号码',
  `email` varchar(64) NOT NULL COMMENT '邮件',
  `description` varchar(1024) DEFAULT NULL COMMENT '留言描述',
  `version` int NOT NULL,
  `extend_info` varchar(2048) DEFAULT NULL,
  `is_delete` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_phone_number` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci