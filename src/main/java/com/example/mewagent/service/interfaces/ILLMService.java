package com.example.mewagent.service.interfaces;

import com.example.mewagent.dto.WebsitePatternDTO;

public interface ILLMService {
    WebAutomationPlanDTO generatePlan(String command, String websiteHtml);
}
