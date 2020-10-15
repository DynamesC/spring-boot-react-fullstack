package com.amigoscode.demo.Hanger.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UnbindLabelsRequest {
    @NotNull
    List<String> labelCodes;

    public List<String> getLabelCodes() {
        return labelCodes;
    }

    public void setLabelCodes(List<String> labelCodes) {
        this.labelCodes = labelCodes;
    }
}
