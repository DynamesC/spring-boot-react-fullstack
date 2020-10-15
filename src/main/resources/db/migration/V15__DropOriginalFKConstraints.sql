alter table hangerproduct drop constraint if exists hangerproduct_template_id_fkey;
alter table hangerlabel drop constraint if exists hangerlabel_product_barcode_fkey;