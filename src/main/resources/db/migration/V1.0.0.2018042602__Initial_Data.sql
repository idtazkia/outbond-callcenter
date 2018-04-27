insert into campaign (id, name, description, start_date, end_date, active)
values ('default', 'Default Campaign', 'Default Campaign', '2018-01-01', '2019-01-01', true);

insert into call_result (id, name, description)
values ('cr001', 'wrong-number', 'Salah Sambung');

insert into call_result (id, name, description)
values ('cr002', 'not-picked-up', 'Tidak Diangkat');

insert into call_result (id, name, description)
values ('cr003', 'busy', 'Nada Sibuk');

insert into call_result (id, name, description)
values ('cr004', 'rejected', 'Ditolak');

insert into call_result (id, name, description)
values ('cr005', 'call-later', 'Telpon Lagi Nanti');

insert into call_result (id, name, description)
values ('cr006', 'will-buy', 'Akan Mendaftar');

insert into call_result (id, name, description)
values ('cr007', 'not-interested', 'Tidak Berminat');

INSERT INTO s_role (id, description, name) VALUES
  ('superuser', 'SUPERUSER', 'Super User'),
  ('agent', 'AGENT', 'Agent'),
  ('manager', 'MANAGER', 'Manager');

INSERT INTO s_user (id, active, username, email, id_role) VALUES
  ('u001', true, 'user001', 'u001@example.com', 'agent');

INSERT INTO s_user (id, active, username, email, id_role) VALUES
  ('u002', true, 'user002', 'u002@example.com','manager');

INSERT INTO s_user (id, active, username, email, id_role) VALUES
  ('u003', true, 'user003', 'u003@example.com','superuser');

INSERT INTO s_user_password (id, id_user, password) VALUES
  -- password : test123
  ('u1', 'u001', '$2a$13$d2GZHGr6gedUiNk8r3Pbo.Jc8eH7oBVdTta.WGMG9g1dO9T4hlNPG');

INSERT INTO s_user_password (id, id_user, password) VALUES
  -- password : test123
  ('u2', 'u002', '$2a$13$d2GZHGr6gedUiNk8r3Pbo.Jc8eH7oBVdTta.WGMG9g1dO9T4hlNPG');

INSERT INTO s_user_password (id, id_user, password) VALUES
  -- password : test123
  ('u3','u003', '$2a$13$d2GZHGr6gedUiNk8r3Pbo.Jc8eH7oBVdTta.WGMG9g1dO9T4hlNPG');

INSERT INTO s_permission (id, permission_value, permission_label) VALUES
  ('configuresystem', 'CONFIGURE_SYSTEM', 'Configure System');


INSERT INTO s_role_permission (id_role, id_permission) VALUES
  ('superuser', 'configuresystem');