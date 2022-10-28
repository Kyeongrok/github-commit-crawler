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
}
