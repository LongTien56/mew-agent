package com.example.mewagent.repositories;

import com.example.mewagent.model.WebsitePattern;
import java.util.Optional;

public interface IWebsitePatternRepository {
    void save(WebsitePattern pattern);
    Optional<WebsitePattern> findByDomainAndTaskType(String websiteDomain, String taskType);
}
