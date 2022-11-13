package com.github.commitscrawler.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@ToString
@Getter
public class Member {
    private final String name;
    private final String gitUsername;
    private final Map<String, String> repositories;

    public Member(String name, String gitUsername, Map<String, String> repositories) {
        this.name = name;
        this.gitUsername = gitUsername;
        this.repositories = repositories;
    }
}
