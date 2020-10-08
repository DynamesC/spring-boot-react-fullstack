package com.amigoscode.demo.Hanger.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class HangerTemplate {

    @NotBlank
    private final String templateId;
    @NotBlank
    private final String name;

    private final String note;

    private final String wl_21r_id;

    private final String wl_21b_id;

    private final String createTime;

    public HangerTemplate(@JsonProperty("templateId") String templateId,
                       @JsonProperty("name") String name,
                       @JsonProperty("note") String note,
                       @JsonProperty("wl_21r_id") String wl_21r_id,
                          @JsonProperty("wl_21b_id") String wl_21b_id,
                          @JsonProperty("createTime") String createTime) {
        this.templateId = templateId;
        this.name = name;
        this.note = note;
        this.wl_21r_id = wl_21r_id;
        this.wl_21b_id = wl_21b_id;
        this.createTime = createTime;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public String getWl_21r_id() {
        return wl_21r_id;
    }

    public String getWl_21b_id() {
        return wl_21b_id;
    }

    public String getCreateTime() {
        return createTime;
    }

}
