CREATE TABLE `ocr_real_predictor` (
  `id` bigint(20) NOT NULL,
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