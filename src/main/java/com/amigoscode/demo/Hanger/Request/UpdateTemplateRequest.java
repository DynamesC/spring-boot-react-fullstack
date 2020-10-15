package com.amigoscode.demo.Hanger.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateTemplateRequest {

    @NotNull
    @NotBlank
    private String id;

    @NotBlank
    private String template_name;

    private String note;

    @NotBlank
    private String wl_21r_id;

    @NotBlank
    private String wl_21b_id;

    public String getId() {
        return id;
    }

    public String getTemplate_name() {
        return template_name;
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
}
