ALTER TABLE hangerproduct alter column template_id set default 'UNKNOWN';
ALTER TABLE hangerlabel alter column product_barcode set default 'UNKNOWN';

ALTER TABLE hangerproduct
ADD CONSTRAINT template_id_delete
FOREIGN KEY (template_id) REFERENCES HangerLabelTemplate(template_id)
ON DELETE SET DEFAULT;

ALTER TABLE hangerlabel
ADD CONSTRAINT product_barcode_delete
FOREIGN KEY (product_barcode) REFERENCES hangerproduct(barcode)
ON DELETE SET DEFAULT;