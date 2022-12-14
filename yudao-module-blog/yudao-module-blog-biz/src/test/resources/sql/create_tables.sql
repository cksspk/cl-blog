-- 将该建表 SQL 语句，添加到 yudao-module-blog-biz 模块的 test/resources/sql/create_tables.sql 文件里
CREATE TABLE IF NOT EXISTS "blog_tag" (
                                          "id" bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
                                          "color" varchar,
                                          "title" varchar NOT NULL,
                                          "creator" varchar DEFAULT '',
                                          "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                          "updater" varchar DEFAULT '',
                                          "update_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                          "deleted" bit NOT NULL DEFAULT FALSE,
                                          PRIMARY KEY ("id")
    ) COMMENT '博客标签表';


