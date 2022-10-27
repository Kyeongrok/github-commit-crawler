package com.github.commitscrawler.lib;

import com.github.commitscrawler.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberListTest {
    @Test
    @DisplayName("학생 이름으로 검색")
    void findByName() {
        Member minwoo = new Member("황민우", "menuhwang");
        Member find = MemberList.findByName(minwoo.getName());
        assertEquals(minwoo.getName(), find.getName());
        assertEquals(minwoo.getGitUsername(), find.getGitUsername());
    }
    @Test
    @DisplayName("해당 학생이 없을 때 : RuntimeException")
    void findByNameNotFoundMember() {
        assertThrows(RuntimeException.class, () -> MemberList.findByName("홍길동"));
    }
}