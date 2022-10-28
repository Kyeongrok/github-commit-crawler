package com.github.commitscrawler.crawler;

import com.github.commitscrawler.domain.commit.CommitDetail;
import com.github.commitscrawler.domain.dto.CommitDetailRequest;

import java.util.List;

public class GitHtmlParser implements CommitDetailCrawler {
    @Override
    public List<CommitDetail> crawlCommitDetail(CommitDetailRequest cdr) {
        // cdr의 정보를 받아서 http request를 전송해 List<CommitDetail>을 리턴
        return null;
    }
}
