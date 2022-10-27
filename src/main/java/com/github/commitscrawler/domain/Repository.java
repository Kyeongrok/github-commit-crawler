package com.github.commitscrawler.domain;

import com.github.commitscrawler.crawler.GitCommitCrawler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter @Setter
public class Repository {
    private String name;
    private String full_name;
    private String url;
    private String commits_url;
    private String pushed_at;

    public String getCommitsUrlOnlyPath() {
        return this.commits_url.substring(GitCommitCrawler.BASE_URL.length(), this.commits_url.length() - 6);
    }
}
