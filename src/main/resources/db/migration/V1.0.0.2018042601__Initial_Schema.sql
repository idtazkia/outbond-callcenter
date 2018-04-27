CREATE TABLE s_permission (
  id               VARCHAR(255) NOT NULL,
  permission_label VARCHAR(255) NOT NULL,
  permission_value VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (permission_value)
);

CREATE TABLE s_role (
  id VARCHAR(255) NOT NULL,
  description VARCHAR(255) DEFAULT NULL,
  name        VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (name)
);

CREATE TABLE s_role_permission (
  id_role       VARCHAR(255) NOT NULL,
  id_permission VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_role, id_permission),
  FOREIGN KEY (id_permission) REFERENCES s_permission (id),
  FOREIGN KEY (id_role) REFERENCES s_role (id)
);

CREATE TABLE s_user (
  id       VARCHAR(36),
  username VARCHAR(255) NOT NULL,
  fullname VARCHAR(255) ,
  active   BOOLEAN      NOT NULL,
  email    VARCHAR (255) NOT NULL,
  id_role  VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (username),
  UNIQUE (email),
  FOREIGN KEY (id_role) REFERENCES s_role (id)
);


create table s_user_password (
  id VARCHAR(36) NOT NULL,
	id_user varchar(36) NOT NULL,
	password varchar(255) NOT NULL,
	PRIMARY KEY (id),
	foreign key (id_user) references s_user (id)
);

CREATE TABLE campaign (
  id varchar(36) NOT NULL,
  active bit(1) DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  end_date date NOT NULL,
  name varchar(255) NOT NULL,
  start_date date NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_ktyf1tkuo57qs1vaevr3thf19 (name)
) Engine=InnoDB ;

CREATE TABLE institution (
  id varchar(36) NOT NULL,
  description varchar(255) DEFAULT NULL,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_qhw15h5f7nc4g3ndva8sory1u (name)
) Engine=InnoDB ;

CREATE TABLE prospect (
  id varchar(36) NOT NULL,
  mobile_phone varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  parent_name varchar(255) DEFAULT NULL,
  id_institution varchar(36) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_institution) REFERENCES institution (id)
) Engine=InnoDB ;

CREATE TABLE campaign_prospect (
  id_campaign varchar (36),
  id_prospect varchar (36),
  primary key(id_campaign, id_prospect)
) Engine=InnoDB ;

CREATE TABLE call_result (
  id varchar(36) NOT NULL,
  description varchar(255) DEFAULT NULL,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_lr6ueuj0lai2299hwfwq6sqo6 (name)
) Engine=InnoDB ;

CREATE TABLE call_session (
  id varchar(36) NOT NULL,
  call_end_time datetime NOT NULL,
  call_start_time datetime NOT NULL,
  followup datetime DEFAULT NULL,
  remarks varchar(255) DEFAULT NULL,
  id_call_result varchar(36) NOT NULL,
  id_prospect varchar(36) NOT NULL,
  id_user varchar(36) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_call_result) references call_result (id),
  FOREIGN KEY (id_prospect) references prospect (id),
  FOREIGN KEY (id_user) references s_user (id)
) Engine=InnoDB ;

create table followup_call (
  id varchar(36) NOT NULL,
  id_call_session varchar (36),
  id_prospect varchar (36) not null,
  call_time datetime not null ,
  PRIMARY KEY (id),
  FOREIGN KEY (id_call_session) references call_session (id),
  FOREIGN KEY (id_prospect) references prospect (id)
) Engine=InnoDB ;