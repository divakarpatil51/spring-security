create sequence if not exists role_id_generator start 1 increment 1
create sequence if not exists user_id_generator start 1 increment 1

create table if not exists roles (id int4 not null, role varchar(255), primary key (id))
create table if not exists users (id int4 not null, active boolean, password varchar(255), username varchar(255), primary key (id))
create table if not exists users_roles (user_id int4 not null, roles_id int4 not null, primary key (user_id, roles_id))

alter table if exists users_roles drop constraint if exists FK_roles_id
alter table if exists users_roles drop constraint if exists FK_user_id

alter table if exists users_roles add constraint FK_roles_id foreign key (roles_id) references roles
alter table if exists users_roles add constraint FK_user_id foreign key (user_id) references users