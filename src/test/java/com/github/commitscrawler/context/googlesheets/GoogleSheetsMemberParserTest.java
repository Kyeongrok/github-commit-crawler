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
        final String ALGORITHM_REPO_URL = "https://github.com/" + GIT_USERNAME + "/" + ALGORITHM_REPO;
        final String SPRINGBOOT_REPO = "springboot";
        final String SPRINGBOOT_REPO_URL = "https://github.com/" + GIT_USERNAME + "/" + SPRINGBOOT_REPO;
        final Map<String, String> repositories = new HashMap<>(){{
            put("algorithm", ALGORITHM_REPO_URL);
            put("springboot", SPRINGBOOT_REPO_URL);
        }};
        List<Object> row = new ArrayList<>(Arrays.asList(NAME, repositories));
        Member member = parser.parse(row);
        assertEquals(NAME, member.getName());
        assertEquals(ALGORITHM_REPO_URL, member.getRepositories().get(ALGORITHM_REPO));
        assertEquals(SPRINGBOOT_REPO_URL, member.getRepositories().get(SPRINGBOOT_REPO));
    }
}