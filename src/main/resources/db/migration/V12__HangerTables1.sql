create type LABELTYPE as enum('21R', '21B');

CREATE TABLE HangerLabelTemplate (
   template_id CHAR(10) PRIMARY KEY,
   name VARCHAR(80) NOT NULL,
   note VARCHAR,
   wl_21r_id VARCHAR,
   wl_21b_id VARCHAR,
   create_time TIMESTAMP NOT NULL
);

-- 新增默认模板
INSERT INTO HangerLabelTemplate
VALUES ('template00', '默认模板','无', '202cb962ac59075b964b07152d234b70', '5f4db73861182', CURRENT_TIMESTAMP);
-- 商品

CREATE TABLE HangerProduct (
   barcode VARCHAR PRIMARY KEY,
   sku VARCHAR(80),
   name VARCHAR(80) NOT NULL,
   size VARCHAR,
   current_price FLOAT NOT NULL,
   original_price FLOAT NOT NULL,
   template_id CHAR(10) NOT NULL REFERENCES HangerLabelTemplate(template_id),
   barcode_text VARCHAR,
   qrcode VARCHAR,
   qrcode_text VARCHAR,
   r1 VARCHAR,
   r2 VARCHAR,
   r3 VARCHAR,
   r4 VARCHAR,
   r5 VARCHAR,
   r6 VARCHAR,
   r7 VARCHAR,
   r8 VARCHAR,
   r9 VARCHAR,
   r10 VARCHAR,
   stock INT,
   create_time TIMESTAMP NOT NULL
);

-- 新增默认商品
INSERT INTO HangerProduct(barcode, sku, name, current_price, original_price, template_id, create_time)
VALUES ('6973291330023', 'ADFB0102','Fingerbot Black', 399.00, 499.00, 'template00', CURRENT_TIMESTAMP);

INSERT INTO HangerProduct(barcode, sku, name, current_price, original_price, template_id, create_time)
VALUES ('9283759879857', 'ADFB0103','Fingerbot Red', 199.00, 299.00, 'template00', CURRENT_TIMESTAMP);

INSERT INTO HangerProduct(barcode, sku, name, current_price, original_price, template_id, create_time)
VALUES ('7163270296198', 'ADFB0101','Fingerbot White', 499.00, 599.00, 'template00', CURRENT_TIMESTAMP);

-- 新增商品
--INSERT INTO Product VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)

-- 拉取商品
-- SELECT barcode, name, sku, size, current_price, original_price, template_id FROM Product
-- ORDER BY create_time ASC
-- LIMIT 10

-- 标签

CREATE TABLE HangerLabel (
   label_id VARCHAR PRIMARY KEY,
   product_barcode VARCHAR NOT NULL REFERENCES HangerProduct(barcode),
   type LABELTYPE NOT NULL
);
