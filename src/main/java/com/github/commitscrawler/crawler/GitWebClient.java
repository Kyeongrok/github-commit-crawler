package com.github.commitscrawler.crawler;

import com.github.commitscrawler.config.ApiKey;
import com.github.commitscrawler.domain.commit.CommitDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GitWebClient {
    public static final String BASE_URL = "https://api.github.com";
    public static final int DEFAULT_PER_PAGE = 5;
    public static final int DEFAULT_PAGE = 1;
    private final WebClient client;

    public GitWebClient(WebClient.Builder builder, ApiKey apiKey) {
        System.out.printf("토큰 : %s\n", apiKey.getGitToken());
        this.client = builder.baseUrl(BASE_URL)
                .defaultHeader("Authorization", apiKey.getGitToken())
                .build();
    }

    public CommitDetail getLatestCommitDetail(String owner, String repo) {
        return getCommitDetails(owner, repo, 1, 1).blockFirst();
    }

    public Flux<CommitDetail> getCommitDetails(String owner, String repo) {
        return getCommitDetails(owner, repo, DEFAULT_PER_PAGE, DEFAULT_PAGE);
    }

    public Flux<CommitDetail> getCommitDetails(String owner, String repo, int perPage, int page) {
        return this.client.get().uri(buildCommitUrl(owner, repo, perPage, page))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    System.out.println((response.bodyToMono(String.class)));
                    return Mono.empty();
                })
                .bodyToFlux(CommitDetail.class);
    }

    private String buildCommitUrl(String owner, String repo, int perPage, int page) {
        StringBuilder sb = new StringBuilder("/repos")
                                    .append("/").append(owner)
                                    .append("/").append(repo)
                                    .append("/commits")
                                    .append("?")
                                    .append("per_page=").append(perPage)
                                    .append("&")
                                    .append("page=").append(page);
        return sb.toString();
    }
}
