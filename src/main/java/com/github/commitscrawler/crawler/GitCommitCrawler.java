package com.github.commitscrawler.crawler;

import com.github.commitscrawler.context.MemberReader;
import com.github.commitscrawler.domain.Member;
import com.github.commitscrawler.domain.commit.Commit;
import com.github.commitscrawler.domain.commit.CommitDetail;
import com.github.commitscrawler.domain.commit.CommitPayload;
import com.github.commitscrawler.domain.dto.CommitDetailRequest;
import com.github.commitscrawler.lib.enumeration.Subject;

import java.util.ArrayList;
import java.util.List;

public class GitCommitCrawler {
    private final CommitDetailCrawler commitDetailCrawler;
    private final MemberReader memberReader;

    public GitCommitCrawler(CommitDetailCrawler commitDetailCrawler, MemberReader memberReader) {
        this.commitDetailCrawler = commitDetailCrawler;
        this.memberReader = memberReader;
    }

    public List<CommitPayload> getLatestCommitsAllMember(Subject subject) {
        List<CommitPayload> payloads = new ArrayList<>(); // 멤버들의 최신 커밋 하나씩만 담는 리스트 선언
        for (Member member : memberReader.read()) {
            if (!member.isValid()) { // 유저 정보가 잘못된 경우 패스
                System.out.printf("유효하지 않은 유저 정보입니다.\n%s\n", member);
                continue;
            }
            String name = member.getName();
            String gitUsername = member.getGitUsername();
            // Subject에 따라 repository 주소 선택
            String repo = (subject == Subject.ALGORITHM) ? member.getAlgorithmRepository() : member.getSpringbootRepository();

            // 멤버정보로 dto 생성.
            CommitDetailRequest cdr = new CommitDetailRequest(gitUsername, repo);

            try { // getLatestCommit() > commitDetailCrawler.crawlCommitDetail()에서의 예외 처리.
                CommitPayload payload = getLatestCommit(cdr);
                payload.setMemberName(name);
                payloads.add(payload);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
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
