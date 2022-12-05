package com.github.commitscrawler.service;

import com.github.commitscrawler.domain.entity.StudentCommit;
import com.github.commitscrawler.repository.StudentCommitRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GitCommitCrawlServiceTest {
    @Autowired
    private GitCommitCrawlService gitCommitCrawlService;
    @Autowired
    private StudentCommitRepository studentCommitRepository;
    private final String ALGORITHM = "알고리즘 repository-algorithm(Algorithm)";

    @Test
    @Disabled
    void crawl() {
        gitCommitCrawlService.crawl();
        List<StudentCommit> result = studentCommitRepository.findAll();
        System.out.println(result);
    }

    @Test
    @Disabled
    void findBySubject() {
        gitCommitCrawlService.crawl();
        List<StudentCommit> result = gitCommitCrawlService.findBySubject(ALGORITHM);
        System.out.println(result);
    }

    @Test
    void getSubjects() {
        gitCommitCrawlService.crawl();
        List<String> subjects = gitCommitCrawlService.getSubjects();
        System.out.println(subjects);
    }
}