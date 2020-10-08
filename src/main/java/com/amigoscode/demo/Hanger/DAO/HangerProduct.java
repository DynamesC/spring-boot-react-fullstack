package com.amigoscode.demo.Hanger.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class HangerProduct {
    @NotNull
    private final String barcode;

    private final String sku;

    @NotNull
    private final String name;

    private final String size;

    @NotNull(message = "test missing original price message")
    private final Double originalPrice;
    @NotNull
    private final Double currentPrice;
    @NotNull
    private final String templateId;

    private final String templateName;

    private final String barcodeText;

    private final String qrcode;

    private final String qrcodeText;

    private final String r1;
    private final String r2;
    private final String r3;
    private final String r4;
    private final String r5;
    private final String r6;
    private final String r7;
    private final String r8;
    private final String r9;
    private final String r10;
    private final Integer stock;

    private final String createTime;


    public HangerProduct(@JsonProperty("barcode") String barcode,
                       @JsonProperty("sku") String sku,
                       @JsonProperty("name") String name,
                         @JsonProperty("size") String size,
                       @JsonProperty("originalPrice") Double originalPrice,
                         @JsonProperty("currentPrice") Double currentPrice,
                         @JsonProperty("templateId") String templateId,
                         @JsonProperty("templateName") String templateName,
                         @JsonProperty("barcodeText") String barcodeText,
                         @JsonProperty("qrcode") String qrcode,
                         @JsonProperty("qrcodeText") String qrcodeText,
                         @JsonProperty("r1") String r1,
                         @JsonProperty("r2") String r2,
                         @JsonProperty("r3") String r3,
                         @JsonProperty("r4") String r4,
                         @JsonProperty("r5") String r5,
                         @JsonProperty("r6") String r6,
                         @JsonProperty("r7") String r7,
                         @JsonProperty("r8") String r8,
                         @JsonProperty("r9") String r9,
                         @JsonProperty("r10") String r10,
                         @JsonProperty("stock") Integer stock,
                         @JsonProperty("createTime") String createTime) {
        this.barcode = barcode;
        this.sku = sku;
        this.name = name;
        this.size = size;
        this.originalPrice = originalPrice;
        this.currentPrice = currentPrice;
        this.templateId = templateId;
        this.templateName = templateName;
        this.barcodeText = barcodeText;
        this.qrcode = qrcode;
        this.qrcodeText = qrcodeText;

        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
        this.r5 = r5;
        this.r6 = r6;
        this.r7 = r7;
        this.r8 = r8;
        this.r9 = r9;
        this.r10 = r10;

        this.stock = stock;
        this.createTime = createTime;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getBarcodeText() {
        return barcodeText;
    }

    public String getQrcode() {
        return qrcode;
    }

    public String getQrcodeText() {
        return qrcodeText;
    }

    public String getR1() {
        return r1;
    }

    public String getR2() {
        return r2;
    }

    public String getR3() {
        return r3;
    }

    public String getR4() {
        return r4;
    }

    public String getR5() {
        return r5;
    }

    public String getR6() {
        return r6;
    }

    public String getR7() {
        return r7;
    }

    public String getR8() {
        return r8;
    }

    public String getR9() {
        return r9;
    }

    public String getR10() {
        return r10;
    }

    public Integer getStock() {
        return stock;
    }

    public String getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "LandingPage{" +
                "barcode=" + barcode +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", originalPrice='" + originalPrice + '\'' +
                ", currentPrice='" + currentPrice + '\'' +
                ", templateId='" + templateId + '\'' +
                '}';
    }


}
