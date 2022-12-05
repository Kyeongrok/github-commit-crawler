package com.github.commitscrawler.context.googlesheets;

import com.github.commitscrawler.context.Parser;
import com.github.commitscrawler.domain.Member;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class GoogleSheetsDataReaderTest {
    private final GoogleSheets googleSheets = new GoogleSheets();
    private final Parser<Member, List<Object>> parser = new GoogleSheetsMemberParser();
    private final GoogleSheetsDataReader googleSheetsMemberReader = new GoogleSheetsDataReader(googleSheets, parser);

    @Test
    void read() {
        List<Member> members = googleSheetsMemberReader.readMemberRepositories();
        for (Member member : members) {
            System.out.println(member);
        }
    }

    @Test
    void readColumn() {
        Map<Integer, String> columns = googleSheetsMemberReader.readColumn();
        System.out.println(columns);
    }
}