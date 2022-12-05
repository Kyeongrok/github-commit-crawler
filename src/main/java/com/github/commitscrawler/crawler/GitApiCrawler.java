package com.github.commitscrawler.crawler;

import com.github.commitscrawler.domain.commit.CommitDetail;
import com.github.commitscrawler.domain.entity.StudentCommit;
import com.github.commitscrawler.exception.IllegalRepositoryAddressException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

public class GitApiCrawler implements CommitDetailCrawler {
    public static final String BASE_URL = "https://api.github.com";
    public static final String HTTP_URL = "https://github.com";
    public static final int DEFAULT_PER_PAGE = 3;
    public static final int DEFAULT_PAGE = 1;
    private final WebClient client;

    public GitApiCrawler(WebClient client) {
        this.client = client;
    }

    @Override
    public StudentCommit crawlStudentCommit(String name, String subject, String repositoryURL) throws RuntimeException {
        if (!verify(repositoryURL)) {
            throw new RuntimeException(String.format("%s : 주소를 확인해주세요.", repositoryURL));
        }
        List<CommitDetail> commitDetails = this.client.get().uri(buildCommitUrl(removeDomain(repositoryURL)))
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToFlux(CommitDetail.class);
                    }
                    return Flux.error(new IllegalRepositoryAddressException(name, subject, repositoryURL));
                })
                .share().collect(Collectors.toList()).block();
        assert commitDetails != null;
        return commitDetails.get(0).toEntity(name, subject);
    }

    private String buildCommitUrl(String pathOwnerRepository) { // 도메인을 제외한 경로 ex) /{owner}/{repository}
        StringBuilder sb = new StringBuilder("/repos")
                .append(pathOwnerRepository)
                .append("/commits")
                .append("?")
                .append("per_page=").append(DEFAULT_PER_PAGE)
                .append("&")
                .append("page=").append(DEFAULT_PAGE);
        return sb.toString();
    }

    private String removeDomain(String repositoryURL) {
        return repositoryURL.substring(HTTP_URL.length());
    }

    private boolean verify(String repositoryURL) {
        if (repositoryURL.endsWith("/")) {
            return false;
        }
        if (repositoryURL.contains(".git")) {
            return false;
        }
        return true;
    }
}
