package com.github.commitscrawler.controller;

import com.github.commitscrawler.crawler.GitCommitCrawler;
import com.github.commitscrawler.domain.commit.CommitPayload;
import com.github.commitscrawler.lib.enumeration.Subject;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/progress")
public class StudentProgressController {
    private final GitCommitCrawler gitCommitCrawler;

    public StudentProgressController(GitCommitCrawler gitCommitCrawler) {
        this.gitCommitCrawler = gitCommitCrawler;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CommitPayload>> getList(@ApiParam(value = "과목 : algorithm / springboot") @RequestParam("subject") String subjectName) {
        // todo crawl한 List를 리턴함
        Subject subject = Subject.valueOf(subjectName.toUpperCase());
        return ResponseEntity.ok().body(gitCommitCrawler.getLatestCommitsAllMember(subject));
    }

}
