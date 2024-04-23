-- 顶级路由插入示例
INSERT INTO menus (id, path, name, component, redirect)
VALUES
  (uuid_generate_v4(),'/dashboard', 'Dashboard', '#', '/dashboard/analysis');

INSERT INTO menu_meta (id, menu_id, title, icon, always_show)
VALUES (uuid_generate_v4(),(SELECT id FROM menus WHERE name = 'Dashboard'), 
'首页', 'ant-design:dashboard-filled', TRUE);

-- 添加 Dashboard 的子路由 Analysis
INSERT INTO menus (id, path, name, component, parent_id)
VALUES (uuid_generate_v4(),'analysis', 'Analysis', 'views/Dashboard/Analysis', 
(SELECT id FROM menus WHERE name = 'Dashboard'));

-- 添加 Analysis 路由的元信息
INSERT INTO menu_meta (id, menu_id, title, no_cache)
VALUES (uuid_generate_v4(),(SELECT id FROM menus WHERE name = 'Analysis'), 
'首页1', TRUE);


-- 添加 Dashboard 的子路由 workplace
INSERT INTO menus (id, path, name, component, parent_id)
VALUES (uuid_generate_v4(),'workplace', 'Workplace', 'views/Dashboard/Workplace', 
(SELECT id FROM menus WHERE name = 'Dashboard'));

-- 添加 workplace 路由的元信息
INSERT INTO menu_meta (id, menu_id, title, no_cache)
VALUES (uuid_generate_v4(),(SELECT id FROM menus WHERE name = 'Workplace'), 
'首页2', TRUE);

-- 顶级路由插入示例
INSERT INTO menus (id, path, name, component, redirect)
VALUES
  (uuid_generate_v4(),'/authorization', 'Authorization', '#', null);

INSERT INTO menu_meta (id, menu_id, title, no_cache)
VALUES (uuid_generate_v4(),(SELECT id FROM menus WHERE name = 'Authorization'), 
'权限管理', TRUE);

INSERT INTO menus (id, path, name, component, parent_id)
VALUES (uuid_generate_v4(),'menu', 'Menu', 'views/Authorization/Menu/Menu', 
(SELECT id FROM menus WHERE name = 'Authorization'));

INSERT INTO menu_meta (id, menu_id, title, no_cache)
VALUES (uuid_generate_v4(),(SELECT id FROM menus WHERE name = 'Menu'), 
'菜单', TRUE);


--下载依赖
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


insert into user_login (id, user_name, "password") values (uuid_generate_v4(),'admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

insert into roles (id, role_name, remark, status) values (uuid_generate_v4(), '管理员', 'admin', 1);

insert into user_role (id, user_id, role_id)
select uuid_generate_v4(),mu.id, r.id from user_login mu, roles r;

insert into role_menu
select uuid_generate_v4(),r.id ,r2.id  from roles r, menus r2 ;

select * from user_login ul ;
select * from user_role ur ;
select * from roles r ;
select * from role_menu rm ;
select * from menus m ;
select * from menu_meta mm ;
