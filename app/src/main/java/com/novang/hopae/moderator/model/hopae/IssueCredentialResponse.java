package com.novang.hopae.moderator.model.hopae;

import com.google.gson.annotations.SerializedName;

public class IssueCredentialResponse {

    @SerializedName("cred_ex_id")
    private String credExId;

    @SerializedName("thread_id")
    private String threadId;

    @SerializedName("created_at")
    private String createdAt;

    public String getCredExId() {
        return credExId;
    }

    public void setCredExId(String credExId) {
        this.credExId = credExId;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
