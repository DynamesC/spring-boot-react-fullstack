package com.amigoscode.demo.Device.Label;

import org.json.JSONObject;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

public class UpdateBindingRequest {
    @NotBlank
    private String mac;
    @NotBlank
    private HashMap<String, String> information;
    @NotBlank
    private String demoId;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public HashMap<String, String> getInformation() {
        return information;
    }

    public void setInformation(HashMap<String, String> information) {
        this.information = information;
    }

    public String getDemoId() {
        return demoId;
    }

    public void setDemoId(String demoId) {
        this.demoId = demoId;
    }
}
