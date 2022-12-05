package com.github.commitscrawler.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString @Getter
public class StudentCommit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String subject;
    private String commitMessage;
    private String gitRepositoryAddress;
    private LocalDateTime latestCommitDatetime;

    public void update(StudentCommit response) {
        updateCommitMessage(response.getCommitMessage());
        updateGitRepositoryAddress(response.getGitRepositoryAddress());
        updateLatestCommitDatetime(response.getLatestCommitDatetime());
    }

    private void updateCommitMessage(String commitMessage) {
        if (commitMessage != null) {
            this.commitMessage = commitMessage;
        }
    }

    private void updateGitRepositoryAddress(String gitRepositoryAddress) {
        if (gitRepositoryAddress != null) {
            this.gitRepositoryAddress = gitRepositoryAddress;
        }
    }

    private void updateLatestCommitDatetime(LocalDateTime latestCommitDatetime) {
        if (latestCommitDatetime != null) {
            this.latestCommitDatetime = latestCommitDatetime;
        }
    }
}
