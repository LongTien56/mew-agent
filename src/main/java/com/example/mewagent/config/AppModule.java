package com.example.mewagent.config;

import com.example.mewagent.service.AgentService;
import com.example.mewagent.service.TaskExecutionService;
import com.example.mewagent.service.interfaces.IAgentService;
import com.example.mewagent.service.interfaces.ITaskExecutionService;
import com.google.inject.AbstractModule;
import com.example.mewagent.repository.ITaskRepository;
import com.example.mewagent.repository.SQLiteTaskRepository;
import com.example.mewagent.repository.WebsitePatternRepository;
import com.example.mewagent.repository.IWebsitePatternRepository;
import com.example.mewagent.service.interfaces.ILLMService;
import com.example.mewagent.service.LLMService;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IAgentService.class).to(AgentService.class);

        bind(ITaskExecutionService.class).to(TaskExecutionService.class);
        bind(ITaskRepository.class).to(SQLiteTaskRepository.class);
        // bind(IWebsitePatternRepository.class).to(WebsitePatternRepository.class);
        bind(ILLMService.class).to(LLMService.class);
    }
}
