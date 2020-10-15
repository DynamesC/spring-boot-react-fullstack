package com.amigoscode.demo.Hanger.Request;

import javax.validation.constraints.NotNull;
import java.util.List;

public class RemoveTemplatesRequest {
    @NotNull
    private List<String> templateIds;

    public List<String> getTemplateIds() {
        return templateIds;
    }

    public void setTemplateIds(List<String> templateIds) {
        this.templateIds = templateIds;
    }
}
