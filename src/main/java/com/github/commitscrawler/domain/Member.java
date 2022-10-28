package com.github.commitscrawler.domain;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Member {
    private final String name;
    private final String gitUsername;
    private final String algorithmRepository;
    private final String springbootRepository;

    public Member(String name, String gitUsername, String algorithmRepository, String springbootRepository) {
        this.name = name;
        this.gitUsername = gitUsername;
        this.algorithmRepository = algorithmRepository;
        this.springbootRepository = springbootRepository;
    }

    public boolean isValid() {
        return name != null &&
                gitUsername != null &&
                algorithmRepository != null &&
                springbootRepository != null;
    }
}
