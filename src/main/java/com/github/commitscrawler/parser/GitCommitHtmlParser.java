package com.github.commitscrawler.parser;

import com.github.commitscrawler.domain.commit.CommitDetail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GitCommitHtmlParser {

//    Document document = new

    public CommitDetail commitDetail(String htmlText) {
        Document doc = Jsoup.parse(htmlText);
        System.out.println(doc);

        return null;
    }

}
