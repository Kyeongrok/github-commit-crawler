package com.github.commitscrawler.crawler;

import com.github.commitscrawler.domain.Member;
import com.github.commitscrawler.domain.commit.CommitDetail;
import com.github.commitscrawler.domain.dto.CommitDetailRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GitApiCrawlerTest {
    @Autowired
    private ApplicationContext context;
    private GitApiCrawler gitApiCrawler;

    private final Member member = new Member("황민우", "menuhwang", "LikeLion", "LikeLion");

    @BeforeEach
    void setUp() {
        gitApiCrawler = (GitApiCrawler) context.getBean("gitApiCrawler");
    }

    @Test
    @DisplayName("SpringBoot 리포지토리 커밋 리스트 불러오기 : 페이징 기본 설정")
    void getCommitDetail() {
        CommitDetailRequest cdr = new CommitDetailRequest(member.getGitUsername(), member.getSpringbootRepository());
        List<CommitDetail> commitDetails = gitApiCrawler.crawlCommitDetail(cdr);
        System.out.println(commitDetails);
        System.out.println(commitDetails.size());
        assertTrue(commitDetails.size() <= CommitDetailRequest.DEFAULT_PER_PAGE);  // 최신 리포지토리의 경우 커밋 수가 요청 보다 적을 수 있기 때문
    }

    @Test
    @DisplayName("SpringBoot 리포지토리 커밋 리스트 불러오기 : 페이징 설정")
    void getCommitDetailWithPaging() {
        final int PER_PAGE = 10;
        final int PAGE = 1;
        CommitDetailRequest cdr = new CommitDetailRequest(member.getGitUsername(), member.getSpringbootRepository(), PER_PAGE, PAGE);
        List<CommitDetail> commitDetails = gitApiCrawler.crawlCommitDetail(cdr);
        System.out.println(commitDetails);
        System.out.println(commitDetails.size());
        assertTrue(commitDetails.size() <= PER_PAGE); // 최신 리포지토리의 경우 커밋 수가 요청 보다 적을 수 있기 때문
    }
}