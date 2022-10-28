package com.github.commitscrawler.crawler;

import com.github.commitscrawler.domain.commit.CommitDetail;
import com.github.commitscrawler.domain.dto.CommitDetailRequest;

public interface CommitDetailStrategy {
    CommitDetail crawlCommitDetail(CommitDetailRequest cdr);
}
