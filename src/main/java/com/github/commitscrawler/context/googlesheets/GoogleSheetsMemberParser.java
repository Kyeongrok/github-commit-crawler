package com.github.commitscrawler.context.googlesheets;

import com.github.commitscrawler.context.Parser;
import com.github.commitscrawler.domain.Member;

import java.util.List;

public class GoogleSheetsMemberParser implements Parser<Member, List<Object>> {
    private static final String GITHUB_URL = "https://github.com/";
    @Override
    public Member parse(List<Object> row) {
        Object nameCol = row.get(0);
        Object algorithmRepoCol = (row.size() > 1) ? row.get(1) : null;
        Object springbootRepoCol = (row.size() > 2) ? row.get(2) : null;

        String name = nameCol.toString();
        String algorithmRepo;
        String springbootRepo;
        String gitUsername = null;

        if (algorithmRepoCol != null) { // algorithmRepo 주소가 있을 경우
            gitUsername = getGitUsername(algorithmRepoCol); // algorithmRepo에서 gitUsername 파싱
        } else if (springbootRepoCol != null) { // springbootRepo 주소가 있을 경우
            gitUsername = getGitUsername(springbootRepoCol); // springbootRepo에서 gitUsername 파싱
        }

        algorithmRepo = getRepositoryName(algorithmRepoCol);
        springbootRepo = getRepositoryName(springbootRepoCol);

        return new Member(name, gitUsername, algorithmRepo, springbootRepo);
    }

    private String getGitUsername(Object repositoryColumn) {
        if (repositoryColumn == null) return null;
        String repository = repositoryColumn.toString();
        if (!repository.contains(GITHUB_URL)) return null;
        String gitUsername = repository.substring(GITHUB_URL.length());
        gitUsername = gitUsername.substring(0, gitUsername.indexOf("/"));
        return gitUsername;
    }

    private String getRepositoryName(Object repositoryColumn) {
        if (repositoryColumn == null) return null;
        String repository = repositoryColumn.toString();
        repository = repository.substring(GITHUB_URL.length());
        String repositoryName = repository.substring(repository.indexOf("/") + 1);
        return repositoryName;
    }
}
