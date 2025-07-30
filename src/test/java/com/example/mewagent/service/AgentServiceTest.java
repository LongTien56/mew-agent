package com.example.mewagent.service;

import com.example.mewagent.dto.TaskDTO;
import com.example.mewagent.service.interfaces.IAgentService;
import com.example.mewagent.service.interfaces.ITaskExecutionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Tests for AgentService demonstrating the benefits of dependency injection.
 */
@DisplayName("Agent Service Tests")
class AgentServiceTest {

    @Mock
    private ITaskExecutionService mockTaskExecutionService;

    private IAgentService agentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        agentService = new AgentService(mockTaskExecutionService);
    }

    @Test
    @DisplayName("Should handle purchase commands correctly")
    void testPurchaseCommand() {
        // Given
        String command = "buy laptop from amazon";
        String expectedResult = "Purchase task completed successfully";
        
        when(mockTaskExecutionService.executeTask(anyString(), anyString(), any(Map.class)))
            .thenReturn(expectedResult);

        // When
        String result = agentService.executeCommand(command);

        // Then
        assertEquals(expectedResult, result);
        verify(mockTaskExecutionService).executeTask(
            eq("Search for 'laptop' and add to cart"),
            eq("https://amazon.com"),
            any(Map.class)
        );
    }

    @Test
    @DisplayName("Should handle task update commands correctly")
    void testTaskUpdateCommand() {
        // Given
        String command = "update task progress on trello";
        String expectedResult = "Task updated successfully";
        
        when(mockTaskExecutionService.executeTask(anyString(), anyString(), any(Map.class)))
            .thenReturn(expectedResult);

        // When
        String result = agentService.executeCommand(command);

        // Then
        assertEquals(expectedResult, result);
        verify(mockTaskExecutionService).executeTask(
            eq("Update task: update task progress"),
            eq("https://trello.com"),
            any(Map.class)
        );
    }

    @Test
    @DisplayName("Should handle basic commands without task execution")
    void testBasicCommands() {
        // Test hello command
        String result = agentService.executeCommand("hello");
        assertTrue(result.contains("Hello! I'm Mew Agent"));
        verifyNoInteractions(mockTaskExecutionService);

        // Test help command
        result = agentService.executeCommand("help");
        assertTrue(result.contains("Available commands"));
        verifyNoInteractions(mockTaskExecutionService);

        // Test version command
        result = agentService.executeCommand("version");
        assertTrue(result.contains("Mew Agent v1.0.0"));
        verifyNoInteractions(mockTaskExecutionService);
    }

    @Test
    @DisplayName("Should handle unknown commands gracefully")
    void testUnknownCommand() {
        String result = agentService.executeCommand("unknown command");
        assertTrue(result.contains("Unknown command"));
        verifyNoInteractions(mockTaskExecutionService);
    }

    @Test
    @DisplayName("Should handle task execution errors gracefully")
    void testTaskExecutionError() {
        // Given
        String command = "buy something from somewhere";
        when(mockTaskExecutionService.executeTask(anyString(), anyString(), any(Map.class)))
            .thenThrow(new RuntimeException("Task execution failed"));

        // When
        String result = agentService.executeCommand(command);

        // Then
        assertTrue(result.startsWith("Error:"));
        assertTrue(result.contains("Task execution failed"));
    }

    @Test
    @DisplayName("Should report ready status when properly initialized")
    void testServiceReadiness() {
        assertTrue(agentService.isReady());
        assertEquals("AI Agent is ready. Services initialized successfully.", agentService.getStatus());
    }

    @Test
    @DisplayName("Should extract parameters correctly from purchase commands")
    void testParameterExtraction() {
        // Given
        String command = "buy wireless headphones from amazon";
        
        when(mockTaskExecutionService.executeTask(anyString(), anyString(), any(Map.class)))
            .thenAnswer(invocation -> {
                Map<String, Object> parameters = invocation.getArgument(2);
                assertEquals("wireless headphones", parameters.get("item"));
                assertEquals("purchase", parameters.get("action"));
                return "Success";
            });

        // When
        agentService.executeCommand(command);

        // Then
        verify(mockTaskExecutionService).executeTask(anyString(), anyString(), any(Map.class));
    }
}
