create sequence hibernate_sequence start 1 increment 1;
create table exercises (id int8 not null, file_name varchar(255), title varchar(255) not null, user_id int8, primary key (id));
create table user_role (user_id int8 not null, roles varchar(255));
create table usr (id int8 not null, activation_code varchar(255), active boolean not null, email varchar(255) not null, password varchar(255) not null, username varchar(255), primary key (id));
alter table if exists exercises add constraint exercise_user_fk foreign key (user_id) references usr;
alter table if exists user_role add constraint user_role_user_fk foreign key (user_id) references usr;