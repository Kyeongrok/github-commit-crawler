package com.github.commitscrawler.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GitCommitCrawlService {

    public void crawl() {
        log.info("crawl을 시작 합니다.");
        // crawl한 후
        // List<T> crawlResults
        // .save(crawlResults)//
        log.info("crawl을 완료 했습니다.");
    }
}
