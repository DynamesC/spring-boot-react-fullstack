package com.amigoscode.demo.Hanger.Request;

import javax.validation.constraints.NotNull;
import java.util.List;

public class RemoveProductsRequest {

    @NotNull
    private List<String> barcodes;

    public List<String> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(List<String> barcodes) {
        this.barcodes = barcodes;
    }

    public RemoveProductsRequest(@NotNull List<String> barcodes) {
        this.barcodes = barcodes;
    }

    public RemoveProductsRequest(){

    }
}
