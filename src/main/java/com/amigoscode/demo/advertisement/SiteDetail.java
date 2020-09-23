package com.amigoscode.demo.advertisement;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class SiteDetail {
    @NotBlank
    private final String siteId;
    @NotBlank
    private final String name;

    private int deviceCount = 0;

    private int scanCount = 0;

    public SiteDetail(@JsonProperty("siteId") String siteId,
                       @JsonProperty("name") String name) {
        this.name = name;
        this.siteId = siteId;
    }

    public SiteDetail(@JsonProperty("siteId") String siteId,
                      @JsonProperty("name") String name,
                      @JsonProperty("deviceCount") int deviceCount,
                      @JsonProperty("scanCount") int scanCount) {
        this.name = name;
        this.siteId = siteId;
        this.deviceCount = deviceCount;
        this.scanCount = scanCount;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getName() {
        return name;
    }

    public int getDeviceCount() {
        return deviceCount;
    }

    public int getScanCount() {
        return scanCount;
    }

    public void setDeviceCount(int deviceCount) {
        this.deviceCount = deviceCount;
    }

    public void setScanCount(int scanCount) {
        this.scanCount = scanCount;
    }

    @Override
    public String toString() {
        return "SiteDetail{" +
                "siteId=" + siteId +
                ", name='" + name + '\'' +
                ", deviceCount='" + deviceCount + '\'' +
                ", scanCount='" + scanCount + '\'' +
                '}';
    }
}
