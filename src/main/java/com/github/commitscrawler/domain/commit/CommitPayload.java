package com.github.commitscrawler.domain.commit;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
public class CommitPayload {
    private String memberName;
    private String message;
    private Date push_at;

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPush_at(Date push_at) {
        this.push_at = push_at;
    }
}
