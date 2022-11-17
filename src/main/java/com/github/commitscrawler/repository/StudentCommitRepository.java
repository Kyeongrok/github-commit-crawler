package com.github.commitscrawler.repository;

import com.github.commitscrawler.domain.entity.StudentCommit;
import org.springframework.data.jpa.repository.JpaRepository;

interface StudentCommitRepository extends JpaRepository<StudentCommit, Integer> {
}
