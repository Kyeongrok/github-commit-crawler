package com.github.commitscrawler.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    private final Member member1 = new Member("홍길동", "kildong", "altorepo", null);
    private final Member member2 = new Member("홍길동", "kildong", null, null);
    private final Member member3 = new Member("홍길동", "kildong", "altorepo", "springrepo");

    @Test
    void isValid() {
        assertFalse(member1.isValid());
        assertFalse(member2.isValid());
        assertTrue(member3.isValid());
    }
}