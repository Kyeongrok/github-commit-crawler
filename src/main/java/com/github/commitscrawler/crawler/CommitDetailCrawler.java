package com.github.commitscrawler.crawler;

import com.github.commitscrawler.domain.entity.StudentCommit;

public interface  CommitDetailCrawler {
    StudentCommit crawlStudentCommit(String name, String subject, String repositoryURL);
}
