package com.amigoscode.demo.Hanger;

import com.amigoscode.demo.Hanger.DAO.HangerProduct;
import com.amigoscode.demo.Hanger.DAO.HangerTemplate;
import com.amigoscode.demo.Hanger.Request.RemoveProductsRequest;
import com.amigoscode.demo.Hanger.Request.UpdateHangerProductRequest;
import com.amigoscode.demo.Hanger.Response.TotalCountsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class HangerDataAccessService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HangerDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<HangerProduct> queryProducts(int startIndex, int endIndex){
        String sql = "" +
                " SELECT p.*, t.name as template_name" +
                " FROM HangerProduct p JOIN HangerLabelTemplate t ON p.template_id = t.template_id "+
                " ORDER BY create_time" +
                " OFFSET ? ROWS" +
                " FETCH FIRST ? ROWS ONLY ";

        return jdbcTemplate.query(sql, new Object[] {startIndex, endIndex - startIndex}, mapHangerProductFromDB());
    }

    public List<HangerTemplate> queryTemplates(){
        String sql = "" +
                " SELECT *" +
                " FROM HangerLabelTemplate";

        return jdbcTemplate.query(sql,  mapHangerTemplateFromDB());
    }

    public TotalCountsResponse queryTotalCount(){
        String sql1 = "select count(*) from HangerProduct";
        int productCount = jdbcTemplate.queryForObject(sql1, Integer.class);
        String sql2 = "select count(*) from HangerLabelTemplate";
        int templateCount = jdbcTemplate.queryForObject(sql2, Integer.class);
        String sql3 = "select count(*) from HangerLabel";
        int labelCount = jdbcTemplate.queryForObject(sql3, Integer.class);

        return new TotalCountsResponse(productCount, labelCount, templateCount);
    }

    Boolean createProduct(HangerProduct product){
        try{
            String sql = "" +
                    " insert into HangerProduct values " +
                    " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, CURRENT_TIMESTAMP )" ;
            jdbcTemplate.update(sql, new Object[] {
                    product.getBarcode(),
                    product.getSku(),
                    product.getName(),
                    product.getSize(),
                    product.getCurrentPrice(),
                    product.getOriginalPrice(),
                    product.getTemplateId(),
                    product.getBarcodeText(),
                    product.getQrcode(),
                    product.getQrcodeText(),
                    product.getR1(),
                    product.getR2(),
                    product.getR3(),
                    product.getR4(),
                    product.getR5(),
                    product.getR6(),
                    product.getR7(),
                    product.getR8(),
                    product.getR9(),
                    product.getR10(),
                    product.getStock() == null ? 0 : product.getStock()
            });
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    Boolean removeProduct(RemoveProductsRequest removeProductsRequest){
        List<String> barcodes = removeProductsRequest.getBarcodes();
        if(barcodes == null || barcodes.size() == 0){
            return true;
        }

        String sql = "" +
                " delete from hangerproduct" +
                " where barcode in (" + "\'"+ barcodes.get(0) + "\'";

        for (int i = 1; i < barcodes.size(); i++) {
            sql = sql + ", " + "\'" + barcodes.get(i) + "\'";
        }

        sql = sql + ")";

        try{
            jdbcTemplate.update(sql);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    Boolean updateProduct(UpdateHangerProductRequest request){
        try{
            String sql = (
                    " update HangerProduct set " +
                    (request.getSku() != null ? String.format(" sku = \'%s\' ,", request.getSku()) : "") +
                    (request.getName() != null ? String.format(" name = \'%s\' ,", request.getName()) : "" )+
                    (request.getSize() != null ? String.format(" size = \'%s\' ,", request.getSize()) : "") +
                    (request.getCurrentPrice() != null ? String.format(" current_price = %f ,", request.getCurrentPrice()) : "") +
                    (request.getOriginalPrice() != null ? String.format(" original_price = %f ,", request.getOriginalPrice()) : "") +
                    (request.getTemplateId() != null ? String.format(" template_id = \'%s\' ,", request.getTemplateId()) : "") +
                    (request.getBarcodeText() != null ? String.format(" barcode_text = \'%s\' ,", request.getBarcodeText()) : "") +
                    (request.getQrcode() != null ? String.format(" qrcode = \'%s\' ,", request.getQrcode()) : "") +
                    (request.getQrcodeText() != null ? String.format(" qrcode_text = \'%s\' ,", request.getQrcodeText()) : "") +
                    (request.getR1() != null ? String.format(" r1 = \'%s\' ,", request.getR1()) : "") +
                    (request.getR2() != null ? String.format(" r2 = \'%s\' ,", request.getR2()) : "") +
                    (request.getR3() != null ? String.format(" r3 = \'%s\' ,", request.getR3()) : "") +
                    (request.getR4() != null ? String.format(" r4 = \'%s\' ,", request.getR4()) : "") +
                    (request.getR5() != null ? String.format(" r5 = \'%s\' ,", request.getR5()) : "") +
                    (request.getR6() != null ? String.format(" r6 = \'%s\' ,", request.getR6()) : "") +
                    (request.getR7() != null ? String.format(" r7 = \'%s\' ,", request.getR7()) : "") +
                    (request.getR8() != null ? String.format(" r8 = \'%s\' ,", request.getR8()) : "") +
                    (request.getR9() != null ? String.format(" r9 = \'%s\' ,", request.getR9()) : "") +
                    (request.getR10() != null ? String.format(" r10 = \'%s\' ,", request.getR10()) : "")
            );

            sql = sql.substring(0, sql.length()-1) + " where barcode = ?";
            System.out.println(sql);
            jdbcTemplate.update(sql, new Object[]{request.getBarcode()});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private RowMapper<HangerTemplate> mapHangerTemplateFromDB(){
        return (resultSet, i) -> {
            String template_id = resultSet.getString("template_id");
            String name = resultSet.getString("name");
            String note = resultSet.getString("note");
            String wl_21r_id = resultSet.getString("wl_21r_id");
            String wl_21b_id = resultSet.getString("wl_21b_id");
            Timestamp createTime = resultSet.getTimestamp("create_time");

            Date date = new Date();
            date.setTime(createTime.getTime());
            String createTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

            return new HangerTemplate(
                    template_id, name, note, wl_21r_id, wl_21b_id, createTimeString
            );
        };
    }

    private RowMapper<HangerProduct> mapHangerProductFromDB() {
        return (resultSet, i) -> {
            String barcode = resultSet.getString("barcode");
            String sku = resultSet.getString("sku");
            String name = resultSet.getString("name");
            String size = resultSet.getString("size");
            Double originalPrice = resultSet.getDouble("original_price");
            Double currentPrice = resultSet.getDouble("current_price");
            String templateId = resultSet.getString("template_id");
            String templateName = resultSet.getString("template_name");
            String barcodeText = resultSet.getString("barcode_text");
            String qrcode = resultSet.getString("qrcode");
            String qrcodeText = resultSet.getString("qrcode_text");

            String r1 = resultSet.getString("r1");
            String r2 = resultSet.getString("r2");
            String r3 = resultSet.getString("r3");
            String r4 = resultSet.getString("r4");
            String r5 = resultSet.getString("r5");
            String r6 = resultSet.getString("r6");
            String r7 = resultSet.getString("r7");
            String r8 = resultSet.getString("r8");
            String r9 = resultSet.getString("r9");
            String r10 = resultSet.getString("r10");

            Integer stock = resultSet.getInt("stock");
            Timestamp createTime = resultSet.getTimestamp("create_time");

            Date date = new Date();
            date.setTime(createTime.getTime());
            String createTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);


            return new HangerProduct(
                    barcode, sku, name, size, originalPrice, currentPrice,
                    templateId, templateName, barcodeText, qrcode, qrcodeText, r1, r2,
                    r3, r4, r5, r6, r7, r8, r9, r10, stock, createTimeString
            );
        };
    }
}
