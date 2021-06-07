package com.novang.hopae.moderator.model.hopae;

import com.google.gson.annotations.SerializedName;

public class RevokeCredentialResponse {

    @SerializedName("revoked")
    private boolean revoked;

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }
}
