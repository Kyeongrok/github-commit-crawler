package com.github.commitscrawler.domain.commit;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter @Setter
public class GitUser {
    private String name;
    private String email;
    private Date date;
}
