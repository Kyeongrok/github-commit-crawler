package com.github.commitscrawler.crawler;

import com.github.commitscrawler.context.DataReader;
import com.github.commitscrawler.domain.Member;
import com.github.commitscrawler.domain.commit.Commit;
import com.github.commitscrawler.domain.commit.CommitDetail;
import com.github.commitscrawler.domain.commit.CommitPayload;
import com.github.commitscrawler.domain.dto.CommitDetailRequest;

import java.util.List;
import java.util.stream.Collectors;

public class GitCommitCrawler {
    private final CommitDetailCrawler commitDetailCrawler;
    private final DataReader dataReader;

    public GitCommitCrawler(CommitDetailCrawler commitDetailCrawler, DataReader dataReader) {
        this.commitDetailCrawler = commitDetailCrawler;
        this.dataReader = dataReader;
    }

    public List<CommitPayload> getLatestCommitsAllMember(String column) {
        List<Member> members = dataReader.readMembers();
        List<CommitPayload> payloads = members.stream()
                .parallel()
                .map(member -> {
                String name = member.getName();
                String gitUsername = member.getGitUsername();
                // Column에 따라 repository 주소 선택
                String repo = member.getRepositories().getOrDefault(column, null);

                // 멤버정보로 dto 생성.
                CommitPayload payload = new CommitPayload(name);
                if (repo == null) return payload;
                CommitDetailRequest cdr = new CommitDetailRequest(gitUsername, repo);
                try { // getLatestCommit() > commitDetailCrawler.crawlCommitDetail()에서의 예외 처리.
                    payload.update(getLatestCommit(cdr));
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
                return payload;
            }).collect(Collectors.toList());
        return payloads;
    }

    public CommitPayload getLatestCommit(CommitDetailRequest cdr) {
        List<CommitDetail> commitDetails = commitDetailCrawler.crawlCommitDetail(cdr); // 한 리포지토리의 커밋 여러개 불러오기
        CommitPayload payload = null;
        if (commitDetails.size() > 0) {
            Commit commit = commitDetails.get(0).getCommit();
            payload = new CommitPayload();
            payload.setMessage(commit.getMessageTitle());
            payload.setPush_at(commit.getCommitter().getDate());
            payload.setUrl(commitDetails.get(0).getCommitsUrl());
        }
        return payload;
    }
}
