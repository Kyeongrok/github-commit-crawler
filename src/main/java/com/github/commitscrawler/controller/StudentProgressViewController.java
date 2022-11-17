package com.github.commitscrawler.controller;

import com.github.commitscrawler.crawler.GitCommitCrawler;
import com.github.commitscrawler.domain.commit.CommitPayload;
import com.github.commitscrawler.lib.enumeration.Subject;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/v1/progress/view")
public class StudentProgressViewController {

    private final GitCommitCrawler gitCommitCrawler;

    public StudentProgressViewController(GitCommitCrawler gitCommitCrawler) {
        this.gitCommitCrawler = gitCommitCrawler;
    }

    // ex) http://localhost:8080/api/v1/progress/view?columnIdx=B
//    @GetMapping("")
//    public String getList(@ApiParam(value = "컬럼Idx: B / C / D / E ...") @RequestParam("columnIdx") String columnIdx, Model model) {
//        // todo 이전 로직과 호환성 유지를 위한 부분
//        String subjectName = "algorithm";
//        switch (columnIdx.toUpperCase()) {
//            case "B": subjectName = "algoritym"; break;
//            case "C": subjectName = "springboot"; break;
//            case "D": subjectName = "springmvc"; break;
//        }
//        Subject subject = Subject.valueOf(subjectName.toUpperCase());
//        List<CommitPayload> commitPayloads = gitCommitCrawler.getLatestCommitsAllMember(subject);
//        model.addAttribute("commitPayloads", commitPayloads);
//
//        return "list";
//    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}