package com.example.mewagent.dto;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class WebAutomationPlanDTO {
    private String thought; // The AI's reasoning
    private List<Step> steps;
    
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Step {
        private String action; // e.g., "NAVIGATE", "TYPE", "CLICK"
        private String cssSelector;
        private String value;
    }
}