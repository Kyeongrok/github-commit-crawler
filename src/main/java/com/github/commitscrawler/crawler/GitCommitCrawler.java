package com.github.commitscrawler.crawler;

import com.github.commitscrawler.domain.Member;
import com.github.commitscrawler.domain.Repository;
import com.github.commitscrawler.domain.commit.Commit;
import com.github.commitscrawler.domain.commit.CommitPayload;
import com.github.commitscrawler.lib.MemberList;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GitCommitCrawler {
    private final GitWebClient gitWebClient;

    public GitCommitCrawler(GitWebClient gitWebClient) {
        this.gitWebClient = gitWebClient;
    }

    public List<CommitPayload> getCommitsAllMember() {
        List<CommitPayload> commitPayloads = new ArrayList<>();
        for (Member member : MemberList.getMembers()) {
            CommitPayload commitPayload = new CommitPayload();

            commitPayload.setMemberName(member.getName());

            Repository repository = gitWebClient.getLatestRepository(member.getGitUsername());
            Commit commit = gitWebClient.getRepositoryCommitDetails(repository, 10, 1).get(0).getCommit();

            commitPayload.setMessage(commit.getMessageTitle());
            commitPayload.setPush_at(commit.getCommitter().getDate());

            commitPayloads.add(commitPayload);
        }
        return commitPayloads;
    }
}
