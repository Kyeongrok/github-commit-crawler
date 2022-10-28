package com.github.commitscrawler.crawler;

import com.github.commitscrawler.domain.commit.CommitDetail;
import com.github.commitscrawler.domain.dto.CommitDetailRequest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

public class GitHtmlParser implements CommitDetailCrawler {

    private final WebClient client;

    private static final String BASE_URL = "https://github.com";

    public GitHtmlParser(WebClient client) {
        this.client = client;
    }

    @Override
    public List<CommitDetail> crawlCommitDetail(CommitDetailRequest cdr) {
        // cdr의 정보를 받아서 http request를 전송해 List<CommitDetail>을 리턴

        Mono<String> res = this.client.get().uri(buildCommitUrl(cdr.getOwner(), cdr.getRepo()))
                .accept(MediaType.TEXT_HTML)
                .retrieve()
                .bodyToMono(String.class);
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
