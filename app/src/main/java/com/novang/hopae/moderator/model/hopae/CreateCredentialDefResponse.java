package com.novang.hopae.moderator.model.hopae;

import com.google.gson.annotations.SerializedName;

public class CreateCredentialDefResponse {

    @SerializedName("cred_def_id")
    private String credDefId;

    public String getCredDefId() {
        return credDefId;
    }

    public void setCredDefId(String credDefId) {
        this.credDefId = credDefId;
    }
}
