package com.amigoscode.demo.Hanger.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateTemplateRequest {
    @NotNull
    @NotBlank
    private String template_name;

    private String note;

    @NotNull
    @NotBlank
    private String wl_21r_id;

    @NotNull
    @NotBlank
    private String wl_21b_id;

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getWl_21r_id() {
        return wl_21r_id;
    }

    public void setWl_21r_id(String wl_21r_id) {
        this.wl_21r_id = wl_21r_id;
    }

    public String getWl_21b_id() {
        return wl_21b_id;
    }

    public void setWl_21b_id(String wl_21b_id) {
        this.wl_21b_id = wl_21b_id;
    }
}
