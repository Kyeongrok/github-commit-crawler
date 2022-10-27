package com.github.commitscrawler.crawler;

import com.github.commitscrawler.config.ApiKey;
import com.github.commitscrawler.domain.Repository;
import com.github.commitscrawler.domain.commit.CommitDetail;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

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

    public Repository getLatestRepository(String gitUsername) {
        Repository latest = this.client.get().uri(uriBuilder ->
                        uriBuilder.path("/users")
                                .path("/" + gitUsername)
                                .path("/repos")
                                .queryParam("sort", "pushed")// 최근 push 정렬
                                .queryParam("per_page", "1")// 페이징 요소 5개 제한
                                .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToFlux(Repository.class).collect(Collectors.toList()).block().get(0);
        if (latest == null) throw new RuntimeException("해당 깃허브 유저가 없습니다.");
        return latest;
    }

    public List<CommitDetail> getRepositoryCommitDetails(Repository repository) {
        return getRepositoryCommitDetails(repository, DEFAULT_PER_PAGE, DEFAULT_PAGE);
    }

    public List<CommitDetail> getRepositoryCommitDetails(Repository repository, int perPage, int page) {
        return this.client.get().uri(uriBuilder ->
                        uriBuilder.path(repository.getCommitsUrlOnlyPath())
                                .queryParam("per_page", perPage)
                                .queryParam("page", page)
                                .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToFlux(CommitDetail.class).collect(Collectors.toList()).block();
    }
}
