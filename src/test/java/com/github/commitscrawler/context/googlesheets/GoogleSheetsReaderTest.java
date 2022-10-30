package com.github.commitscrawler.context.googlesheets;

import com.github.commitscrawler.context.Parser;
import com.github.commitscrawler.domain.Member;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoogleSheetsReaderTest {
    private final Parser<Member, List<Object>> parser = new GoogleSheetsMemberParser();
    private final GoogleSheetsReader googleSheetsReader = new GoogleSheetsReader(parser);

    @Test
    void read() {
        List<Member> members = googleSheetsReader.read();
        System.out.println(members);
        assertEquals(83, members.size());
    }
}