INSERT INTO menus (id,"path","name",component,redirect,parent_id,status,title,icon,always_show,affix,no_cache,hidden,can_to,active_menu,breadcrumb,no_tags_view,sort,"type",create_time,update_time) VALUES
	 ('3d391027-6994-6558-3c0b-5e44b71e649f','menu','Menu','views/Authorization/Menu/Menu',NULL,'07c31770-d439-daa2-9a04-5c309ad99e5b',1,'菜单',NULL,false,false,true,false,false,NULL,true,false,5,1,'2024-04-29 21:46:51.84204',NULL),
	 ('07c31770-d439-daa2-9a04-5c309ad99e5b','/authorization','Authorization','#',NULL,NULL,1,'权限管理',NULL,true,false,false,false,false,NULL,true,false,4,0,'2024-04-29 21:46:51.84204',NULL),
	 ('d621c508-5d12-3948-5aaf-4007333192f2','analysis','Analysis','views/Dashboard/Analysis',NULL,'c420e68a-c8cd-04db-b707-9fb4584d1854',1,'java',NULL,false,false,true,false,false,NULL,true,false,3,1,'2024-04-29 21:46:51.84204','2024-04-29 22:04:42.945992'),
	 ('14d4cab6-f978-6847-8388-24aa9ab5eac3','workplace','Workplace','views/Dashboard/Workplace',NULL,'c420e68a-c8cd-04db-b707-9fb4584d1854',1,'你猜',NULL,false,false,true,false,false,NULL,true,true,2,1,'2024-04-29 21:46:51.84204','2024-04-29 22:07:17.376694'),
	 ('c420e68a-c8cd-04db-b707-9fb4584d1854','/dashboard','Dashboard','#','/dashboard/analysis',NULL,1,'首页','ant-design:dashboard-filled',true,false,false,false,true,NULL,true,false,1,0,'2024-04-29 21:46:51.84204','2024-04-29 22:07:46.641206');


insert into user_login ( user_name, "password") values ('admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

insert into roles ( role_name, remark, status) values ( '管理员', 'admin', 1);

insert into user_role ( user_id ,role_id)
select mu.id, r.id from user_login mu, roles r;

insert into role_menu (role_id, menu_id)
select r.id ,r2.id  from roles r, menus r2 ;

select * from sys_log order by start_time desc;
select * from user_login ul ;
select * from user_role ur ;
select * from roles r ;
select * from role_menu rm ;
select * from menus order by sort ; where "type" = '0';
delete from user_role;
delete from role_menu;
-----------------------------------------------------------------
