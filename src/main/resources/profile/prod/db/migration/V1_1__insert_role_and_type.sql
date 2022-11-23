INSERT
INTO
  t_account_role
  (created_at, created_by, `role`, is_deleted)
VALUES
  (NOW(), 'FLYWAY', 0, 0),
  (NOW(), 'FLYWAY', 1, 0);

INSERT
INTO
  t_account_type
  (created_at, created_by, type, is_deleted)
VALUES
  (NOW(), 'FLYWAY', 0, 0),
  (NOW(), 'FLYWAY', 1, 0),
  (NOW(), 'FLYWAY', 2, 0),
  (NOW(), 'FLYWAY', 3, 0),
  (NOW(), 'FLYWAY', 4, 0);