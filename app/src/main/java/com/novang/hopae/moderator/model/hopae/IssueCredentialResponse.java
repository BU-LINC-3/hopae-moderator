package com.novang.hopae.moderator.model.hopae;

import com.google.gson.annotations.SerializedName;

public class IssueCredentialResponse {

    @SerializedName("cred_ex_id")
    public String schemaId;

    @SerializedName("thread_id")
    public String threadId;

    @SerializedName("created_id")
    public String createdId;

}
