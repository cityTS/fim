CREATE DATABASE IF NOT EXISTS fim;

use fim;

CREATE TABLE IF NOT EXISTS `user`
(
    `user_account`  BIGINT PRIMARY KEY COMMENT '用户账号',
    `user_password` VARCHAR(255) NOT NULL COMMENT '用户密码',
    `username`      VARCHAR(255) NOT NULL COMMENT '用户昵称',
    `user_phone`    VARCHAR(255) NOT NULL unique COMMENT '用户手机号',
    `user_email`    VARCHAR(255) NOT NULL unique COMMENT '用户邮箱',
    `user_sex`      CHAR(1)      NOT NULL COMMENT '用户性别' DEFAULT '男',
    `user_birth`    BIGINT       NOT NULL                    DEFAULT 1672502400 COMMENT '用户生日',
    `user_frozen`   BOOL         NOT NULL                    DEFAULT FALSE COMMENT '账号状态',
    `created_at`    BIGINT       NOT NULL COMMENT '创建时间',
    `avatar_url`    VARCHAR(255) NOT NULL                    DEFAULT 'https://picx.zhimg.com/80/v2-2e1641a8fb38884c8b185ee293d5ae12_720w.webp?source=1940ef5c' COMMENT '头像链接'
) ENGINE = InnoDB COMMENT '用户模型'
  DEFAULT CHARSET = utf8;


CREATE TABLE IF NOT EXISTS `group`
(
    `group_chat_account` BIGINT PRIMARY KEY COMMENT '群号',
    `group_chat_name`    VARCHAR(255) NOT NULL COMMENT '群名称',
    `group_chat_status`  INT(4)       NOT NULL COMMENT '群状态 0:正常 1:禁止加入 2:解散 3:违规解散' DEFAULT 0,
    `created_at`         BIGINT       NOT NULL COMMENT '创建时间',
    `group_leader_id`    BIGINT       NOT NULL COMMENT '群主ID',
    FOREIGN KEY (group_leader_id) REFERENCES user (user_account)
) ENGINE = InnoDB COMMENT '群模型'
  DEFAULT CHARSET = utf8;


CREATE TABLE IF NOT EXISTS `group_user`
(
    `id`                INTEGER PRIMARY KEY AUTO_INCREMENT COMMENT '映射关系ID',
    `user_id`           BIGINT NOT NULL COMMENT '用户ID',
    `group_chat_id`     BIGINT NOT NULL COMMENT '群ID',
    `group_chat_status` INT(2) NOT NULL COMMENT '映射关系 0：群成员 1：退出群聊',
    `created_at`        BIGINT NOT NULL COMMENT '创建时间',
    FOREIGN KEY (user_id) REFERENCES user (user_account),
    FOREIGN KEY (group_chat_id) REFERENCES `group` (group_chat_account),
    index group_chat_id_index (group_chat_id, group_chat_status)
) ENGINE = InnoDB COMMENT '群成员模型'
  DEFAULT CHARSET = utf8;


CREATE TABLE IF NOT EXISTS `relation`
(
    `id`                INTEGER PRIMARY KEY AUTO_INCREMENT COMMENT '关系ID',
    `user_id`           BIGINT       NOT NULL COMMENT '用户账号',
    `friend_id`         BIGINT       NOT NULL COMMENT '好友账号',
    `relationship_time` BIGINT       NOT NULL COMMENT '成为好友时间',
    `strange_time`      BIGINT       NULL COMMENT '删除好友时间',
    `friend_nickname`   VARCHAR(255) NULL COMMENT '备注昵称',
    FOREIGN KEY (user_id) REFERENCES user (user_account),
    FOREIGN KEY (friend_id) REFERENCES user (user_account)
) ENGINE = InnoDB COMMENT '好友关系模型'
  DEFAULT CHARSET = utf8;


CREATE TABLE IF NOT EXISTS `admin`
(
    `id`             INTEGER PRIMARY KEY AUTO_INCREMENT COMMENT '管理员编号',
    `admin_name`     VARCHAR(255) NOT NULL unique COMMENT '用户姓名',
    `admin_password` VARCHAR(255) NOT NULL COMMENT '用户密码',
    `admin_phone`    VARCHAR(255) NOT NULL COMMENT '用户手机',
    `admin_email`    VARCHAR(255) NOT NULL COMMENT '用户邮箱',
    `createdAt`      BIGINT       NOT NULL COMMENT '创建时间'
) ENGINE = InnoDB COMMENT '管理员模型'
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `apply`
(
    `id`             INTEGER PRIMARY KEY AUTO_INCREMENT COMMENT '申请ID',
    `sponsor_id`     BIGINT  NOT NULL COMMENT '发起用户id',
    `recipient_id`   BIGINT  NOT NULL COMMENT '被申请者id',
    `sponsor_time`   BIGINT  NOT NULL COMMENT '发起时间',
    `sponsor_status` INTEGER NOT NULL DEFAULT 2 COMMENT '请求状态：0:拒绝，1:同意2:未处理',
    FOREIGN KEY (sponsor_id) REFERENCES user (user_account)
) ENGINE = InnoDB COMMENT '申请模型'
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `accounts`
(
    `account` BIGINT NOT NULL COMMENT '账号',
    `status`  BOOL   NOT NULL DEFAULT false COMMENT '该账号的状态'
) ENGINE = InnoDB COMMENT '账号库'
  DEFAULT CHARSET = utf8;