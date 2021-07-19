/**
    ocr识别结果单项表
 */
CREATE TABLE `ocr_result_item` (
  `id` varchar(100) NOT NULL,
  `doc_id` varchar(100) NOT NULL COLLATE utf8mb4_bin,
  `ocr_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ocr_name',
  `ocr_value` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ocr_value',
  `ocr_boxes` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ocr_boxes',
  `ocr_texts` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ocr_texts',
  `comment` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'redundance 1',
  `value_type` varchar(125) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'redundance 2',
  `value` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'redundance 3',
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  `last_modified_user` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `version` bigint(20) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


/**
   文档请求信息表
 */
CREATE TABLE `ocr_predictor_info` (
  `id` varchar(100) NOT NULL,
  `doc_type` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'doc_type',
  `sub_type` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'doc_sub_type',
  `file_size` bigint(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'file_size',
  `file_type` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'file_type',
  `file_md5` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'file_md5',
  `file_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'file_name',
  `oss_path` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'doc_data',
  `data` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'doc_data',
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  `last_modified_user` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `version` bigint(20) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


/**
    ocr原始结果信息
 */
CREATE TABLE `ocr_origin_result` (
  `id` varchar(100) NOT NULL,
  `ocr_result` text COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ocr_result',
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  `last_modified_user` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `version` bigint(20) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


/**
    ocr预估的运行信息
 */
CREATE TABLE `ocr_predictor_runtime` (
  `id` varchar(100) NOT NULL,
  `doc_id` varchar(100) NOT NULL,
  `time_type` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'time_type',
  `class_name` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'class_name',
  `method_name` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'method_name',
  `message` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'message',
  `millisecond` bigint(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'millisecond',
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  `last_modified_user` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `version` bigint(20) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;