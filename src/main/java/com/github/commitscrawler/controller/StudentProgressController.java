package com.github.commitscrawler.controller;

import com.github.commitscrawler.domain.commit.CommitDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/progress")
public class StudentProgressController {

    @GetMapping("/list")
    public ResponseEntity<List<CommitDetail>> getList() {
        // todo crawl한 List를 리턴함
        return ResponseEntity.ok().body(new ArrayList<>());
    }

}
