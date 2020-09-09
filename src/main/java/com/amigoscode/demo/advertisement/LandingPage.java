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

    public LandingPage(@JsonProperty("landingPageId") String landingPageId,
                   @JsonProperty("name") String name,
                   @JsonProperty("url") String url,
                   @JsonProperty("description") String description) {
        this.landingPageId = landingPageId;
        this.name = name;
        this.url = url;
        this.description = description;
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

    @Override
    public String toString() {
        return "LandingPage{" +
                "landingPageId=" + landingPageId +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
