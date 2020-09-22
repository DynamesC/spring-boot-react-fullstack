CREATE TABLE Site (
   site_id varchar primary key,
   site_name varchar
);

CREATE TABLE Device (
   mac VARCHAR PRIMARY KEY,
   name VARCHAR,
   site_id VARCHAR REFERENCES Site(site_id)
);

insert into Site values ('SITE0001', '六和桥孵化集群');
insert into Site values ('SITE0002', '五和桥孵化集群');
insert into Site values ('SITE0003', '七和桥孵化集群');
insert into Site values ('NULL', '无');

INSERT INTO Device VALUES ('ac233fd09d21', 'Patrick Star', 'SITE0001');
INSERT INTO Device VALUES ('ac233fd09d26', 'SpongeBob', 'SITE0002');
INSERT INTO Device VALUES ('ac233fd0ace9', 'Squidward', 'SITE0003');
INSERT INTO Device VALUES ('ac233fd0ace1', 'Mr. Krabs', 'SITE0001');
