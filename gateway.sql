-- 步骤 1: 创建序列
CREATE SEQUENCE GID_NUM
START 1
INCREMENT BY 1;

-- 步骤 2: 创建函数
CREATE OR REPLACE FUNCTION gid_num_id()
RETURNS NUMERIC AS $$
DECLARE
    next_id BIGINT;
    fixed_part TEXT := '44108500000000';
BEGIN
    -- 获取序列的下一个值
    next_id := nextval('GID_NUM');
    
    -- 生成自定义ID，将固定部分与序列值结合
    RETURN fixed_part::NUMERIC * 100000000 + next_id; -- Adjust as per your numbering scheme
END;
$$ LANGUAGE plpgsql;

select gid_num_id();


create table user_login
(
    id                  numeric(24) default gateway.gid_num_id() not null
        primary key,
    user_name           varchar(50),
    password            varchar(200),
    create_time         timestamp   default now(),
    update_time         timestamp,
    name                varchar(50),
    status              integer,
    account_type_id     numeric(24),
    permission_type_id  numeric(24),
    app_organization_id numeric(24)
);

create table roles
(
    id          numeric(24) default gateway.gid_num_id() not null
        primary key,
    role_name   varchar(255)                             not null,
    remark      text,
    status      integer                                  not null
        constraint roles_status_check
            check (status = ANY (ARRAY [0, 1])),
    create_time timestamp   default CURRENT_TIMESTAMP,
    update_time timestamp
);

menus
(
    id           numeric(24) default gateway.gid_num_id() not null
    primary key,
    path         varchar(255)                             not null,
    name         varchar(255)
    unique,
    component    varchar(255),
    redirect     varchar(255),
    parent_id    numeric(24),
    status       integer     default 1
    constraint status_check
    check (status = ANY (ARRAY [0, 1])),
    title        varchar(255),
    icon         varchar(255),
    always_show  boolean     default false,
    affix        boolean     default false,
    no_cache     boolean     default false,
    hidden       boolean     default false,
    can_to       boolean     default false,
    active_menu  varchar(255),
    breadcrumb   boolean     default true,
    no_tags_view boolean     default false,
    sort         integer,
    type         integer,
    create_time  timestamp   default now(),
    update_time  timestamp
);

create table sys_log
(
    id             numeric(24) default gateway.gid_num_id() not null
        primary key,
    source         varchar(255),
    severity       varchar(50),
    oper_name      varchar(255),
    is_persistence boolean,
    url            varchar(255),
    method         varchar(255),
    remote_addr    varchar(255),
    request_method varchar(255),
    args           text,
    user_agent     varchar(255),
    result         text,
    start_time     timestamp,
    end_time       timestamp,
    create_time    timestamp   default now(),
    update_time    timestamp
);

create table user_role
(
    id          numeric(24) default gateway.gid_num_id() not null
        primary key,
    user_id     numeric(24)                              not null
        references user_login,
    role_id     numeric(24)                              not null
        references roles,
    create_time timestamp   default CURRENT_TIMESTAMP,
    update_time timestamp,
    unique (user_id, role_id)
);

create table role_menu
(
    id          numeric(24) default gateway.gid_num_id() not null
        primary key,
    role_id     numeric(24)                              not null
        references roles,
    menu_id     numeric(24)                              not null
        references menus,
    create_time timestamp   default CURRENT_TIMESTAMP,
    update_time timestamp,
    unique (role_id, menu_id)
);

create table app_organizations
(
    id          numeric(24) default gateway.gid_num_id() not null
        primary key,
    parentid    numeric(24),
    name        varchar(255)                             not null,
    level       integer,
    create_time timestamp   default now(),
    update_time timestamp
);

create table app_roles
(
    id          numeric(24) default gateway.gid_num_id() not null
        primary key,
    name        varchar(255),
    remark      varchar(255)                             not null,
    status      integer,
    app_id      numeric(24),
    create_time timestamp   default now(),
    update_time timestamp
);

create table user_app_roles
(
    id           numeric(24) default gateway.gid_num_id() not null
        primary key,
    user_id      numeric(24)                              not null,
    app_roles_id numeric(24)                              not null,
    create_time  timestamp   default CURRENT_TIMESTAMP,
    update_time  timestamp
);

create table app_region
(
    id          numeric(24) default gateway.gid_num_id() not null
        primary key,
    name        varchar(150),
    code        varchar(50),
    parent_id   numeric(24),
    create_time timestamp   default CURRENT_TIMESTAMP,
    update_time timestamp
);

create table user_app_region
(
    id            numeric(24) default gateway.gid_num_id() not null
        primary key,
    user_id       numeric(24)                              not null,
    app_region_id numeric(24)                              not null,
    create_time   timestamp   default CURRENT_TIMESTAMP,
    update_time   timestamp
);

-- ID	INT	主键，自增
-- code	VARCHAR(50)	字典项编码
-- name	VARCHAR(100)	字典项名称
-- description	VARCHAR(255)	字典项描述（可选）
-- sort_order	INT	排序顺序（可选）
-- IsActive	BOOLEAN	是否有效
-- CreatedAt	TIMESTAMP	创建时间
-- UpdatedAt	TIMESTAMP	更新时间
create table dictionary
(
    id          numeric(24) default gateway.gid_num_id() not null
        primary key,
    code        varchar(50)                              not null,
    name        varchar(100)                             not null,
    description varchar(255),
    sort_order  numeric(1000),
    status      numeric(1),
    create_time timestamp   default CURRENT_TIMESTAMP,
    update_time timestamp
);

create table apps
(
    id          numeric(24) default gateway.gid_num_id() not null
        primary key,
    name        varchar(100)                             not null,
    create_time timestamp   default CURRENT_TIMESTAMP,
    update_time timestamp
);

create table user_apps
(
    id          numeric(24) default gateway.gid_num_id() not null
        primary key,
    user_id     numeric(24)                              not null,
    apps_id     numeric(24)                              not null,
    create_time timestamp   default CURRENT_TIMESTAMP,
    update_time timestamp
);























