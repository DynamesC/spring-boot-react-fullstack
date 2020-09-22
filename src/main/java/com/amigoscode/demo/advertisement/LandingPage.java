package com.amigoscode.demo.advertisement;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class LandingPage {

    private final String landingPageId;

    @NotBlank
    private final String name;

    @NotBlank
    private final String url;

    @NotBlank
    private final String description;

    private final String largeDemoId;

    private final String smallDemoId;

    public LandingPage(@JsonProperty("landingPageId") String landingPageId,
                       @JsonProperty("name") String name,
                       @JsonProperty("url") String url,
                       @JsonProperty("description") String description,
                       @JsonProperty("largeDemoId") String largeDemoId,
                       @JsonProperty("smallDemoId") String smallDemoId) {
        this.landingPageId = landingPageId;
        this.name = name;
        this.url = url;
        this.description = description;
        this.smallDemoId = smallDemoId;
        this.largeDemoId = largeDemoId;
    }

    public String getLandingPageId() {
        return landingPageId;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getLargeDemoId() {
        return largeDemoId;
    }

    public String getSmallDemoId() {
        return smallDemoId;
    }

    @Override
    public String toString() {
        return "LandingPage{" +
                "landingPageId=" + landingPageId +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", smallDemoId='" + smallDemoId + '\'' +
                ", largeDemoId='" + largeDemoId + '\'' +
                '}';
    }

}
