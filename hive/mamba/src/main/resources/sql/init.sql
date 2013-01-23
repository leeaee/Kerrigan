-- admin
insert into admin(id, name, password, true_name) values (1, 'admin', '96e79218965eb72c92a549dd5a330112', 'admin');
insert into admin(id, name, password, true_name) values (2, 'guest', '96e79218965eb72c92a549dd5a330112', 'guest');

-- admin_role
insert into admin_role(id, name) values (1, 'Administrator');
insert into admin_role(id, name) values (2, 'Guest');

-- admin_x_role
insert into admin_x_role(admin_id, role_id) values (1, 1);
insert into admin_x_role(admin_id, role_id) values (2, 2);

-- admin_permission
insert into admin_permission(id, permission) values (1, 'rs:admin:read');
insert into admin_permission(id, permission) values (2, 'rs:admin:write');

-- admin_role_x_permission
insert into admin_role_x_permission(role_id, permission_id) values (1, 1);
insert into admin_role_x_permission(role_id, permission_id) values (1, 2);
insert into admin_role_x_permission(role_id, permission_id) values (2, 1);
