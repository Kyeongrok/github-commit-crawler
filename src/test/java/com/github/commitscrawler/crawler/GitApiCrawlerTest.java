package com.github.commitscrawler.crawler;

import com.github.commitscrawler.config.DefaultCommitDetailCrawlerFactory;
import com.github.commitscrawler.domain.Member;
import com.github.commitscrawler.domain.commit.CommitDetail;
import com.github.commitscrawler.domain.dto.CommitDetailRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DefaultCommitDetailCrawlerFactory.class)
class GitApiCrawlerTest {
    @Autowired
    private CommitDetailCrawler gitApiCrawler;

    private final Member member = new Member("황민우", "menuhwang", "LikeLion", "LikeLion");

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("SpringBoot 리포지토리 커밋 리스트 불러오기 : 페이징 기본 설정")
    void getCommitDetail() {
        System.out.printf("Git Token이 Null인지 %b%n", System.getenv().get("GIT_TOKEN") == null);
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