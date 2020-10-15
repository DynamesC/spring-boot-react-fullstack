package com.amigoscode.demo.Hanger.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class BindLabelRequest {
    @NotNull
    List<String> labelCodes;
    @NotNull
    @NotBlank
    String productBarcode;

    public List<String> getLabelCodes() {
        return labelCodes;
    }

    public void setLabelCodes(List<String> labelCodes) {
        this.labelCodes = labelCodes;
    }

    public String getProductBarcode() {
        return productBarcode;
    }

    public void setProductBarcode(String productBarcode) {
        this.productBarcode = productBarcode;
    }

    public BindLabelRequest(@NotNull List<String> labelCodes, @NotNull @NotBlank String productBarcode) {
        this.labelCodes = labelCodes;
        this.productBarcode = productBarcode;
    }
}
