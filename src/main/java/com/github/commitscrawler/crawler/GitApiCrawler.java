package com.github.commitscrawler.crawler;

import com.github.commitscrawler.config.ApiKey;
import com.github.commitscrawler.domain.commit.CommitDetail;
import com.github.commitscrawler.domain.dto.CommitDetailRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

public class GitApiCrawler implements CommitDetailCrawler {
    public static final String BASE_URL = "https://api.github.com";
    private final WebClient client;

    public GitApiCrawler(WebClient.Builder builder, ApiKey apiKey) {
        System.out.printf("토큰 : %s\n", apiKey.getGitToken());
        this.client = builder.baseUrl(BASE_URL)
                .defaultHeader("Authorization", apiKey.getGitToken())
                .build();
    }

    @Override
    public List<CommitDetail> crawlCommitDetail(CommitDetailRequest cdr) throws RuntimeException {
        return this.client.get().uri(buildCommitUrl(cdr))
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToFlux(CommitDetail.class);
                    }
                    return Flux.error(new RuntimeException(String.format("[%s] %s 리포지토리 오류\n", response.statusCode(), cdr.getOwner())));
                })
                .share().collect(Collectors.toList()).block();
    }

    private String buildCommitUrl(CommitDetailRequest cdr) {
        StringBuilder sb = new StringBuilder("/repos")
                .append("/").append(cdr.getOwner())
                .append("/").append(cdr.getRepo())
                .append("/commits")
                .append("?")
                .append("per_page=").append(cdr.getPerPage())
                .append("&")
                .append("page=").append(cdr.getPage());
        return sb.toString();
    }
}
