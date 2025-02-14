package com.gabrielspassos.innter;

import java.util.List;
import java.util.Map;

public class UpdateCreditCardResponse {

    private List<String> updated;
    private Map<String, List<String>> errors;

    public List<String> getUpdated() {
        return updated;
    }

    public void setUpdated(List<String> updated) {
        this.updated = updated;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<String>> errors) {
        this.errors = errors;
    }

}
