package com.amigoscode.demo.Hanger.Request;

import java.util.List;

public class ToWolinkBindMultipleLabelsRequest {
    private String sign;
    private String store_code;
    private List<BindInfo> f1;

    public ToWolinkBindMultipleLabelsRequest(String sign, String store_code, List<BindInfo> f1) {
        this.sign = sign;
        this.store_code = store_code;
        this.f1 = f1;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getStore_code() {
        return store_code;
    }

    public void setStore_code(String store_code) {
        this.store_code = store_code;
    }

    public List<BindInfo> getF1() {
        return f1;
    }

    public void setF1(List<BindInfo> f1) {
        this.f1 = f1;
    }

    public static class BindInfo{
        private String els_code;
        private String product_code;
        private String esltemplate_id;

        public BindInfo(String els_code, String product_code, String esltemplate_id) {
            this.els_code = els_code;
            this.product_code = product_code;
            this.esltemplate_id = esltemplate_id;
        }

        public String getEls_code() {
            return els_code;
        }

        public void setEls_code(String els_code) {
            this.els_code = els_code;
        }

        public String getProduct_code() {
            return product_code;
        }

        public void setProduct_code(String product_code) {
            this.product_code = product_code;
        }

        public String getEsltemplate_id() {
            return esltemplate_id;
        }

        public void setEsltemplate_id(String esltemplate_id) {
            this.esltemplate_id = esltemplate_id;
        }
    }
}
