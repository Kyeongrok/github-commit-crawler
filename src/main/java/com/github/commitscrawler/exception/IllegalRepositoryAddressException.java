package com.github.commitscrawler.exception;

public class IllegalRepositoryAddressException extends RuntimeException{
    public IllegalRepositoryAddressException(String name, String subject, String repositoryAddress) {
        super(String.format("잘못된 리포지토리 주소입니다. student:%s subject:%s repository:%s", name, subject, repositoryAddress));
    }
}
