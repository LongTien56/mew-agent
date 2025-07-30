package com.example.mewagent.repositories;

import com.example.mewagent.model.WebsitePattern;
import java.util.Optinal;

public class WebsitePatternRepository implements IWebsitePatternRepository {
    
    @Override 
    public void save(WebsitePattern pattern){
        System.out.println("Saved the Website Parrtern");
    }

    @Override
    public Optional<WebsitePattern> findbyDomainAndTaskType(String websiteDomain, String taskType){
        return;
    }
}
