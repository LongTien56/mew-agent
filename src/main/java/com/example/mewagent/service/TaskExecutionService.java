package com.example.mewagent.service;

import com.example.mewagent.dto.WebAutomationPlanDTO;
import com.example.mewagent.service.interfaces.ILLMService;
import com.example.mewagent.service.interfaces.ITaskExecutionService;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Map;

public class TaskExecutionService implements ITaskExecutionService {

    private final ILLMService llmService;
    private WebDriver driver;

    @Inject
    public TaskExecutionService(ILLMService llmService) {
        this.llmService = llmService;
    }

    @Override
    public String executeTask(String command, String targetUrl, Map<String, Object> parameters) {
        try {
            setupWebDriver();
            driver.get(targetUrl);
            // Wait for the page to load
            Thread.sleep(3000);

            // Get the current state of the page and ask the LLM for a plan
            String pageHtml = driver.getPageSource();
            WebAutomationPlanDTO plan = llmService.generatePlan(command, pageHtml);

            if (plan == null || plan.getSteps() == null || plan.getSteps().isEmpty()) {
                return "Failed to generate a valid automation plan.";
            }

            // Execute the plan step-by-step
            for (WebAutomationPlanDTO.Step step : plan.getSteps()) {
                executeStep(step);
            }

            return "Automation task completed successfully. AI thought: " + plan.getThought();

        } catch (Exception e) {
            e.printStackTrace();
            return "Task failed during execution: " + e.getMessage();
        } finally {
            cleanup();
        }
    }

    private void executeStep(WebAutomationPlanDTO.Step step) throws InterruptedException {
        System.out.println("Executing step: " + step.getAction() + " on " + step.getCssSelector());
        WebElement element = null;

        // Find the element if a selector is provided
        if (step.getCssSelector() != null && !step.getCssSelector().isEmpty()) {
            element = driver.findElement(By.cssSelector(step.getCssSelector()));
        }

        switch (step.getAction().toUpperCase()) {
            case "NAVIGATE":
                driver.get(step.getValue());
                break;
            case "TYPE":
                if (element != null) {
                    element.sendKeys(step.getValue());
                }
                break;
            case "CLICK":
                if (element != null) {
                    element.click();
                }
                break;
            case "EXTRACT_TEXT":
                 // Not implemented yet, but the structure is here
                break;
            default:
                System.err.println("Unknown action: " + step.getAction());
        }
        // Wait a moment after each action to let the UI update
        Thread.sleep(1500);
    }

    private void setupWebDriver() {
        // You must have chromedriver installed and in your system's PATH,
        // or specify its location via a system property.
        // System.setProperty("webdriver.chrome.driver", "C:/path/to/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless"); // Run without opening a browser window
        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private void cleanup() {
        if (driver != null) {
            driver.quit();
        }
    }
}
