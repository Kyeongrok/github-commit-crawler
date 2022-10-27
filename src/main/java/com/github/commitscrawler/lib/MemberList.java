package com.github.commitscrawler.lib;

import com.github.commitscrawler.domain.Member;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemberList {
    private static final List<Member> members = new ArrayList<>(Arrays.asList(
            new Member("황민우", "menuhwang", "LikeLion", "LikeLion")
    ));

    public static List<Member> getMembers() {
        return members;
    }

    public static Member findByName(String name) {
        for (Member member : members) {
            if (name.equals(member.getName())) return member;
        }
        throw new RuntimeException("해당 학생을 찾지 못했습니다.");
    }
}
