package com.assignment.springbootapplication.payload.response;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private List<Violations> violations = new ArrayList<>();
    public List<Violations> getViolations() {
        return violations;
    }
    public void setViolations(List<Violations> violations) {
        this.violations = violations;
    }
}


