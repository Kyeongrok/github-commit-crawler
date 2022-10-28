package com.github.commitscrawler.config;

import com.github.commitscrawler.config.ApiKey;
import com.github.commitscrawler.crawler.CommitDetailCrawler;
import com.github.commitscrawler.crawler.GitApiCrawler;
import com.github.commitscrawler.crawler.GitCommitCrawler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GitCommitCrawlerFactory {
    @Bean
    public GitCommitCrawler gitCommitCrawler(CommitDetailCrawler commitDetailStrategy) {
        return new GitCommitCrawler(commitDetailStrategy);
    }

    @Bean
    public CommitDetailCrawler gitApiCrawler(WebClient.Builder builder, ApiKey apiKey) {
        return new GitApiCrawler(builder, apiKey);
    }
}
