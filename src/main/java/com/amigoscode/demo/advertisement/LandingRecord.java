package com.amigoscode.demo.advertisement;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Calendar;

public class LandingRecord {

    private final String landingRecordId;

    @NotBlank
    private final String adId;

    @NotBlank
    private final String landingPageId;

    @NotBlank
    private final String deviceId;

    @NotBlank
    private final Calendar landingTime;

    @NotBlank
    private final String description;

    public LandingRecord(@JsonProperty("landingRecordId") String landingRecordId,
                       @JsonProperty("adId") String adId,
                       @JsonProperty("landingPageId") String landingPageId,
                       @JsonProperty("deviceId") String deviceId,
                         @JsonProperty("landingTime") Calendar landingTime,
                         @JsonProperty("description") String description) {
        this.landingRecordId = landingRecordId;
        this.adId = adId;
        this.landingPageId = landingPageId;
        this.deviceId = deviceId;
        this.landingTime = landingTime;
        this.description = description;
    }

    public String getLandingRecordId() {
        return landingRecordId;
    }

    public String getAdId() {
        return adId;
    }

    public String getLandingPageId() {
        return landingPageId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Calendar getLandingTime() {
        return landingTime;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "LandingRecord{" +
                "landingRecordId=" + landingRecordId +
                ", adId='" + adId + '\'' +
                ", landingPageId='" + landingPageId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", landingTime='" + landingTime.toString() + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
