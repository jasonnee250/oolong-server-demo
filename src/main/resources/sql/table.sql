CREATE TABLE `user`
(
    `id`               bigint NOT NULL COMMENT 'id',
    `username`         varchar(100) DEFAULT NULL COMMENT '用户名',
    `password`         varchar(100) DEFAULT NULL COMMENT '密码',
    `is_locked`        int          DEFAULT NULL COMMENT '账户是否被锁定，0-false，1-true',
    `expired`          datetime     DEFAULT NULL COMMENT '账户失效时间',
    `password_expired` datetime     DEFAULT NULL COMMENT '密码失效时间',
    `is_enabled`       int          DEFAULT NULL COMMENT '账户是否可用，0-false，1-true',
    `created_at`       datetime     DEFAULT NULL COMMENT '创建时间',
    `updated_at`       datetime     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_username` (`username`) USING BTREE COMMENT '唯一索引'
) ENGINE=InnoDB;


CREATE TABLE `oauth_client_details`
(
    `client_id`               varchar(48) NOT NULL,
    `resource_ids`            varchar(256)  DEFAULT NULL,
    `client_secret`           varchar(256)  DEFAULT NULL,
    `scope`                   varchar(256)  DEFAULT NULL,
    `authorized_grant_types`  varchar(256)  DEFAULT NULL,
    `web_server_redirect_uri` varchar(256)  DEFAULT NULL,
    `authorities`             varchar(256)  DEFAULT NULL,
    `access_token_validity`   int           DEFAULT NULL,
    `refresh_token_validity`  int           DEFAULT NULL,
    `additional_information`  varchar(4096) DEFAULT NULL,
    `autoapprove`             varchar(256)  DEFAULT NULL,
    PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB;