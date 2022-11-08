package com.github.commitscrawler.domain.commit;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class CommitDetail {
    private String url;
    private String sha;
    private String html_url;
    private Commit commit;

    public String getCommitsUrl() {
        return html_url.substring(0, html_url.lastIndexOf("/")) + "s";
    }
}
