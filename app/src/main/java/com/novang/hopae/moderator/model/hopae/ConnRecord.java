package com.novang.hopae.moderator.model.hopae;

import com.google.gson.annotations.SerializedName;

public class ConnRecord {

    @SerializedName("accept")
    private String accept;

    @SerializedName("alias")
    private String alias;

    @SerializedName("connection_id")
    private String connectionId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("error_msg")
    private String errorMsg;

    @SerializedName("inbound_connection_id")
    private String inboundConnectionId;

    @SerializedName("invitation_key")
    private String invitationKey;

    @SerializedName("invitation_mode")
    private String invitationMode;

    @SerializedName("invitation_msg_id")
    private String invitationMsdId;

    @SerializedName("my_did")
    private String myDID;

    @SerializedName("request_id")
    private String requestId;

    @SerializedName("rfc23_state")
    private String rfc23State;

    @SerializedName("routing_state")
    private String routingState;

    @SerializedName("state")
    private String state;

    @SerializedName("their_did")
    private String theirDID;

    @SerializedName("their_label")
    private String theirLabel;

    @SerializedName("their_public_did")
    private String theirPublicDID;

    @SerializedName("their_role")
    private String theirRole;

    @SerializedName("updated_at")
    private String updatedAt;

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getInboundConnectionId() {
        return inboundConnectionId;
    }

    public void setInboundConnectionId(String inboundConnectionId) {
        this.inboundConnectionId = inboundConnectionId;
    }

    public String getInvitationKey() {
        return invitationKey;
    }

    public void setInvitationKey(String invitationKey) {
        this.invitationKey = invitationKey;
    }

    public String getInvitationMode() {
        return invitationMode;
    }

    public void setInvitationMode(String invitationMode) {
        this.invitationMode = invitationMode;
    }

    public String getInvitationMsdId() {
        return invitationMsdId;
    }

    public void setInvitationMsdId(String invitationMsdId) {
        this.invitationMsdId = invitationMsdId;
    }

    public String getMyDID() {
        return myDID;
    }

    public void setMyDID(String myDID) {
        this.myDID = myDID;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRfc23State() {
        return rfc23State;
    }

    public void setRfc23State(String rfc23State) {
        this.rfc23State = rfc23State;
    }

    public String getRoutingState() {
        return routingState;
    }

    public void setRoutingState(String routingState) {
        this.routingState = routingState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTheirDID() {
        return theirDID;
    }

    public void setTheirDID(String theirDID) {
        this.theirDID = theirDID;
    }

    public String getTheirLabel() {
        return theirLabel;
    }

    public void setTheirLabel(String theirLabel) {
        this.theirLabel = theirLabel;
    }

    public String getTheirPublicDID() {
        return theirPublicDID;
    }

    public void setTheirPublicDID(String theirPublicDID) {
        this.theirPublicDID = theirPublicDID;
    }

    public String getTheirRole() {
        return theirRole;
    }

    public void setTheirRole(String theirRole) {
        this.theirRole = theirRole;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}