package com.github.commitscrawler.context.googlesheets;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoogleSheetsTest {
    private final GoogleSheets googleSheets = new GoogleSheets();
    private final String SHEET_ID = "1cJ9XQDISo3B6UHzcDmWtG9ucDRDg_YgJPkkW9atCFjk";
    @Test
    void read() {
        final String range = "repositories!2:100";
        List<List<Object>> result = googleSheets.read(SHEET_ID, range);
        System.out.println(result);
    }
}