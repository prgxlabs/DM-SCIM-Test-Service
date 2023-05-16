package com.prgx.deductionManagement.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Schema {
    USER("urn:ietf:params:scim:schemas:core:2.0:User"),
    SERVICEPROVIDERCONFIG("urn:ietf:params:scim:schemas:core:2.0:ServiceProviderConfig"),
    SUPPLIERHUB("urn:scim:schemas:extension:supplierHubExtenstion:1.0");

    private String schema;
    Schema(String scimSchema){
        this.schema = scimSchema;
    }

    @JsonValue
    public String getSchema(){
        return this.schema;
    }
}
