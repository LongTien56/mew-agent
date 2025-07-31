package com.example.mewagent.service;

import com.example.mewagent.dto.WebAutomationPlanDTO;
import com.example.mewagent.service.interfaces.ILLMService;
import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.GenerationConfig;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LLMService implements ILLMService {

    // IMPORTANT: Replace "YOUR_API_KEY" with your actual Google AI Studio API key.
    private static final String API_KEY = "YOUR_API_KEY";
    private final GenerativeModelFutures model;
    private final Gson gson = new Gson();

    public LLMService() {
        // For multi-turn conversations, use a `ChatFutures` instance
        GenerativeModel generativeModel = new GenerativeModel("gemini-1.5-flash", API_KEY);
        this.model = GenerativeModelFutures.from(generativeModel);
    }

    @Override
    public WebAutomationPlanDTO generatePlan(String command, String websiteHtml) {
        String prompt = buildPrompt(command, websiteHtml);
        Content content = new Content.Builder().addText(prompt).build();
        Executor executor = Executors.newSingleThreadExecutor();

        // Set generation config to force JSON output
        GenerationConfig.Builder configBuilder = new GenerationConfig.Builder();
        configBuilder.responseMimeType = "application/json";
        GenerationConfig generationConfig = configBuilder.build();

        try {
            ListenableFuture<GenerateContentResponse> future = model.generateContent(content, generationConfig);
            GenerateContentResponse response = future.get(); // Blocking call for simplicity
            String jsonResponse = response.getText();

            // Clean the response to ensure it's valid JSON
            String cleanedJson = cleanJson(jsonResponse);
            return gson.fromJson(cleanedJson, WebAutomationPlanDTO.class);

        } catch (Exception e) {
            System.err.println("Error calling Gemini API: " + e.getMessage());
            // In case of error, return a fallback plan
            return createFallbackPlan("Could not generate plan due to an API error.");
        }
    }

    private String buildPrompt(String command, String websiteHtml) {
        // A detailed prompt to guide the AI
        return "You are an expert web automation assistant. Your task is to create a step-by-step plan to accomplish a user's goal on a given website.\n\n" +
                "User's command: \"" + command + "\"\n\n" +
                "Analyze the following HTML content of the website and generate a JSON plan. The plan should only include actions like 'NAVIGATE', 'TYPE', 'CLICK', and 'EXTRACT_TEXT'.\n\n" +
                "Rules:\n" +
                "1.  Use robust CSS selectors. Prefer IDs, then data-attributes, then class names.\n" +
                "2.  The `value` field should be used for the text to type in 'TYPE' actions.\n" +
                "3.  The output MUST be a valid JSON object matching the required structure.\n\n" +
                "Here is the HTML snapshot:\n" +
                "```html\n" +
                (websiteHtml.length() > 6000 ? websiteHtml.substring(0, 6000) : websiteHtml) + "\n" + // Truncate long HTML
                "```\n\n" +
                "Produce only the JSON plan now.";
    }

    private String cleanJson(String rawResponse) {
        // LLMs sometimes wrap their JSON in ```json ... ```, so we need to clean it.
        return rawResponse.trim().replace("```json", "").replace("```", "").trim();
    }
    
    private WebAutomationPlanDTO createFallbackPlan(String reason) {
        WebAutomationPlanDTO plan = new WebAutomationPlanDTO();
        plan.setThought("Fallback plan created: " + reason);
        plan.setSteps(java.util.Collections.emptyList());
        return plan;
    }
}
