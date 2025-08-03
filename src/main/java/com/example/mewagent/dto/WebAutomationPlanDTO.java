package com.example.mewagent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebAutomationPlanDTO {
    private String thought;
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
