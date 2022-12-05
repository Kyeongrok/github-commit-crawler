package com.github.commitscrawler.crawler;

import com.github.commitscrawler.config.DefaultCommitDetailCrawlerFactory;
import com.github.commitscrawler.domain.entity.StudentCommit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DefaultCommitDetailCrawlerFactory.class)
class GitApiCrawlerTest {
    @Autowired
    private CommitDetailCrawler gitApiCrawler;
    private final Map<String, String> repositories = new HashMap<>() {{
        put("알고리즘 repository-algorithm(Algorithm)", "LikeLion");
        put("SpringBoot", "LikeLion");
        put("SpringBoot Mustache BBS", "springboot-mustach");
    }};

    @Test
    @DisplayName("SpringBoot 리포지토리 최신 커밋 StudentCommit 불러오기")
    void crawlStudentCommit() {
        String NAME = "황민우";
        String SUBJECT = "알고리즘 repository-algorithm(Algorithm)";
        String REPOSITORY_URL = "https://github.com/menuhwang/LikeLion";
        StudentCommit studentCommit = gitApiCrawler.crawlStudentCommit(NAME, SUBJECT, REPOSITORY_URL);

        assertEquals(NAME, studentCommit.getName());
        assertEquals(SUBJECT, studentCommit.getSubject());
        assertEquals(REPOSITORY_URL + "/commits", studentCommit.getGitRepositoryAddress());
        assertNotNull(studentCommit.getCommitMessage());
        assertNotNull(studentCommit.getLatestCommitDatetime());
    }
}