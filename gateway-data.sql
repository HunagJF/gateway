INSERT INTO menus2 (id,"path","name",component,redirect,parent_id,status,title,icon,always_show,affix,no_cache,hidden,can_to,active_menu) VALUES
	 ('c420e68a-c8cd-04db-b707-9fb4584d1854','/dashboard','Dashboard','#','/dashboard/analysis',NULL,1,'首页','ant-design:dashboard-filled',true,false,false,false,false,NULL),
	 ('d621c508-5d12-3948-5aaf-4007333192f2','analysis','Analysis','views/Dashboard/Analysis',NULL,'c420e68a-c8cd-04db-b707-9fb4584d1854',1,'首页1',NULL,false,false,true,false,false,NULL),
	 ('14d4cab6-f978-6847-8388-24aa9ab5eac3','workplace','Workplace','views/Dashboard/Workplace',NULL,'c420e68a-c8cd-04db-b707-9fb4584d1854',1,'首页2',NULL,false,false,true,false,false,NULL),
	 ('3d391027-6994-6558-3c0b-5e44b71e649f','menu','Menu','views/Authorization/Menu/Menu',NULL,'07c31770-d439-daa2-9a04-5c309ad99e5b',1,'菜单',NULL,false,false,true,false,false,NULL),
	 ('07c31770-d439-daa2-9a04-5c309ad99e5b','/authorization','Authorization','#',NULL,NULL,1,'权限管理',NULL,true,false,false,false,false,NULL);


insert into user_login ( user_name, "password") values ('admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

insert into roles ( role_name, remark, status) values ( '管理员', 'admin', 1);

insert into user_role ( user_id ,role_id)
select mu.id, r.id from user_login mu, roles r;

insert into role_menu (role_id, menu_id)
select r.id ,r2.id  from roles r, menus r2 ;

select * from user_login ul ;
select * from user_role ur ;
select * from roles r ;
select * from role_menu rm ;
select * from menus m ;
select * from menu_meta mm ;
select * from menus2;
delete from user_role;
delete from role_menu;




-----------------------------------------------------------------
select * from menus2;
insert into menus2 (id, "path","name",component,redirect,parent_id,status,title,icon,always_show,affix,no_cache,hidden,can_to,active_menu)
select 
m.id,
m."path",
m."name" ,
m."component" ,
m."redirect" ,
m."parent_id" ,
m."status" ,
mm.title ,
mm.icon ,
mm.always_show ,
mm.affix ,
mm.no_cache ,
mm.hidden ,
mm.can_to ,
mm.active_menu 
from menus m ,menu_meta mm where m.id = mm.menu_id;
