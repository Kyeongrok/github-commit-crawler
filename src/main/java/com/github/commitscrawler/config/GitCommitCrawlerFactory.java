package com.github.commitscrawler.config;

import com.github.commitscrawler.context.DataReader;
import com.github.commitscrawler.context.Parser;
import com.github.commitscrawler.context.googlesheets.GoogleSheets;
import com.github.commitscrawler.context.googlesheets.GoogleSheetsMemberParser;
import com.github.commitscrawler.context.googlesheets.GoogleSheetsDataReader;
import com.github.commitscrawler.crawler.CommitDetailCrawler;
import com.github.commitscrawler.crawler.GitCommitCrawler;
import com.github.commitscrawler.domain.Member;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GitCommitCrawlerFactory {
    @Bean
    public GitCommitCrawler gitCommitCrawler(CommitDetailCrawler commitDetailStrategy, DataReader dataReader) {
        return new GitCommitCrawler(commitDetailStrategy, dataReader);
    }

    @Bean
    public DataReader googleSheetsReader(GoogleSheets googleSheets, Parser<Member, List<Object>> parser) {
        return new GoogleSheetsDataReader(googleSheets, parser);
    }

    @Bean
    public GoogleSheets googleSheets() {
        return new GoogleSheets();
    }

    @Bean
    public Parser<Member, List<Object>> googleSheetsMemberParser() {
        return new GoogleSheetsMemberParser();
    }
}
