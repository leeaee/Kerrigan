-- create databse
CREATE DATABASE mamba;


USE mamba;
-- creat tables

/*==============================================================*/
/* Table: admin         									    */
/*==============================================================*/
CREATE TABLE admin
(
	id bigint AUTO_INCREMENT NOT NULL,
	name varchar(63) NOT NULL,
	password varchar(32) NOT NULL,
	true_name varchar(63) NULL,
	phone varchar(20)  NULL,
	mobile varchar(20) NULL,
	email varchar(127) NULL,	
	state int(1) DEFAULT '0',
	description varchar(255) DEFAULT NULL,
	create_time bigint(20) DEFAULT '111111111111',
	last_modify bigint(20) DEFAULT '111111111111',
	PRIMARY KEY (id),
	UNIQUE KEY (name)
)
ENGINE = InnoDB AUTO_INCREMENT = 10000 ROW_FORMAT = DEFAULT CHARSET=utf8;


/*==============================================================*/
/* Table: admin_role										    */
/*==============================================================*/
CREATE TABLE admin_role
(
	id bigint AUTO_INCREMENT NOT NULL,
	name varchar(63) NOT NULL,
	description varchar(255) DEFAULT NULL,
	create_time bigint(20) DEFAULT '111111111111',
    PRIMARY KEY (id),
    UNIQUE KEY (name)
)
ENGINE = InnoDB AUTO_INCREMENT = 10000 ROW_FORMAT = DEFAULT CHARSET=utf8;


/*==============================================================*/
/* Table: admin_x_role										    */
/*==============================================================*/
CREATE TABLE admin_x_role
(
  admin_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL,
  PRIMARY KEY (admin_id,role_id),
  CONSTRAINT pk_admin_id FOREIGN KEY (admin_id) REFERENCES admin (id),
  CONSTRAINT pk_role_id FOREIGN KEY (role_id) REFERENCES admin_role (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: admin_permission									    */
/*==============================================================*/
CREATE TABLE admin_permission
(
	id bigint AUTO_INCREMENT NOT NULL,
	permission varchar(63) NOT NULL,
	description varchar(255) DEFAULT NULL,
	create_time bigint(20) DEFAULT '111111111111',
    PRIMARY KEY (id),
    UNIQUE KEY (permission)
)
ENGINE = InnoDB AUTO_INCREMENT = 10000 ROW_FORMAT = DEFAULT CHARSET=utf8;


/*==============================================================*/
/* Table: admin_role_x_permission							    */
/*==============================================================*/
CREATE TABLE admin_role_x_permission
(
	role_id bigint NOT NULL,
	permission_id bigint NOT NULL,
	PRIMARY KEY (role_id, permission_id)
)
ENGINE = InnoDB ROW_FORMAT = DEFAULT CHARSET=utf8;




