package com.github.commitscrawler.crawler;

import com.github.commitscrawler.domain.commit.CommitDetail;
import com.github.commitscrawler.domain.dto.CommitDetailRequest;

import java.util.List;

public interface CommitDetailCrawler {
    List<CommitDetail> crawlCommitDetail(CommitDetailRequest cdr);
}
