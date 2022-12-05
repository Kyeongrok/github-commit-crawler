package com.github.commitscrawler.service;

import com.github.commitscrawler.context.DataReader;
import com.github.commitscrawler.crawler.CommitDetailCrawler;
import com.github.commitscrawler.domain.Member;
import com.github.commitscrawler.domain.entity.StudentCommit;
import com.github.commitscrawler.repository.StudentCommitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GitCommitCrawlService {
    private final CommitDetailCrawler commitDetailCrawler;
    private final DataReader dataReader;
    private final StudentCommitRepository studentCommitRepository;

    @Transactional
    public void crawl() {
        log.info("crawl을 시작 합니다.");
        // crawl한 후
        // List<T> crawlResults
        // .save(crawlResults)//
        List<StudentCommit> studentCommits = new ArrayList<>();
        List<Member> members = dataReader.readMemberRepositories();
        members.stream()
                .parallel()
                .forEach(member -> {
                    for (String key : member.getRepositories().keySet()) {
                        StudentCommit studentCommit = studentCommitRepository.findFirstByNameAndSubject(member.getName(), key)
                                                                                                        .orElse(StudentCommit.builder()
                                                                                                                .name(member.getName())
                                                                                                                .subject(key)
                                                                                                                .build());

                        if (!member.getRepositories().get(key).isEmpty()) {
                            try {
                                StudentCommit response = commitDetailCrawler.crawlStudentCommit(
                                                                                    member.getName(),
                                                                                    key,
                                                                                    member.getRepositories().get(key));
                                studentCommit.update(response);
                            } catch (RuntimeException e) {
                                log.error(e.getMessage());
                            }
                        }
                        studentCommits.add(studentCommit);
                    }
                });
        studentCommitRepository.saveAll(studentCommits);
        log.info("crawl을 완료 했습니다.");
    }

    public List<StudentCommit> findBySubject(String subject) {
        return studentCommitRepository.findBySubjectOrderByName(subject);
    }

    public List<String> getSubjects() {
        return studentCommitRepository.getSubjects();
    }
}
