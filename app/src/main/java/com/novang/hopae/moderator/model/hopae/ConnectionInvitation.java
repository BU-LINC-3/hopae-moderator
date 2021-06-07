package com.novang.hopae.moderator.model.hopae;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConnectionInvitation {

    @SerializedName("@id")
    private String id;

    @SerializedName("@type")
    private String type;

    @SerializedName("did")
    private String did;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("label")
    private String label;

    @SerializedName("recipientKeys")
    private List<String> recipientKeys;

    @SerializedName("routingKeys")
    private List<String> routingKeys;

    @SerializedName("serviceEndpoint")
    private String serviceEndPoint;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getRecipientKeys() {
        return recipientKeys;
    }

    public void setRecipientKeys(List<String> recipientKeys) {
        this.recipientKeys = recipientKeys;
    }

    public List<String> getRoutingKeys() {
        return routingKeys;
    }

    public void setRoutingKeys(List<String> routingKeys) {
        this.routingKeys = routingKeys;
    }

    public String getServiceEndPoint() {
        return serviceEndPoint;
    }

    public void setServiceEndPoint(String serviceEndPoint) {
        this.serviceEndPoint = serviceEndPoint;
    }
}
