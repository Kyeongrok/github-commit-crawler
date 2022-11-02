package com.github.commitscrawler.config;

import com.github.commitscrawler.crawler.CommitDetailCrawler;
import com.github.commitscrawler.crawler.GitApiCrawler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import static com.github.commitscrawler.crawler.GitApiCrawler.BASE_URL;

@Configuration
public class DefaultCommitDetailCrawlerFactory {
    private String gitToken = System.getenv().get("GIT_TOKEN");
    @Bean
    public CommitDetailCrawler commitDetailCrawler() {
        return new GitApiCrawler(getGitApiWebClient());
    }

    private WebClient getGitApiWebClient() {
        return WebClient.builder().baseUrl(BASE_URL)
                .defaultHeader("Authorization", "Bearer " + gitToken)
                .build();
    }
}
