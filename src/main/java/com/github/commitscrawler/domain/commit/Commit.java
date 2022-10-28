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

    public String getMessageTitle() {
        int newLine = message.indexOf('\n');
        if (newLine > -1) return message.substring(0, newLine);
        return message;
    }
}
