CREATE TABLE `flowable_process_record` (
   `uuid` varchar(63) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'uuid',
   `process_instance_id` varchar(63) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '流程实例id',
   `process_definition_key` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程定义key',
   `process_definition_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '流程定义名称',
   `status` varchar(63) COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程状态',
   `resource_uuid` varchar(63) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '资源uuid',
   `resource_type` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '资源类型',
   `start_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '流程开始时间',
   `end_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '流程结束时间',
   `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   `create_user` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
   `update_user` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
   `active` tinyint(1) DEFAULT NULL COMMENT '流程是否存活',
   `message` text COLLATE utf8mb4_general_ci COMMENT '错误信息',
   PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='流程记录信息表';