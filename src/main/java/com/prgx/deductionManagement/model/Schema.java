package com.prgx.deductionManagement.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Schema {
    USER("urn:ietf:params:scim:schemas:core:2.0:User"),
    SERVICEPROVIDERCONFIG("urn:ietf:params:scim:schemas:core:2.0:ServiceProviderConfig"),
    SUPPLIERHUB("urn:com:kroger:ess:vendorsso:v1:lavante:User");

    private String schema;
    Schema(String scimSchema){
        this.schema = scimSchema;
    }

    @JsonValue
    public String getSchema(){
        return this.schema;
    }
}
