package com.github.commitscrawler.crawler;

import com.github.commitscrawler.context.MemberReader;
import com.github.commitscrawler.domain.Member;
import com.github.commitscrawler.domain.commit.Commit;
import com.github.commitscrawler.domain.commit.CommitDetail;
import com.github.commitscrawler.domain.commit.CommitPayload;
import com.github.commitscrawler.domain.dto.CommitDetailRequest;
import com.github.commitscrawler.lib.enumeration.Subject;

import java.util.List;
import java.util.stream.Collectors;

public class GitCommitCrawler {
    private final CommitDetailCrawler commitDetailCrawler;
    private final MemberReader memberReader;

    public GitCommitCrawler(CommitDetailCrawler commitDetailCrawler, MemberReader memberReader) {
        this.commitDetailCrawler = commitDetailCrawler;
        this.memberReader = memberReader;
    }

    public List<CommitPayload> getLatestCommitsAllMember(Subject subject) {
        List<Member> members = memberReader.read();
        List<CommitPayload> payloads = members.stream()
                .parallel()
                .filter(member -> {
                    if (!member.isValid()) {
                        System.out.printf("유효하지 않은 유저 정보입니다.\n%s\n", member);
                        return false;
                    }
                    return true;
                }).map(member -> {
                String name = member.getName();
                String gitUsername = member.getGitUsername();
                // Subject에 따라 repository 주소 선택
                String repo = (subject == Subject.ALGORITHM) ? member.getAlgorithmRepository() : member.getSpringbootRepository();


                // 멤버정보로 dto 생성.
                CommitDetailRequest cdr = new CommitDetailRequest(gitUsername, repo);
                CommitPayload payload = new CommitPayload(name);

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
        }
        return payload;
    }
}
