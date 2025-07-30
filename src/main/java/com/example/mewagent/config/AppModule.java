package com.example.mewagent.config;

import com.example.mewagent.service.AgentService;
import com.example.mewagent.service.TaskExecutionService;
import com.example.mewagent.service.interfaces.IAgentService;
import com.example.mewagent.service.interfaces.ITaskExecutionService;
import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IAgentService.class).to(AgentService.class);

        bind(ITaskExecutionService.class).to(TaskExecutionService.class);
    }
}
