drop database if exists authority;
create database authority;
drop table if exists `user_account`;
create table user_account
(
    id          bigint       not null primary key auto_increment,
    account     varchar(255) not null comment '账号',
    passwd      varchar(255) not null comment '密码',
    salt        varchar(255) not null comment '盐',
    status      tinyint(4)   not null default 1 comment '状态 0是禁用、1是启用',
    create_time datetime,
    unique index user_account (account)
) engine = INNODB comment '用户账号表';

drop table if exists `account_role_ref`;
create table account_role_ref(
    id bigint not null primary key auto_increment,
    user_role_id bigint not null comment '角色ID',
    user_account_id bigint not null comment '用户账号ID',
    status tinyint(4) not null default 0 comment '状态 0是禁用 1是启用',
    create_time datetime,
    unique index ref(user_account_id, user_role_id)
) engine = INNODB comment '账号角色关联表';

drop table if exists `user_role`;
create table user_role
(
    id bigint not null primary key auto_increment comment '主键',
    role_name varchar(255) not null comment '角色名称',
    role_code varchar(255) not null comment '角色编码',
    create_time datetime
) engine = INNODB comment '角色表';

drop table if exists `role_resource_ref`;
create table role_resource_ref(
    id bigint not null primary key auto_increment,
    role_id bigint not null comment '角色Id',
    resource_id bigint not null comment '资源ID',
    status tinyint(4) not null default 0 comment '状态 0是禁用、1是启用'
)engine = INNODB comment '角色资源关联表';

drop table if exists `authority_resource`;
create table authority_resource(
    id bigint not null primary key auto_increment,
    uri varchar(255) not null default '' comment '请求路径',
    method varchar(50) not null default '' comment '请求方法',
    resource_code varchar(255) not null comment '资源编码',
    prev_id bigint not null default 0 comment '上一级id',
    menu_desc varchar(255) comment '菜单',
    status tinyint(4) not null default 0 comment '状态 0是禁用、1是启用',
    sort int not null default 0 comment '排序',
    index resource(uri,method)
)engine = INNODB comment '权限资源表';
