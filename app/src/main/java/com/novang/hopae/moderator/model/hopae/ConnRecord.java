package com.novang.hopae.moderator.model.hopae;

import com.google.gson.annotations.SerializedName;

public class ConnRecord {

    @SerializedName("accept")
    public String accept;

    @SerializedName("alias")
    public String alias;

    @SerializedName("connection_id")
    public String connectionId;

    @SerializedName("created_at")
    public String createdAt;

    @SerializedName("error_msg")
    public String errorMsg;

    @SerializedName("inbound_connection_id")
    public String inboundConnectionId;

    @SerializedName("invitation_key")
    public String invitationKey;

    @SerializedName("invitation_mode")
    public String invitationMode;

    @SerializedName("invitation_msg_id")
    public String invitationMsdId;

    @SerializedName("my_did")
    public String myDID;

    @SerializedName("request_id")
    public String requestId;

    @SerializedName("rfc23_state")
    public String rfc23State;

    @SerializedName("routing_state")
    public String routingState;

    @SerializedName("state")
    public String state;

    @SerializedName("their_did")
    public String theirDID;

    @SerializedName("their_label")
    public String theirLabel;

    @SerializedName("their_public_did")
    public String theirPublicDID;

    @SerializedName("their_role")
    public String theirRole;

    @SerializedName("updated_at")
    public String updatedAt;

}