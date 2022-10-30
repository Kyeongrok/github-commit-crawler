package com.github.commitscrawler.config;

import com.github.commitscrawler.context.MemberReader;
import com.github.commitscrawler.context.Parser;
import com.github.commitscrawler.context.googlesheets.GoogleSheetsMemberParser;
import com.github.commitscrawler.context.googlesheets.GoogleSheetsReader;
import com.github.commitscrawler.crawler.CommitDetailCrawler;
import com.github.commitscrawler.crawler.GitApiCrawler;
import com.github.commitscrawler.crawler.GitCommitCrawler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GitCommitCrawlerFactory {
    @Bean
    public GitCommitCrawler gitCommitCrawler(CommitDetailCrawler commitDetailStrategy, MemberReader memberReader) {
        return new GitCommitCrawler(commitDetailStrategy, memberReader);
    }

    @Bean
    public CommitDetailCrawler gitApiCrawler(WebClient.Builder builder, ApiKey apiKey) {
        return new GitApiCrawler(builder, apiKey);
    }

    @Bean
    public MemberReader googleSheetsReader(Parser parser) {
        return new GoogleSheetsReader(parser);
    }

    @Bean
    public Parser googleSheetsMemberParser() {
        return new GoogleSheetsMemberParser();
    }
}
