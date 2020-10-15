package com.amigoscode.demo.Hanger.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SingleBindRequest {
    @NotBlank
    @NotNull
    private String eslCode;
    @NotBlank
    @NotNull
    private String productCode;
    private String templateCode;

    public String getEslCode() {
        return eslCode;
    }

    public void setEslCode(String eslCode) {
        this.eslCode = eslCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}
