package com.github.commitscrawler.context.googlesheets;

import com.github.commitscrawler.context.DataReader;
import com.github.commitscrawler.context.Parser;
import com.github.commitscrawler.domain.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GoogleSheetsDataReader implements DataReader {
    private final GoogleSheets googleSheets;
    private final Parser<Member, List<Object>> parser;

    public GoogleSheetsDataReader(GoogleSheets googleSheets, Parser<Member, List<Object>> parser) {
        this.googleSheets =googleSheets;
        this.parser = parser;
    }

    @Override
    public List<Member> readMembers() {
        final String sheetsId = "1cJ9XQDISo3B6UHzcDmWtG9ucDRDg_YgJPkkW9atCFjk";
        final String range = "repositories!2:100";
        List<List<Object>> values = googleSheets.read(sheetsId, range);
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
            return null;
        }
        Map<Integer, String> columns = readColumn();

        return values.stream()
                .parallel()
                .filter((row) -> row.size() > 0)
                .map((row) -> {
                    Map<String, String> repositories = new HashMap<>();
                    for (int i = row.size() - 1; i > 0; i--) { // 이름(인덱스:0) 제외
                        String repoUrl = row.remove(i).toString();
                        repositories.put(columns.get(i), repoUrl);
                    }
                    row.add(1, repositories);
                    return parser.parse(row);
                }).collect(Collectors.toList());
    }

    @Override
    public Map<Integer, String> readColumn() {
        final String sheetsId = "1cJ9XQDISo3B6UHzcDmWtG9ucDRDg_YgJPkkW9atCFjk";
        final String range = "repositories!1:1";
        Map<Integer, String> columns = new HashMap<>();
        List<Object> columnRow = googleSheets.read(sheetsId, range).get(0);
        for (int i = 0; i < columnRow.size(); i++) {
            String column = null;
            if (columnRow.get(i) instanceof String) {
                column = (String) columnRow.get(i);
            }
            if (column != null && column.length() != 0) columns.put(i, (String)columnRow.get(i));
        }
        return columns;
    }
}
