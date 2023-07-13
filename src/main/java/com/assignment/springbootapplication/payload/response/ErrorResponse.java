package com.assignment.springbootapplication.payload.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorResponse {
    private List<Violations> violations = new ArrayList<>();

    public List<Violations> getViolations() {
        return violations;
    }
    public void setViolations(List<Violations> violations) {
        this.violations = violations;
    }
}


