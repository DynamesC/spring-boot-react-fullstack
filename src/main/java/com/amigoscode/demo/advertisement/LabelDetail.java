package com.amigoscode.demo.advertisement;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class LabelDetail {

    @NotBlank
    private final String mac;
    @NotBlank
    private final String name;

    private final String siteId;

    private final String siteName;

    public LabelDetail(@JsonProperty("mac") String mac,
                    @JsonProperty("name") String name,
                    @JsonProperty("siteId") String siteId,
                    @JsonProperty("siteName") String siteName) {
        this.mac = mac;
        this.name = name;
        this.siteId = siteId;
        this.siteName = siteName;
    }

    public String getMac() {
        return mac;
    }

    public String getName() {
        return name;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    @Override
    public String toString() {
        return "LandingPage{" +
                "mac=" + mac +
                ", name='" + name + '\'' +
                ", siteId='" + siteId + '\'' +
                ", siteName='" + siteName + '\'' +
                '}';
    }
}
