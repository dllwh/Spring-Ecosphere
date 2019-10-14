CREATE TABLE funny
(
   id INT (11) UNSIGNED NOT NULL AUTO_INCREMENT,
   article_genre VARCHAR (255),
   behot_time TIMESTAMP NULL DEFAULT NULL,
   chinese_tag VARCHAR (255),
   group_id VARCHAR (255),
   has_gallery VARCHAR (255),
   image_url VARCHAR (255),
   is_feed_ad VARCHAR (255),
   media_avatar_url VARCHAR (255),
   media_url VARCHAR (255),
   middle_mode VARCHAR (255),
   more_mode VARCHAR (255),
   single_mode VARCHAR (255),
   source VARCHAR (255),
   source_url VARCHAR (255),
   tag VARCHAR (255),
   tag_url VARCHAR (255),
   title VARCHAR (255),
   comments_count VARCHAR (255),
   document VARCHAR (255),
   CONSTRAINT PK_funny PRIMARY KEY (id),
   UNIQUE KEY uq_ group_id (group_id) USING BTREE
)
ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin;