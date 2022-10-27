package com.github.commitscrawler.domain;

public class Member {
    private final String name;
    private final String gitUsername;

    public Member(String name, String gitUsername) {
        this.name = name;
        this.gitUsername = gitUsername;
    }

    public String getName() {
        return name;
    }

    public String getGitUsername() {
        return gitUsername;
    }
}
