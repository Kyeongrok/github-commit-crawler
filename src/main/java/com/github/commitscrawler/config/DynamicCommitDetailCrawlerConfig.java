package com.github.commitscrawler.config;

import com.github.commitscrawler.crawler.CommitDetailCrawler;
import com.github.commitscrawler.crawler.GitHtmlParser;
import com.github.commitscrawler.parser.GitCommitHtmlParser;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class DynamicCommitDetailCrawlerConfig implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CommitDetailCrawler && System.getenv().get("GIT_TOKEN") == null){
            System.out.println("GIT_TOKEN의 값이 NULL입니다. GitHtmlParser를 사용합니다.");
            return new GitHtmlParser(WebClient.builder().baseUrl("https://github.com").build(), new GitCommitHtmlParser());
        }
        return bean;
    }
}
