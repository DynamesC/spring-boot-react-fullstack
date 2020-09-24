CREATE TABLE User (
   account VARCHAR PRIMARY KEY,
   name VARCHAR,
   passwordMd5 VARCHAR,
   type enum('root', 'admin', 'normal', 'advertiser', 'great advertiser', 'supreme advertiser')
);