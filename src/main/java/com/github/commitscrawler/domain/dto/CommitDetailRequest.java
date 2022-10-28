package com.github.commitscrawler.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Dto
@AllArgsConstructor
@Getter
public class CommitDetailRequest {
    public static final int DEFAULT_PER_PAGE = 5;
    public static final int DEFAULT_PAGE = 1;
    private String owner;
    private String repo;

    // optional로 from to page
    private int perPage; // limit
    private int page; // from no

    public CommitDetailRequest(String owner, String repo) {
        this.owner = owner;
        this.repo = repo;
        this.page = DEFAULT_PAGE;
        this.perPage = DEFAULT_PER_PAGE;
    }
}
