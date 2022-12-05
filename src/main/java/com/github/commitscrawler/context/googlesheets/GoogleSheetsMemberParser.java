package com.github.commitscrawler.context.googlesheets;

import com.github.commitscrawler.context.Parser;
import com.github.commitscrawler.domain.Member;

import java.util.List;
import java.util.Map;

public class GoogleSheetsMemberParser implements Parser<Member, List<Object>> {
    private static final String GITHUB_URL = "https://github.com/";
    @Override
    public Member parse(List<Object> row) {
        Object nameCol = row.get(0);
        Map<String, String> repositories = (Map<String, String>) row.get(1);

        String name = nameCol.toString();

        return new Member(name, repositories);
    }
}
