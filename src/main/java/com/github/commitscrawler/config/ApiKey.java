package com.github.commitscrawler.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiKey {
    @Value("${git.token}")
    private String gitToken;

    public String getGitToken() {
        return gitToken;
    }
}
