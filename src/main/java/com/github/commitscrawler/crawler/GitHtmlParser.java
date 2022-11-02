package com.github.commitscrawler.crawler;

import com.github.commitscrawler.domain.commit.CommitDetail;
import com.github.commitscrawler.domain.dto.CommitDetailRequest;
import com.github.commitscrawler.parser.GitCommitHtmlParser;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class GitHtmlParser implements CommitDetailCrawler {

    private final WebClient client;
    private final GitCommitHtmlParser gitCommitHtmlParser;

    private static final String BASE_URL = "https://github.com";

    public GitHtmlParser(WebClient client, GitCommitHtmlParser gitCommitHtmlParser) {
        this.client = client;
        this.gitCommitHtmlParser = gitCommitHtmlParser;
    }

    @Override
    public List<CommitDetail> crawlCommitDetail(CommitDetailRequest cdr) {
        // cdr의 정보를 받아서 http request를 전송해 List<CommitDetail>을 리턴

        String res = this.client.get().uri(buildCommitUrl(cdr.getOwner(), cdr.getRepo()))
                .accept(MediaType.TEXT_HTML)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // res를 parsing

        System.out.println(res);

        return null;
    }
    private String buildCommitUrl(String owner, String repo) {
        StringBuilder sb = new StringBuilder("")
                .append("/").append(owner)
                .append("/").append(repo)
                .append("/commits");
        return sb.toString();
    }
}
