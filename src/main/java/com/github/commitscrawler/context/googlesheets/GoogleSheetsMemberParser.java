package com.github.commitscrawler.context.googlesheets;

import com.github.commitscrawler.context.Parser;
import com.github.commitscrawler.domain.Member;

import java.util.List;
import java.util.Map;

public class GoogleSheetsMemberParser implements Parser<Member, List<Object>> {
    private static final String GITHUB_URL = "https://github.com/";
    @Override
    public Member parse(List<Object> row) {
        Object nameCol = row.get(0);
        Map<String, String> repositories = (Map<String, String>) row.get(1);

        String name = nameCol.toString();
        String gitUsername = null;

        for (String key : repositories.keySet()) {
            if (gitUsername == null) gitUsername = getGitUsername(repositories.get(key));
            repositories.put(key, getRepositoryName(repositories.get(key)));
        }

        return new Member(name, gitUsername, repositories);
    }

    private String getGitUsername(String repository) {
        if (!repository.contains(GITHUB_URL)) return null;
        String gitUsername = repository.substring(GITHUB_URL.length());
        gitUsername = gitUsername.substring(0, gitUsername.indexOf("/"));
        return gitUsername;
    }

    private String getRepositoryName(String repository) {
        if (!repository.contains(GITHUB_URL)) return null;
        repository = repository.substring(GITHUB_URL.length());
        return repository.substring(repository.indexOf("/") + 1);
    }
}
