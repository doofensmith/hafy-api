INSERT
INTO
  t_account_profile
  (created_at, created_by, fullname, is_deleted)
VALUES
  (NOW(), 'FLYWAY', 'Administrator 1', 0);

INSERT
INTO
  t_account_master
  (created_at, created_by, username, password, id_profile, is_deleted, active)
VALUES
  (NOW(), 'FLYWAY', 'admin1', '$2a$12$4iUjL7.Cj.ZmDHohUgIqp.vQFlUcwRb3iMIwXoPou/l2PbSEErS2u', 1, 0, 1);

INSERT
INTO
  bt_account_roles
  (id_account, id_role)
VALUES
  (1, 1),
  (1, 2);

INSERT
INTO
  bt_account_types
  (id_account, id_type)
VALUES
  (1, 2);