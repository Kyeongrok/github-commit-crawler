package com.github.commitscrawler.crawler;

import com.github.commitscrawler.domain.Member;
import com.github.commitscrawler.domain.commit.CommitDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GitWebClientTest {
    @Autowired
    private GitWebClient gitWebClient;

    private final Member member = new Member("황민우", "menuhwang", "LikeLion", "LikeLion");

    @Test
    @DisplayName("SpringBoot 리포지토리 최신 커밋 불러오기")
    void getLatestCommit() {
        CommitDetail commitDetail = gitWebClient.getLatestCommitDetail(member.getGitUsername(), member.getSpringbootRepository());
        System.out.println(commitDetail);
    }

    @Test
    @DisplayName("SpringBoot 리포지토리 커밋 리스트 불러오기 : 페이징 기본 설정")
    void getCommitDetail() {
        Flux<CommitDetail> find = gitWebClient.getCommitDetails(member.getGitUsername(), member.getSpringbootRepository());
        List<CommitDetail> toList = find.collect(Collectors.toList()).share().block();
        System.out.println(toList.size());
        assertTrue(toList.size() <= GitWebClient.DEFAULT_PER_PAGE);  // 최신 리포지토리의 경우 커밋 수가 요청 보다 적을 수 있기 때문
    }

    @Test
    @DisplayName("SpringBoot 리포지토리 커밋 리스트 불러오기 : 페이징 설정")
    void getCommitDetailWithPaging() {
        final int PER_PAGE = 10;
        final int PAGE = 1;
        Flux<CommitDetail> find = gitWebClient.getCommitDetails(member.getGitUsername(), member.getSpringbootRepository(), PER_PAGE, PAGE);
        List<CommitDetail> toList = find.collect(Collectors.toList()).share().block();
        System.out.println(toList.size());
        assertTrue(toList.size() <= PER_PAGE); // 최신 리포지토리의 경우 커밋 수가 요청 보다 적을 수 있기 때문
    }
}