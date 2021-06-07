package com.novang.hopae.moderator.model.hopae;

import com.google.gson.annotations.SerializedName;

public class IssueCredentialResponse {

    @SerializedName("cred_ex_id")
    private String schemaId;

    @SerializedName("thread_id")
    private String threadId;

    @SerializedName("created_id")
    private String createdId;

    public String getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(String schemaId) {
        this.schemaId = schemaId;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getCreatedId() {
        return createdId;
    }

    public void setCreatedId(String createdId) {
        this.createdId = createdId;
    }
}
