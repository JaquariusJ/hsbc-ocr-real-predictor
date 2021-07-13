CREATE TABLE `ocr_real_predictor` (
  `id` bigint(20) NOT NULL,
  `ocr_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ocr识别的字段',
  `ocr_value` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ocr识别的字段值',
  `ocr_boxes` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ocr识别的boxes',
  `ocr_texts` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ocr识别的texts',
  `comment` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '预留字段1',
  `value_type` varchar(125) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '预留字段2',
  `value` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '预留字段3',
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  `last_modified_user` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `version` bigint(20) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;