package com.github.commitscrawler.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Dto
@AllArgsConstructor
@Getter
public class CommitDetailRequest {
    private String owner;
    private String repo;

    // optional로 from to page
    private int perPage; // limit
    private int page; // from no

    public CommitDetailRequest(String owner, String repo) {
        this.owner = owner;
        this.repo = repo;
        this.page = 1;
        this.perPage = 5;
    }
}
