package com.github.commitscrawler.crawler;

import com.github.commitscrawler.domain.commit.CommitDetail;
import com.github.commitscrawler.domain.dto.CommitDetailRequest;
import com.github.commitscrawler.parser.GitCommitHtmlParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.github.commitscrawler.crawler.GitApiCrawler.BASE_URL;


class GitHtmlParserTest {

    @Test
    void gitCommitHtmlParser() {
        GitCommitHtmlParser gcht = new GitCommitHtmlParser();





    }

    @Test
    @DisplayName("owner의 commitDetail의 목록")
    void aMemberCommitList() {
        GitHtmlParser gitHtmlParser = new GitHtmlParser(WebClient.create(BASE_URL),
                new GitCommitHtmlParser());
        List<CommitDetail> commitDetails = gitHtmlParser.crawlCommitDetail(new CommitDetailRequest("Kyeongrok","java-algorithm"));


    }
}