package com.github.commitscrawler.crawler;

import com.github.commitscrawler.domain.Member;
import com.github.commitscrawler.domain.commit.Commit;
import com.github.commitscrawler.domain.commit.CommitDetail;
import com.github.commitscrawler.domain.commit.CommitPayload;
import com.github.commitscrawler.lib.MemberList;
import com.github.commitscrawler.lib.enumeration.Subject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GitCommitCrawler {
    private final GitWebClient gitWebClient;

    public GitCommitCrawler(GitWebClient gitWebClient) {
        this.gitWebClient = gitWebClient;
    }

    public List<CommitPayload> getLatestCommitAllMember(Subject subject) {
        List<CommitPayload> commitPayloads = new ArrayList<>();
        for (Member member : MemberList.getMembers()) {
            CommitPayload commitPayload = new CommitPayload();

            commitPayload.setMemberName(member.getName());

            String owner = member.getGitUsername();
            String repo = (subject == Subject.ALGORITHM) ? member.getAlgorithmRepository() : member.getSpringbootRepository();

            if (repo == null) {
                System.out.printf("%s 학생의 %s 리포지토리 설정이 필요합니다.\n", member.getName(), subject.name());
                commitPayload.setMessage(null);
                commitPayload.setPush_at(null);
                continue;
            }

            CommitDetail commitDetail = gitWebClient.getLatestCommitDetail(owner, repo);
            Commit commit = commitDetail.getCommit();

            commitPayload.setMessage((commit != null) ? commit.getMessageTitle() : null);
            commitPayload.setPush_at((commit != null) ? commit.getCommitter().getDate() : null);

            commitPayloads.add(commitPayload);
        }
        return commitPayloads;
    }
}
