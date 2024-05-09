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
	 ('6892e7af-fbb8-e66d-043e-5f55588929a0','card-table','CardTable','views/model/Components/Table/CardTable',NULL,'0d151fbb-0817-daf2-fc6d-c019c005e950',1,'卡片表格',NULL,false,false,true,false,false,NULL,false,false,17,1,'2024-05-08 21:50:49.423525',NULL),
	 ('dadcc7c9-f59f-ff94-4f5b-e8b36030210d','editor-demo','EditorDemo','##',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'编辑器',NULL,true,false,false,false,false,NULL,false,false,18,0,'2024-05-09 14:58:31.755435',NULL),
	 ('e0a2b76e-c799-ba36-2031-5c55c3ebaa24','editor','Editor','views/model/Components/Editor/Editor',NULL,'dadcc7c9-f59f-ff94-4f5b-e8b36030210d',1,'富文本编辑器',NULL,false,false,true,false,false,NULL,false,false,19,1,'2024-05-09 14:59:27.007152',NULL),
	 ('9a006e12-7026-ce3f-5cf3-e55004622617','json-editor','JsonEditor','views/model/Components/Editor/JsonEditor',NULL,'dadcc7c9-f59f-ff94-4f5b-e8b36030210d',1,'JSON编辑器',NULL,false,false,true,false,false,NULL,false,false,20,1,'2024-05-09 15:00:10.411828',NULL);
INSERT INTO menus (id,"path","name",component,redirect,parent_id,status,title,icon,always_show,affix,no_cache,hidden,can_to,active_menu,breadcrumb,no_tags_view,sort,"type",create_time,update_time) VALUES
	 ('c80058e6-7aed-54a3-86ce-820401904189','search','Search','views/model/Components/Search',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'查询',NULL,false,false,true,false,false,NULL,false,false,21,1,'2024-05-09 15:01:52.75091',NULL),
	 ('8d4dc48c-a0ff-4be3-7cf1-35f9a62b0ea2','descriptions','Descriptions','views/model/Components/Descriptions',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'描述',NULL,false,false,true,false,false,NULL,false,false,22,1,'2024-05-09 15:42:53.186449',NULL),
	 ('509e2847-3b91-2bb1-2c40-2df953d6a2fc','image-viewer','ImageViewer','views/model/Components/ImageViewer',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'图片预览',NULL,false,false,true,false,false,NULL,false,false,23,1,'2024-05-09 15:43:46.029713',NULL),
	 ('ed8cecd7-d4d5-df99-4699-10fe9a9a59aa','dialog','Dialog','views/model/Components/Dialog',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'弹窗',NULL,false,false,true,false,false,NULL,false,false,24,1,'2024-05-09 15:44:19.553161',NULL),
	 ('e25b497e-7317-8bea-6f96-cb4e8ad3f6be','icon','Icon','views/model/Components/Icon',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'图标',NULL,false,false,true,false,false,NULL,false,false,25,1,'2024-05-09 15:44:57.531423',NULL),
	 ('f2774912-26c4-9cf9-f1ef-ff22ef60f485','icon-picker','IconPicker','views/model/Components/IconPicker',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'图标选择器',NULL,false,false,true,false,false,NULL,false,false,26,1,'2024-05-09 15:46:12.634391',NULL),
	 ('c1655858-a655-287b-89f0-b85cc86ab900','echart','Echart','views/model/Components/Echart',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'图表',NULL,false,false,true,false,false,NULL,false,false,27,1,'2024-05-09 15:48:46.541315',NULL),
	 ('601646df-5f51-2edc-3eba-7c4a245a0994','count-to','CountTo','views/model/Components/CountTo',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'数字动画',NULL,false,false,true,false,false,NULL,false,false,28,1,'2024-05-09 15:51:34.232096',NULL),
	 ('e145752b-4c79-64ed-d43d-96fb6b7da8f9','qrcode','Qrcode','views/model/Components/Qrcode',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'二维码',NULL,false,false,true,false,false,NULL,false,false,29,1,'2024-05-09 15:52:10.834426',NULL),
	 ('df627837-6337-626b-4666-a2e6b6ae097c','highlight','Highlight','views/model/Components/Highlight',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'高亮',NULL,false,false,true,false,false,NULL,false,false,30,1,'2024-05-09 15:55:16.3542',NULL);
INSERT INTO menus (id,"path","name",component,redirect,parent_id,status,title,icon,always_show,affix,no_cache,hidden,can_to,active_menu,breadcrumb,no_tags_view,sort,"type",create_time,update_time) VALUES
	 ('2e155900-2e85-b56d-45f6-7b5e7164e251','infotip','Infotip','views/model/Components/Infotip',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'信息提示',NULL,false,false,true,false,false,NULL,false,false,31,1,'2024-05-09 15:55:52.006229',NULL),
	 ('193dc7d2-e709-7d02-b7b5-433248c96ffa','input-password','InputPassword','views/model/Components/InputPassword',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'密码输入框',NULL,false,false,true,false,false,NULL,false,false,32,1,'2024-05-09 15:58:07.082877',NULL),
	 ('79f45761-e249-2a4b-61b4-f8e0d0922779','waterfall','Waterfall','views/model/Components/Waterfall',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'瀑布流',NULL,false,false,true,false,false,NULL,false,false,33,1,'2024-05-09 15:58:38.312942',NULL),
	 ('fcaade0d-38b0-477e-da11-b2724b854325','image-cropping','ImageCropping','views/model/Components/ImageCropping',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'图片裁剪',NULL,false,false,true,false,false,NULL,false,false,34,1,'2024-05-09 15:59:13.299665',NULL),
	 ('7dcdcf89-9e75-2fd1-0ece-4b04a60aae9e','video-player','VideoPlayer','views/model/Components/VideoPlayer',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'视频播放器',NULL,false,false,true,false,false,NULL,false,false,35,1,'2024-05-09 15:59:45.537174',NULL),
	 ('4af54d8f-b41a-286e-1e0d-046a95d0f248','avatars','Avatars','views/model/Components/Avatars',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'头像列表',NULL,false,false,true,false,false,NULL,false,false,36,1,'2024-05-09 16:00:21.033128',NULL),
	 ('7099562c-2112-7464-abc6-8a6a9033ec43','i-agree','IAgree','views/model/Components/IAgree',NULL,'a36613e5-e249-41a5-9ee6-edc6524c648e',1,'我同意',NULL,false,false,true,false,false,NULL,false,false,37,1,'2024-05-09 16:00:52.19633',NULL),
	 ('eb8cd725-09f1-9992-aabf-1a7f6b4cc5c9','function','Function','##',NULL,'0fadbea2-0c0a-24ad-3d47-010a264e6cf6',1,'功能','ri:function-fill',true,false,false,false,false,NULL,false,false,38,0,'2024-05-09 16:03:06.054326',NULL),
	 ('b2efc652-e412-94d5-ca2e-50ec90ac0023','multipleTabs','MultipleTabs','views/model/Function/MultipleTabs',NULL,'eb8cd725-09f1-9992-aabf-1a7f6b4cc5c9',1,'多开标签页',NULL,false,false,true,false,false,NULL,false,false,39,1,'2024-05-09 16:03:45.1763',NULL),
	 ('b387dc45-f6ff-0df8-4e3c-54dda0a535b1','multiple-tabs-demo/:id','MultipleTabsDemo','views/model/Function/MultipleTabsDemo',NULL,'eb8cd725-09f1-9992-aabf-1a7f6b4cc5c9',1,'详情页',NULL,false,false,false,true,true,NULL,false,false,40,1,'2024-05-09 16:07:13.79171',NULL);
INSERT INTO menus (id,"path","name",component,redirect,parent_id,status,title,icon,always_show,affix,no_cache,hidden,can_to,active_menu,breadcrumb,no_tags_view,sort,"type",create_time,update_time) VALUES
	 ('52b07001-d572-1a51-e4bc-e8cb14f0168f','request','Request','views/model/Function/Request',NULL,'eb8cd725-09f1-9992-aabf-1a7f6b4cc5c9',0,'请求',NULL,false,false,false,false,false,NULL,false,false,41,1,'2024-05-09 16:07:46.655467',NULL),
	 ('1d2fae42-de04-97a2-2a29-94a01017b6ea','test','Test','views/model/Function/Test',NULL,'eb8cd725-09f1-9992-aabf-1a7f6b4cc5c9',1,'权限测试页',NULL,false,false,true,false,false,NULL,false,false,42,1,'2024-05-09 16:08:20.810648',NULL),
	 ('1a4e1118-ef7b-37e4-78f0-84129f6d8971','hooks','Hooks','##',NULL,'0fadbea2-0c0a-24ad-3d47-010a264e6cf6',1,'hooks','ic:outline-webhook',true,false,false,false,false,NULL,false,false,43,0,'2024-05-09 16:09:29.293371',NULL),
	 ('9a10db0e-da42-318e-2833-fc7d8da991bd','useWatermark','UseWatermark','views/model/hooks/useWatermark',NULL,'1a4e1118-ef7b-37e4-78f0-84129f6d8971',1,'useWatermark',NULL,false,false,true,false,false,NULL,false,false,44,1,'2024-05-09 16:10:07.777818',NULL),
	 ('c444d5f1-83d8-764b-c0ec-ff6d435d6b28','useTagsView','UseTagsView','views/model/hooks/useTagsView',NULL,'1a4e1118-ef7b-37e4-78f0-84129f6d8971',1,'useTagsView',NULL,false,false,true,false,false,NULL,false,false,45,1,'2024-05-09 16:14:29.829083',NULL),
	 ('d0e78c9f-6224-198f-a92c-f5579ae1ce7b','useValidator','UseValidator','views/model/hooks/useValidator',NULL,'1a4e1118-ef7b-37e4-78f0-84129f6d8971',1,'useValidator',NULL,false,false,true,false,false,NULL,false,false,46,1,'2024-05-09 16:15:00.375577',NULL),
	 ('35917f3b-d431-ad39-5968-c0bc2a8c884d','useCrudSchemas','UseCrudSchemas','views/model/hooks/useCrudSchemas',NULL,'1a4e1118-ef7b-37e4-78f0-84129f6d8971',1,'useCrudSchemas',NULL,false,false,true,false,false,NULL,false,false,47,1,'2024-05-09 16:15:24.61613',NULL),
	 ('5c1b6bb2-5dfd-7b81-8f76-7a9e07125135','useClipboard','UseClipboard','views/model/hooks/useClipboard',NULL,'1a4e1118-ef7b-37e4-78f0-84129f6d8971',1,'useClipboard',NULL,false,false,true,false,false,NULL,false,false,48,1,'2024-05-09 16:15:51.594713',NULL),
	 ('b6a29ae7-d1cc-7414-f53d-eb2a3a3f0a09','useNetwork','UseNetwork','views/model/hooks/useNetwork',NULL,'1a4e1118-ef7b-37e4-78f0-84129f6d8971',1,'useNetwork',NULL,false,false,true,false,false,NULL,false,false,49,1,'2024-05-09 16:16:16.81775',NULL),
	 ('c223bbdf-095b-d133-8c6e-7cfb9adc9335','level','Level','##',NULL,'0fadbea2-0c0a-24ad-3d47-010a264e6cf6',1,'多级菜单','carbon:skill-level-advanced',true,false,false,false,false,NULL,false,false,50,0,'2024-05-09 16:16:59.96367',NULL);
INSERT INTO menus (id,"path","name",component,redirect,parent_id,status,title,icon,always_show,affix,no_cache,hidden,can_to,active_menu,breadcrumb,no_tags_view,sort,"type",create_time,update_time) VALUES
	 ('024e02e8-1c84-044a-177e-3b6cca889fe5','menu1','Menu1','##',NULL,'c223bbdf-095b-d133-8c6e-7cfb9adc9335',1,'菜单1',NULL,true,false,false,false,false,NULL,false,false,51,0,'2024-05-09 16:17:41.108904',NULL),
	 ('9d16de49-e370-74d1-9425-aa58731a931d','menu1-1','Menu11','##',NULL,'024e02e8-1c84-044a-177e-3b6cca889fe5',1,'菜单1-1',NULL,true,false,false,false,false,NULL,false,false,52,0,'2024-05-09 16:19:45.936514',NULL),
	 ('4d58ec8f-2c2d-63f0-af26-7eede454a6ad','menu1-1-1','Menu111','views/model/Level/Menu111',NULL,'9d16de49-e370-74d1-9425-aa58731a931d',1,'菜单1-1-1',NULL,false,false,false,true,false,NULL,false,false,53,1,'2024-05-09 16:20:19.211794',NULL),
	 ('f69fb94e-711a-039a-dd74-2590b7cf734d','menu1-2','Menu12','views/model/Level/Menu12',NULL,'024e02e8-1c84-044a-177e-3b6cca889fe5',1,'菜单1-2',NULL,false,false,true,false,false,NULL,false,false,54,1,'2024-05-09 16:21:00.132864',NULL),
	 ('3a8c3670-a360-3b72-7e49-9bd5097a06c3','menu2','Menu2Demo','views/model/Level/Menu2',NULL,'c223bbdf-095b-d133-8c6e-7cfb9adc9335',1,'菜单2',NULL,false,false,true,false,false,NULL,false,false,55,1,'2024-05-09 16:21:38.571008',NULL),
	 ('e393ad39-117f-9a1c-3bdf-ac83ddfc46b9','example','Example','##',NULL,'0fadbea2-0c0a-24ad-3d47-010a264e6cf6',1,'综合示例','ep:management',true,false,false,false,false,NULL,false,false,56,0,'2024-05-09 16:26:44.912268',NULL),
	 ('bb6523f5-08cb-177f-598a-56f411822d5c','example-dialog','ExampleDialog','views/model/Example/Dialog/ExampleDialog',NULL,'e393ad39-117f-9a1c-3bdf-ac83ddfc46b9',1,'综合示例 - 弹窗',NULL,false,false,true,false,false,NULL,false,false,57,1,'2024-05-09 16:29:37.485566',NULL),
	 ('5942cf14-9872-b444-b79c-0e0e42c81581','example-page','ExamplePage','views/model/Example/Page/ExamplePage',NULL,'e393ad39-117f-9a1c-3bdf-ac83ddfc46b9',1,'综合示例 - 页面',NULL,false,false,true,false,false,NULL,false,false,58,1,'2024-05-09 16:30:24.261827',NULL),
	 ('1b2ac5f2-f1db-3247-5f93-3f918be4a2ea','example-add','ExampleAdd','views/model/Example/Page/ExampleAdd',NULL,'e393ad39-117f-9a1c-3bdf-ac83ddfc46b9',1,'综合示例 - 新增',NULL,false,false,true,false,false,NULL,false,false,59,1,'2024-05-09 16:31:03.857986',NULL),
	 ('20d7d35f-b45f-0cb6-058d-595f595e7c8b','example-edit','ExampleEdit','views/model/Example/Page/ExampleEdit',NULL,'e393ad39-117f-9a1c-3bdf-ac83ddfc46b9',1,'综合示例 - 编辑',NULL,false,false,true,false,false,NULL,false,false,60,1,'2024-05-09 16:32:52.387704',NULL);
INSERT INTO menus (id,"path","name",component,redirect,parent_id,status,title,icon,always_show,affix,no_cache,hidden,can_to,active_menu,breadcrumb,no_tags_view,sort,"type",create_time,update_time) VALUES
	 ('7aa95cde-eead-cd1d-f921-4f55d9c4f387','example-detail','ExampleDetail','views/model/Example/Page/ExampleDetail',NULL,'e393ad39-117f-9a1c-3bdf-ac83ddfc46b9',1,'综合示例 - 详情',NULL,false,false,true,false,false,NULL,false,false,61,1,'2024-05-09 16:38:27.809013',NULL),
	 ('7a6ecb58-1031-b330-7f43-7819741a74ba','error','Error','##',NULL,'0fadbea2-0c0a-24ad-3d47-010a264e6cf6',1,'错误页面','ci:error',true,false,false,false,false,NULL,false,false,62,0,'2024-05-09 16:39:10.838692',NULL),
	 ('69b609bb-f109-ee38-a2cc-30c5b3e4956e','404-demo','404Demo','views/model/Error/404',NULL,'7a6ecb58-1031-b330-7f43-7819741a74ba',1,'404',NULL,false,false,true,false,false,NULL,false,false,63,1,'2024-05-09 16:40:27.044979',NULL),
	 ('51caef43-8bc5-1ef2-259e-673f592767df','403-demo','403Demo','views/model/Error/403',NULL,'7a6ecb58-1031-b330-7f43-7819741a74ba',1,'403',NULL,false,false,true,false,false,NULL,false,false,64,1,'2024-05-09 16:40:52.575872',NULL),
	 ('a0935db7-0b8e-c9fb-fa71-de0de1c933bc','500-demo','500Demo','views/model/Error/500',NULL,'7a6ecb58-1031-b330-7f43-7819741a74ba',1,'500',NULL,false,false,true,false,false,NULL,false,false,65,1,'2024-05-09 16:41:18.037139',NULL);

	
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
--delete from menus;
-----------------------------------------------------------------
ALTER TABLE gateway.sys_log ADD create_time timestamp DEFAULT now() NULL;
ALTER TABLE gateway.sys_log ADD update_time timestamp NULL;


