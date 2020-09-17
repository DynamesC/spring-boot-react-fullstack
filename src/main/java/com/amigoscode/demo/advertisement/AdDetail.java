package com.amigoscode.demo.advertisement;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class AdDetail {

    @NotBlank
    private final String adId;

    @NotBlank
    private final String name;

    @NotBlank
    private final int scanCount;

    private final String lastScanTime;

    @NotBlank
    private final int scanCount24H;

    public AdDetail(@JsonProperty("adId") String adId,
                       @JsonProperty("name") String name,
                       @JsonProperty("scanCount") int scanCount,
                       @JsonProperty("lastScanTime") String lastScanTime,
                        @JsonProperty("scanCount24H") int scanCount24H) {
        this.adId = adId;
        this.name = name;
        this.scanCount = scanCount;
        this.lastScanTime = lastScanTime;
        this.scanCount24H = scanCount24H;
    }

    public String getAdId() {
        return adId;
    }

    public String getName() {
        return name;
    }

    public int getScanCount() {
        return scanCount;
    }

    public String getLastScanTime() {
        return lastScanTime;
    }

    public int getScanCount24H() {
        return scanCount24H;
    }


    @Override
    public String toString() {
        return "LandingPage{" +
                "adId=" + adId +
                ", name='" + name + '\'' +
                ", scanCount='" + scanCount + '\'' +
                ", lastScanTime='" + lastScanTime + '\'' +
                ", scanCount='" + scanCount24H + '\'' +
                '}';
    }

}
