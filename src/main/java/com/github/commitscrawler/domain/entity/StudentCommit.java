package com.github.commitscrawler.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class StudentCommit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String commitMessage;
    private String gitRepositoryAddress;
    private LocalDateTime latestCommitDatetime;
}
