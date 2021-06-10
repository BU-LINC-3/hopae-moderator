package com.novang.hopae.moderator.model.pass;

import com.google.gson.annotations.SerializedName;
import com.novang.hopae.moderator.model.hopae.ConnectionInvitation;

import java.util.Objects;

public class ReceivedQRData {

    public static final String ISSUE = "issue";

    public static final String PROOF = "proof";

    @SerializedName("type")
    private String type;

    @SerializedName("sessionId")
    private String sessionId;

    @SerializedName("alias")
    private String alias;

    @SerializedName("invitation")
    private ConnectionInvitation invitation;

    @SerializedName("credRevId")
    private String credRevId;

    @SerializedName("revRegId")
    private String revRegId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public ConnectionInvitation getInvitation() {
        return invitation;
    }

    public void setInvitation(ConnectionInvitation invitation) {
        this.invitation = invitation;
    }

    public String getCredRevId() {
        return credRevId;
    }

    public void setCredRevId(String credRevId) {
        this.credRevId = credRevId;
    }

    public String getRevRegId() {
        return revRegId;
    }

    public void setRegRegId(String revRegId) {
        this.revRegId = revRegId;
    }

    public boolean isValidForm() {
        if (Objects.equals(type, ISSUE)) {
            return !Objects.equals(sessionId, null) && !Objects.equals(invitation, null);
        } else if (Objects.equals(type, PROOF)) {
            return !Objects.equals(alias, null) && !Objects.equals(credRevId, null) && !Objects.equals(revRegId, null);
        }
        return false;
    }
}
