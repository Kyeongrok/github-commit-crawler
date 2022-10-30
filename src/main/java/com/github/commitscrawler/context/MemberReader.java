package com.github.commitscrawler.context;

import com.github.commitscrawler.domain.Member;

import java.util.List;

public interface MemberReader {
    List<Member> read();
}
