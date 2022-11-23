CREATE TABLE t_account_role (
  id BIGINT AUTO_INCREMENT NOT NULL,
   is_deleted BIT(1) NULL,
   deleted_at datetime NULL,
   created_at datetime NOT NULL,
   created_by VARCHAR(50) NOT NULL,
   updated_at datetime NULL,
   updated_by VARCHAR(50) NULL,
   `role` INT NOT NULL,
   CONSTRAINT pk_t_account_role PRIMARY KEY (id)
);

CREATE TABLE t_account_type (
  id BIGINT AUTO_INCREMENT NOT NULL,
   is_deleted BIT(1) NULL,
   deleted_at datetime NULL,
   created_at datetime NOT NULL,
   created_by VARCHAR(50) NOT NULL,
   updated_at datetime NULL,
   updated_by VARCHAR(50) NULL,
   type INT NOT NULL,
   CONSTRAINT pk_t_account_type PRIMARY KEY (id)
);

CREATE TABLE t_account_profile (
  id BIGINT AUTO_INCREMENT NOT NULL,
   is_deleted BIT(1) NULL,
   deleted_at datetime NULL,
   created_at datetime NOT NULL,
   created_by VARCHAR(50) NOT NULL,
   updated_at datetime NULL,
   updated_by VARCHAR(50) NULL,
   fullname VARCHAR(50) NOT NULL,
   email VARCHAR(50) NULL,
   phone_number VARCHAR(15) NULL,
   address TEXT NULL,
   birth_place VARCHAR(20) NULL,
   birth_date date NULL,
   CONSTRAINT pk_t_account_profile PRIMARY KEY (id)
);

ALTER TABLE t_account_profile ADD CONSTRAINT uc_t_account_profile_email UNIQUE (email);

ALTER TABLE t_account_profile ADD CONSTRAINT uc_t_account_profile_phone_number UNIQUE (phone_number);

CREATE TABLE t_account_master (
  id BIGINT AUTO_INCREMENT NOT NULL,
   is_deleted BIT(1) NULL,
   deleted_at datetime NULL,
   created_at datetime NOT NULL,
   created_by VARCHAR(50) NOT NULL,
   updated_at datetime NULL,
   updated_by VARCHAR(50) NULL,
   username VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   active BIT(1) DEFAULT 1 NOT NULL,
   id_profile BIGINT NOT NULL,
   CONSTRAINT pk_t_account_master PRIMARY KEY (id)
);

ALTER TABLE t_account_master ADD CONSTRAINT uc_t_account_master_username UNIQUE (username);

ALTER TABLE t_account_master ADD CONSTRAINT FK_T_ACCOUNT_MASTER_ON_ID_PROFILE FOREIGN KEY (id_profile) REFERENCES t_account_profile (id);

CREATE TABLE bt_account_types (
  id_account BIGINT NOT NULL,
   id_type BIGINT NOT NULL
);

ALTER TABLE bt_account_types ADD CONSTRAINT uc_bt_account_types_id_type UNIQUE (id_type);

ALTER TABLE bt_account_types ADD CONSTRAINT fk_btacctyp_on_account_dao FOREIGN KEY (id_account) REFERENCES t_account_master (id);

ALTER TABLE bt_account_types ADD CONSTRAINT fk_btacctyp_on_type_dao FOREIGN KEY (id_type) REFERENCES t_account_type (id);

CREATE TABLE bt_account_roles (
  id_account BIGINT NOT NULL,
   id_role BIGINT NOT NULL
);

ALTER TABLE bt_account_roles ADD CONSTRAINT uc_bt_account_roles_id_role UNIQUE (id_role);

ALTER TABLE bt_account_roles ADD CONSTRAINT fk_btaccrol_on_account_dao FOREIGN KEY (id_account) REFERENCES t_account_master (id);

ALTER TABLE bt_account_roles ADD CONSTRAINT fk_btaccrol_on_role_dao FOREIGN KEY (id_role) REFERENCES t_account_role (id);