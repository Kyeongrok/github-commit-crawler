package com.github.commitscrawler.controller;

import com.github.commitscrawler.domain.entity.StudentCommit;
import com.github.commitscrawler.service.GitCommitCrawlService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/progress")
@RequiredArgsConstructor
public class StudentProgressController {
    private final GitCommitCrawlService gitCommitCrawlService;

    @GetMapping("/list")
    public List<StudentCommit> getList(@ApiParam(value = "명렬표 컬럼명 입력") @RequestParam("column") String column) {
        return gitCommitCrawlService.findBySubject(column);
    }

    @GetMapping("/columns")
    public List<String> getColumns() {
        return gitCommitCrawlService.getSubjects();
    }

    @GetMapping("/forced-crawl")
    public ResponseEntity<Void> forcedCrawl() {
        gitCommitCrawlService.crawl();
        return ResponseEntity.noContent().build();
    }
}
