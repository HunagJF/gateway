INSERT INTO menus (id,"path","name",component,redirect,parent_id,status,title,icon,always_show,affix,no_cache,hidden,can_to,active_menu,breadcrumb,no_tags_view,sort,"type",create_time,update_time) VALUES
	 ('3d391027-6994-6558-3c0b-5e44b71e649f','menu','Menu','views/Authorization/Menu/Menu',NULL,'07c31770-d439-daa2-9a04-5c309ad99e5b',1,'菜单',NULL,false,false,true,false,false,NULL,true,false,5,1,'2024-04-29 21:46:51.84204',NULL),
	 ('07c31770-d439-daa2-9a04-5c309ad99e5b','/authorization','Authorization','#',NULL,NULL,1,'权限管理',NULL,true,false,false,false,false,NULL,true,false,4,0,'2024-04-29 21:46:51.84204',NULL),
	 ('d621c508-5d12-3948-5aaf-4007333192f2','analysis','Analysis','views/Dashboard/Analysis',NULL,'c420e68a-c8cd-04db-b707-9fb4584d1854',1,'java',NULL,false,false,true,false,false,NULL,true,false,3,1,'2024-04-29 21:46:51.84204','2024-04-29 22:04:42.945992'),
	 ('14d4cab6-f978-6847-8388-24aa9ab5eac3','workplace','Workplace','views/Dashboard/Workplace',NULL,'c420e68a-c8cd-04db-b707-9fb4584d1854',1,'你猜',NULL,false,false,true,false,false,NULL,true,true,2,1,'2024-04-29 21:46:51.84204','2024-04-29 22:07:17.376694'),
	 ('c420e68a-c8cd-04db-b707-9fb4584d1854','/dashboard','Dashboard','#','/dashboard/analysis',NULL,1,'首页','ant-design:dashboard-filled',true,false,false,false,true,NULL,true,false,1,0,'2024-04-29 21:46:51.84204','2024-04-29 22:07:46.641206');

INSERT INTO menus (id,"path","name",component,redirect,parent_id,status,title,icon,always_show,affix,no_cache,hidden,can_to,active_menu,breadcrumb,no_tags_view,sort,"type",create_time,update_time) VALUES
	 ('c420e68a-c8cd-04db-b707-9fb4584d1854','/dashboard','Dashboard','#','/dashboard/analysis',NULL,1,'首页','ant-design:dashboard-filled',true,false,false,false,true,NULL,true,false,1,0,'2024-04-29 21:46:51.84204','2024-05-07 21:49:48.253537'),
	 ('14d4cab6-f978-6847-8388-24aa9ab5eac3','workplace','Workplace','views/Dashboard/Workplace',NULL,'c420e68a-c8cd-04db-b707-9fb4584d1854',1,'你猜',NULL,false,false,true,false,false,NULL,true,false,2,1,'2024-04-29 21:46:51.84204','2024-05-08 21:00:59.122137'),
	 ('d621c508-5d12-3948-5aaf-4007333192f2','analysis','Analysis','views/Dashboard/Analysis',NULL,'c420e68a-c8cd-04db-b707-9fb4584d1854',1,'java',NULL,true,true,true,false,false,NULL,true,false,3,1,'2024-04-29 21:46:51.84204','2024-05-08 21:57:18.754962'),
	 ('07c31770-d439-daa2-9a04-5c309ad99e5b','/authorization','Authorization','#',NULL,NULL,1,'权限管理',NULL,true,false,false,false,false,NULL,true,false,4,0,'2024-04-29 21:46:51.84204',NULL),
	 ('3d391027-6994-6558-3c0b-5e44b71e649f','menu','Menu','views/Authorization/Menu/Menu',NULL,'07c31770-d439-daa2-9a04-5c309ad99e5b',1,'菜单',NULL,false,false,true,false,false,NULL,true,false,5,1,'2024-04-29 21:46:51.84204',NULL),
	 ('0fadbea2-0c0a-24ad-3d47-010a264e6cf6','/model','Model','#','/model/components/form/default-form',NULL,1,'案例',NULL,true,false,false,false,false,NULL,false,false,6,0,'2024-05-08 21:06:13.292083','2024-05-08 21:07:31.982078'),
	 ('a36613e5-e249-41a5-9ee6-edc6524c648e','components','ComponentsDemo','##',NULL,'0fadbea2-0c0a-24ad-3d47-010a264e6cf6',1,'组件',NULL,true,false,false,false,false,NULL,false,false,7,0,'2024-05-08 21:07:20.879543',NULL),
	 ('9551e08c-d0d4-135b-0a26-94ad33fcaf6f','form','Form','##',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'表单',NULL,true,false,false,false,false,NULL,false,false,8,0,'2024-05-08 21:09:05.291696',NULL),
	 ('7e48e089-324d-7783-eb8d-1b924a73cc42','default-form','DefaultForm','views/model/Components/Form/DefaultForm',NULL,'9551e08c-d0d4-135b-0a26-94ad33fcaf6f',1,'全部示例',NULL,false,false,true,false,false,NULL,false,false,9,1,'2024-05-08 21:15:37.921592',NULL),
	 ('3b6dfe6e-b612-af66-87d0-dc1e3853d3f3','use-form','UseForm','views/model/Components/Form/UseFormDemo',NULL,'9551e08c-d0d4-135b-0a26-94ad33fcaf6f',1,'UseForm',NULL,false,false,true,false,false,NULL,false,false,10,1,'2024-05-08 21:44:31.773065','2024-05-08 21:44:49.052318');
INSERT INTO menus (id,"path","name",component,redirect,parent_id,status,title,icon,always_show,affix,no_cache,hidden,can_to,active_menu,breadcrumb,no_tags_view,sort,"type",create_time,update_time) VALUES
	 ('0d151fbb-0817-daf2-fc6d-c019c005e950','table','TableDemo','##',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'表格',NULL,true,false,false,false,false,NULL,false,false,11,0,'2024-05-08 21:46:17.378042','2024-05-08 21:46:32.408614'),
	 ('11f4d952-7242-2070-f064-81538697d8b9','default-table','DefaultTable','views/model/Components/Table/DefaultTable',NULL,'0d151fbb-0817-daf2-fc6d-c019c005e950',1,'基础示例',NULL,false,false,true,false,false,NULL,false,false,12,1,'2024-05-08 21:47:23.690391','2024-05-08 21:47:28.52603'),
	 ('93b87065-8fe0-2847-3298-2ab5ffa6d79d','use-table','UseTable','views/model/Components/Table/UseTableDemo',NULL,'0d151fbb-0817-daf2-fc6d-c019c005e950',1,'UseTable',NULL,false,false,true,false,false,NULL,false,false,13,1,'2024-05-08 21:48:15.907309','2024-05-08 21:48:22.993142'),
	 ('d15d17e7-00f6-6c8e-c087-b7546c2f725f','tree-table','TreeTable','views/model/Components/Table/TreeTable',NULL,'0d151fbb-0817-daf2-fc6d-c019c005e950',1,'TreeTable',NULL,false,false,true,false,false,NULL,false,false,14,1,'2024-05-08 21:49:01.693737',NULL),
	 ('1f7d2ce7-b29c-9811-ede8-d7050783f701','table-image-preview','TableImagePreview','views/model/Components/Table/TableImagePreview',NULL,'0d151fbb-0817-daf2-fc6d-c019c005e950',1,'表格图片预览',NULL,false,false,true,false,false,NULL,false,false,15,1,'2024-05-08 21:49:40.966629',NULL),
	 ('ea57eabe-9c38-f339-ad54-fbf63b4d3708','table-video-preview','TableVideoPreview','views/model/Components/Table/TableVideoPreview',NULL,'0d151fbb-0817-daf2-fc6d-c019c005e950',1,'表格视频预览',NULL,false,false,true,false,false,NULL,false,false,16,1,'2024-05-08 21:50:16.945828',NULL),
	 ('6892e7af-fbb8-e66d-043e-5f55588929a0','card-table','CardTable','views/model/Components/Table/CardTable',NULL,'0d151fbb-0817-daf2-fc6d-c019c005e950',1,'卡片表格',NULL,false,false,true,false,false,NULL,false,false,17,1,'2024-05-08 21:50:49.423525',NULL);

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
