package com.github.commitscrawler.context;

import com.github.commitscrawler.domain.Member;

import java.util.List;
import java.util.Map;

public interface DataReader {
    List<Member> readMembers();
    Map<Integer, String> readColumn();
}
