package com.github.commitscrawler.repository;

import com.github.commitscrawler.domain.entity.StudentCommit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentCommitRepository extends JpaRepository<StudentCommit, Integer> {
    List<StudentCommit> findBySubjectOrderByName(String subject);
    Optional<StudentCommit> findFirstByNameAndSubject(String name, String subject);
    @Query("SELECT DISTINCT (s.subject) FROM StudentCommit s")
    List<String> getSubjects();
}
