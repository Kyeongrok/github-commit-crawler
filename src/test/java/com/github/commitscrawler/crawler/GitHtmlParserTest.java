package com.github.commitscrawler.crawler;

import com.github.commitscrawler.domain.dto.CommitDetailRequest;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static com.github.commitscrawler.crawler.GitApiCrawler.BASE_URL;


class GitHtmlParserTest {

    @Test
    void name() {
        GitHtmlParser gitHtmlParser = new GitHtmlParser(WebClient.create(BASE_URL));
        gitHtmlParser.crawlCommitDetail(new CommitDetailRequest("Kyeongrok","java-algorithm"));
    }
}