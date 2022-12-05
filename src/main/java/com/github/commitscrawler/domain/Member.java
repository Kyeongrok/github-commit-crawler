package com.github.commitscrawler.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@ToString
@Getter
public class Member {
    private final String name;
    private final Map<String, String> repositories;

    public Member(String name, Map<String, String> repositories) {
        this.name = name;
        this.repositories = repositories;
    }
}
