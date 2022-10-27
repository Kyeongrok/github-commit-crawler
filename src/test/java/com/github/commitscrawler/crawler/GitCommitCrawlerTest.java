package com.github.commitscrawler.crawler;

import com.github.commitscrawler.domain.Repository;
import com.github.commitscrawler.domain.commit.CommitDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GitCommitCrawlerTest {
    @Autowired
    private GitCommitCrawler gitCommitCrawler;

    @Test
    @DisplayName("푸시기준 최신 리포지토리 불러오기")
    void getLatestRepository() {
        Repository latest = gitCommitCrawler.getLatestRepository("menuhwang");
        System.out.println(latest);
        assertNotNull(latest.getName());
        assertNotNull(latest.getFull_name());
        assertNotNull(latest.getUrl());
        assertNotNull(latest.getCommits_url());
        assertNotNull(latest.getPushed_at());
    }

    @Test
    @DisplayName("최신 리포지토리 불러오기 : 해당 유저가 없는 경우")
    void getLatestRepositoryNotFoundUser() {
        assertThrows(RuntimeException.class, () -> gitCommitCrawler.getLatestRepository("menuhwangddddd"));
    }

    @Test
    @DisplayName("리포지토리 커밋 리스트 불러오기 : 페이징 기본 설정")
    void getRepositoryCommitDetail() {
        Repository repository = gitCommitCrawler.getLatestRepository("menuhwang");
        List<CommitDetail> find = gitCommitCrawler.getRepositoryCommitDetails(repository);
        System.out.println(find.size());
        assertEquals(30, find.size());
    }

    @Test
    @DisplayName("리포지토리 커밋 리스트 불러오기 : 페이징 설정")
    void getRepositoryCommitDetailWithPaging() {
        final int PER_PAGE = 10;
        final int PAGE = 1;
        Repository repository = gitCommitCrawler.getLatestRepository("menuhwang");
        List<CommitDetail> find = gitCommitCrawler.getRepositoryCommitDetails(repository, PER_PAGE, PAGE);
        System.out.println(find.size());
        System.out.println(find);
        assertTrue(find.size() <= PER_PAGE); // 최신 리포지토리의 경우 커밋 수가 요청 보다 적을 수 있기 때문
    }
}