package com.github.commitscrawler.context.googlesheets;

import com.github.commitscrawler.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<Object> row = new ArrayList<>(Arrays.asList(NAME, "https://github.com/" + GIT_USERNAME + "/" + ALGORITHM_REPO, "https://github.com/" + GIT_USERNAME + "/" + SPRINGBOOT_REPO));
        Member member = parser.parse(row);
        assertEquals(NAME, member.getName());
        assertEquals(GIT_USERNAME, member.getGitUsername());
        assertEquals(ALGORITHM_REPO, member.getAlgorithmRepository());
        assertEquals(SPRINGBOOT_REPO, member.getSpringbootRepository());
    }

    @Test
    @DisplayName("알고리즘 주소가 없는 경우")
    void parseWhenAlgorithmRepoNull() {
        final String NAME = "홍길동";
        final String GIT_USERNAME = "gildong";
        final String SPRINGBOOT_REPO = "springboot";
        List<Object> row = new ArrayList<>(Arrays.asList(NAME, null, "https://github.com/" + GIT_USERNAME + "/" + SPRINGBOOT_REPO));
        Member member = parser.parse(row);
        assertEquals(NAME, member.getName());
        assertEquals(GIT_USERNAME, member.getGitUsername());
        assertNull(member.getAlgorithmRepository());
        assertEquals(SPRINGBOOT_REPO, member.getSpringbootRepository());
    }

    @Test
    @DisplayName("스프링부트 주소가 없는 경우")
    void parseWhenSpringbootRepoNull() {
        final String NAME = "홍길동";
        final String GIT_USERNAME = "gildong";
        final String ALGORITHM_REPO = "algorithm";
        List<Object> row = new ArrayList<>(Arrays.asList(NAME, "https://github.com/" + GIT_USERNAME + "/" + ALGORITHM_REPO));
        Member member = parser.parse(row);
        assertEquals(NAME, member.getName());
        assertEquals(GIT_USERNAME, member.getGitUsername());
        assertEquals(ALGORITHM_REPO, member.getAlgorithmRepository());
        assertNull(member.getSpringbootRepository());
    }

    @Test
    @DisplayName("모든 리포지토리 주소가 없는 경우")
    void parseWhenNothingRepo() {
        final String NAME = "홍길동";
        List<Object> row = new ArrayList<>(Arrays.asList(NAME));
        Member member = parser.parse(row);
        assertEquals(NAME, member.getName());
        assertNull(member.getGitUsername());
        assertNull(member.getAlgorithmRepository());
        assertNull(member.getSpringbootRepository());
    }
}