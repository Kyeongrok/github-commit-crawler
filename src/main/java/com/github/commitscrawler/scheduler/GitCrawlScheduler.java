package com.github.commitscrawler.scheduler;


import com.github.commitscrawler.service.GitCommitCrawlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GitCrawlScheduler {

    private final GitCommitCrawlService gitCommitCrawlService;

    public GitCrawlScheduler(GitCommitCrawlService gitCommitCrawlService) {
        this.gitCommitCrawlService = gitCommitCrawlService;
    }

    @Scheduled(cron = "0 */10 9-16 * * MON-FRI") // 9-16시 사이 매 10분 마다 월~금
    public void run() {
        // crawl을 여기에서 수행함
        gitCommitCrawlService.crawl();
    }
}
