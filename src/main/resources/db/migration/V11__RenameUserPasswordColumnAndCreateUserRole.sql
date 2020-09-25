alter table users rename column  passwordMd5 to password_md5;
create table user_roles(
    user_id integer references users(id),
    role_id integer references roles(id)
);