package com.novang.hopae.moderator.model.hopae;

import com.google.gson.annotations.SerializedName;

public class CreateSchemaResponse {

    @SerializedName("schema_id")
    private String schemaId;

    public String getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(String schemaId) {
        this.schemaId = schemaId;
    }
}
