package com.github.commitscrawler.domain.commit;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class Commit {
    private GitUser author;
    private GitUser committer;
    private String message;
}
