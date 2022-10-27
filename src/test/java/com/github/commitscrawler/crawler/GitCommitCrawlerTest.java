package com.github.commitscrawler.crawler;

import com.github.commitscrawler.domain.commit.CommitPayload;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestConfiguration("classpath:application-local.yml")
@SpringBootTest
class GitCommitCrawlerTest {

    @Autowired
    private GitCommitCrawler gitCommitCrawler;

    @Test
    @DisplayName("가장 최근 커밋 조회")
    void getCommitsAllMember() {
        List<CommitPayload> commitPayloads = gitCommitCrawler.getCommitsAllMember();
        System.out.println(commitPayloads);
    }
}