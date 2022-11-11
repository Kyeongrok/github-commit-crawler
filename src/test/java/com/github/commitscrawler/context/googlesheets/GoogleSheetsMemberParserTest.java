package com.github.commitscrawler.context.googlesheets;

import com.github.commitscrawler.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GoogleSheetsMemberParserTest {
    private final GoogleSheetsMemberParser parser = new GoogleSheetsMemberParser();

    @Test
    @DisplayName("정상")
    void parse() {
        final String NAME = "홍길동";
        final String GIT_USERNAME = "gildong";
        final String ALGORITHM_REPO = "algorithm";
        final String SPRINGBOOT_REPO = "springboot";
        final Map<String, String> repositories = new HashMap<>(){{
            put("algorithm", "https://github.com/" + GIT_USERNAME + "/" + ALGORITHM_REPO);
            put("springboot", "https://github.com/" + GIT_USERNAME + "/" + SPRINGBOOT_REPO);
        }};
        List<Object> row = new ArrayList<>(Arrays.asList(NAME, repositories));
        Member member = parser.parse(row);
        assertEquals(NAME, member.getName());
        assertEquals(GIT_USERNAME, member.getGitUsername());
        assertEquals(ALGORITHM_REPO, member.getRepositories().get(ALGORITHM_REPO));
        assertEquals(SPRINGBOOT_REPO, member.getRepositories().get(SPRINGBOOT_REPO));
    }

    @Test
    @DisplayName("알고리즘 주소가 없는 경우")
    void parseWhenAlgorithmRepoNull() {
        final String NAME = "홍길동";
        final String GIT_USERNAME = "gildong";
        final String SPRINGBOOT_REPO = "springboot";
        final Map<String, String> repositories = new HashMap<>(){{
            put("springboot", "https://github.com/" + GIT_USERNAME + "/" + SPRINGBOOT_REPO);
        }};
        List<Object> row = new ArrayList<>(Arrays.asList(NAME, repositories));
        Member member = parser.parse(row);
        assertEquals(NAME, member.getName());
        assertEquals(GIT_USERNAME, member.getGitUsername());
        assertEquals(1, member.getRepositories().size());
        assertEquals(SPRINGBOOT_REPO, member.getRepositories().get(SPRINGBOOT_REPO));
    }

    @Test
    @DisplayName("모든 리포지토리 주소가 없는 경우")
    void parseWhenNothingRepo() {
        final String NAME = "홍길동";
        List<Object> row = new ArrayList<>(Arrays.asList(NAME, new HashMap<String, String>()));
        Member member = parser.parse(row);
        assertEquals(NAME, member.getName());
        assertNull(member.getGitUsername());
        assertEquals(0, member.getRepositories().size());
    }
}