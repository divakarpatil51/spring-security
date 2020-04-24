insert into users(id, username, password, active) values(1, 'user', 'user', true);
insert into users(id, username, password, active) values(2, 'admin', 'admin', true);

insert into roles(id, role) values(1, 'ROLE_USER');
insert into roles(id, role) values(2, 'ROLE_ADMIN');

insert into users_roles(user_id, roles_id) values(1, 1);
insert into users_roles(user_id, roles_id) values(2, 1);
insert into users_roles(user_id, roles_id) values(2, 2);
