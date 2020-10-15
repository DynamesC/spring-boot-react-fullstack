INSERT INTO HangerLabelTemplate
VALUES ('UNKNOWN', '未知','无', 'UNKNOWN', 'UNKNOWN', CURRENT_TIMESTAMP);

INSERT INTO HangerProduct(barcode, sku, name, current_price, original_price, template_id, create_time)
VALUES ('UNKNOWN', 'UNKNOWN','未知', 0, 0, 'UNKNOWN', CURRENT_TIMESTAMP);

INSERT INTO HangerLabel
VALUES ('UNKNOWN', 'UNKNOWN', '21R');