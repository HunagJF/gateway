CREATE TABLE gateway.sys_log (
	trance_id varchar(50) NOT NULL DEFAULT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring),
	"source" varchar(255) NULL,
	severity varchar(50) NULL,
	oper_name varchar(255) NULL,
	is_persistence bool NULL,
	url varchar(255) NULL,
	"method" varchar(255) NULL,
	remote_addr varchar(255) NULL,
	request_method varchar(255) NULL,
	args text NULL,
	user_agent varchar(255) NULL,
	"result" text NULL,
	start_time timestamp NULL,
	end_time timestamp NULL,
	CONSTRAINT sys_log_pkey PRIMARY KEY (trance_id)
);

CREATE TABLE gateway.user_login (
	id varchar(50) NOT NULL DEFAULT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring),
	user_name varchar(50) NULL,
	"password" varchar(200) null,
	CONSTRAINT user_login_pkey PRIMARY KEY (id)
);

CREATE TABLE gateway.roles (
	id varchar(50) NOT NULL DEFAULT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring),
	role_name varchar(255) NOT NULL,
	remark text NULL,
	status int4 NOT NULL,
	create_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT roles_pkey PRIMARY KEY (id),
	CONSTRAINT roles_status_check CHECK ((status = ANY (ARRAY[0, 1])))
);

--CREATE TABLE gateway."menus" (
--	id varchar(50) NOT NULL DEFAULT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring),
--	"path" varchar(255) NOT NULL,
--	"name" varchar(255) NULL,
--	component varchar(255) NULL,
--	redirect varchar(255) NULL,
--	parent_id varchar(50) NULL,
--	status int4 NULL DEFAULT 1,
--	CONSTRAINT menus_name_key UNIQUE (name),
--	CONSTRAINT menus_pkey PRIMARY KEY (id),
--	CONSTRAINT status_check CHECK ((status = ANY (ARRAY[0, 1]))),
--	CONSTRAINT menus_parent_id_fkey FOREIGN KEY (parent_id) REFERENCES gateway."menus"(id) ON DELETE CASCADE
--);
--
--CREATE TABLE gateway."menu_meta" (
--	id varchar(50) NOT NULL DEFAULT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring),
--	menu_id varchar(50) NOT NULL,
--	title varchar(255) NULL,
--	icon varchar(255) NULL,
--	always_show bool NULL DEFAULT false,
--	affix bool NULL DEFAULT false,
--	no_cache bool NULL DEFAULT false,
--	hidden bool NULL DEFAULT false,
--	can_to bool NULL DEFAULT false,
--	active_menu varchar(255) NULL,
--	CONSTRAINT menu_meta_pkey PRIMARY KEY (id),
--	CONSTRAINT menu_meta_route_id_fkey FOREIGN KEY (menu_id) REFERENCES gateway."menus"(id) ON DELETE CASCADE
--);


CREATE TABLE gateway.user_role (
	id varchar(50) NOT NULL DEFAULT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring),
	user_id varchar(50) NOT NULL,
	role_id varchar(50) NOT NULL,
	create_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT user_role_user_id_role_id_key UNIQUE (user_id, role_id),
	CONSTRAINT user_role_role_id_fkey FOREIGN KEY (role_id) REFERENCES gateway.roles(id),
	CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id) REFERENCES gateway.user_login(id)
);


CREATE TABLE gateway.role_menu (
	id varchar(50) NOT NULL DEFAULT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring),
	role_id varchar(50) NOT NULL,
	menu_id varchar(50) NOT NULL,
	create_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT role_menu_role_id_menu_id_key UNIQUE (role_id, menu_id),
	CONSTRAINT role_menu_role_id_fkey FOREIGN KEY (role_id) REFERENCES gateway.roles(id),
	CONSTRAINT role_menu_menu_id_fkey FOREIGN KEY (menu_id) REFERENCES gateway.menus(id)
);


CREATE TABLE gateway.menus (
	id varchar(50) DEFAULT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring) NOT NULL,
	"path" varchar(255) NOT NULL,
	"name" varchar(255) NULL,
	component varchar(255) NULL,
	redirect varchar(255) NULL,
	parent_id varchar(50) NULL,
	status int4 DEFAULT 1 NULL,
	title varchar(255) NULL,
	icon varchar(255) NULL,
	always_show bool DEFAULT false NULL,
	affix bool DEFAULT false NULL,
	no_cache bool DEFAULT false NULL,
	hidden bool DEFAULT false NULL,
	can_to bool DEFAULT false NULL,
	active_menu varchar(255) NULL,
	breadcrumb bool DEFAULT true NULL,
	no_tags_view bool DEFAULT false NULL,
	sort int4 NULL,
	CONSTRAINT menus_name_key UNIQUE (name),
	CONSTRAINT menus_pkey PRIMARY KEY (id),
	CONSTRAINT status_check CHECK ((status = ANY (ARRAY[0, 1]))),
	CONSTRAINT menus_parent_id_fkey FOREIGN KEY (parent_id) REFERENCES gateway.menus(id) ON DELETE CASCADE
);

select uuid_in(md5(random()::text || clock_timestamp()::text)::cstring);