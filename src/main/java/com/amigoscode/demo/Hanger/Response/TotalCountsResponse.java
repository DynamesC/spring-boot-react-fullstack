package com.amigoscode.demo.Hanger.Response;

public class TotalCountsResponse {
    private int productCount;
    private int labelCount;
    private int templateCount;

    public TotalCountsResponse(int productCount, int labelCount, int templateCount) {
        this.productCount = productCount;
        this.labelCount = labelCount;
        this.templateCount = templateCount;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public int getLabelCount() {
        return labelCount;
    }

    public void setLabelCount(int labelCount) {
        this.labelCount = labelCount;
    }

    public int getTemplateCount() {
        return templateCount;
    }

    public void setTemplateCount(int templateCount) {
        this.templateCount = templateCount;
    }
}
