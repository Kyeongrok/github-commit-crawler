package com.github.commitscrawler.domain.commit;

import com.github.commitscrawler.domain.entity.StudentCommit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZoneId;

@ToString
@Getter @Setter
public class CommitDetail {
    private String url;
    private String sha;
    private String html_url;
    private Commit commit;

    public StudentCommit toEntity(String name, String subject) {
        String commitMessage = this.getCommit().getMessageTitle();
        LocalDateTime latestCommitDatetime = this.getCommit().getCommitter().getDate().toInstant() // Date -> Instant
                                                                                    .atZone(ZoneId.systemDefault()) // Instant -> ZonedDateTime
                                                                                    .toLocalDateTime(); // ZonedDateTime -> LocalDateTime
        return StudentCommit.builder()
                .name(name)
                .subject(subject)
                .commitMessage(commitMessage)
                .gitRepositoryAddress(getCommitsUrl())
                .latestCommitDatetime(latestCommitDatetime)
                .build();
    }

    public String getCommitsUrl() {
        return html_url.substring(0, html_url.lastIndexOf("/")) + "s";
    }
}
