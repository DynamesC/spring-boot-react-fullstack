package com.amigoscode.demo.Hanger.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ProductSwitchTemplateRequest {
    @NotNull
    private List<String> barcodes;

    @NotNull
    @NotBlank
    private String templateId;

    public List<String> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(List<String> barcodes) {
        this.barcodes = barcodes;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}
