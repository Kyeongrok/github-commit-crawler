package com.github.commitscrawler.domain.commit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter @Setter
@NoArgsConstructor
public class CommitPayload {
    private String memberName;
    private String message;
    private String url;
    private Date push_at;

    public CommitPayload(String memberName) {
        this.memberName = memberName;
    }

    public void update(CommitPayload commitPayload) {
        updateMsg(commitPayload.getMessage());
        updatePushAt(commitPayload.getPush_at());
        updateUrl(commitPayload.getUrl());
    }

    private void updateUrl(String url) {
        if (url != null) this.url = url;
    }

    private void updateMsg(String message) {
        if (message != null) this.message = message;
    }

    private void updatePushAt(Date pushAt) {
        if (pushAt != null) this.push_at =pushAt;
    }
}
