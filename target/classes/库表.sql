CREATE TABLE `user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户主键id，自增',
    `username` varchar(32) NOT NULL COMMENT '用户名称',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=6285868 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `user_memory` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `username` varchar(32) NOT NULL,
    `create_time` datetime NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=MEMORY AUTO_INCREMENT=6285868 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
