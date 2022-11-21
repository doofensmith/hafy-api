CREATE TABLE t_account_role (
  id BIGINT AUTO_INCREMENT NOT NULL,
   is_deleted BOOLEAN,
   deleted_at TIMESTAMP,
   created_at TIMESTAMP NOT NULL,
   created_by VARCHAR(255) NOT NULL,
   updated_at TIMESTAMP,
   updated_by VARCHAR(255),
   role INT NOT NULL,
   CONSTRAINT pk_t_account_role PRIMARY KEY (id)
);

CREATE TABLE t_account_type (
  id BIGINT AUTO_INCREMENT NOT NULL,
   is_deleted BOOLEAN,
   deleted_at TIMESTAMP,
   created_at TIMESTAMP NOT NULL,
   created_by VARCHAR(255) NOT NULL,
   updated_at TIMESTAMP,
   updated_by VARCHAR(255),
   type INT NOT NULL,
   CONSTRAINT pk_t_account_type PRIMARY KEY (id)
);