package com.example.mewagent.service.interfaces;

public interface IAgentService {
    String executeCommand(String command);
    boolean isReady();
    String getStatus();
}
