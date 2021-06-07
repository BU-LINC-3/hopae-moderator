package com.novang.hopae.moderator.model.hopae;

import com.google.gson.annotations.SerializedName;

public class RequestProofResponse {

    @SerializedName("verified")
    private boolean verified;

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
