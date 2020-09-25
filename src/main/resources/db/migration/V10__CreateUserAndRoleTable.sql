CREATE TABLE Users (
   id SERIAL PRIMARY KEY,
   username VARCHAR,
   passwordMd5 VARCHAR
);

Create Table Roles(
    id SERIAL PRIMARY KEY,
    name VARCHAR
);

insert into roles(name) values ('ROLE_USER');
insert into roles(name) values ('ROLE_MODERATOR');
insert into roles(name) values ('ROLE_ADMIN');