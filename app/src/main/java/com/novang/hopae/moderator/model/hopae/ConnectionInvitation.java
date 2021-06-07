package com.novang.hopae.moderator.model.hopae;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConnectionInvitation {

    @SerializedName("@id")
    public String id;

    @SerializedName("@type")
    public String type;

    @SerializedName("did")
    public String did;

    @SerializedName("imageUrl")
    public String imageUrl;

    @SerializedName("label")
    public String label;

    @SerializedName("recipientKeys")
    public List<String> recipientKeys;

    @SerializedName("routingKeys")
    public List<String> routingKeys;

    @SerializedName("serviceEndpoint")
    public String serviceEndPoint;

}
