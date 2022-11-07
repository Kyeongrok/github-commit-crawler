package com.github.commitscrawler.domain.commit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@NoArgsConstructor
public class CommitPayload {
    private String memberName;
    private String message;
    private Date push_at;

    public CommitPayload(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPush_at(Date push_at) {
        this.push_at = push_at;
    }

    public void update(CommitPayload commitPayload) {
        updateMsg(commitPayload.getMessage());
        updatePushAt(commitPayload.getPush_at());
    }

    private void updateMsg(String message) {
        if (message != null) this.message = message;
    }

    private void updatePushAt(Date pushAt) {
        if (pushAt != null) this.push_at =pushAt;
    }
}
