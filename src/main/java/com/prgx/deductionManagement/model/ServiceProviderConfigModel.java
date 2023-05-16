package com.prgx.deductionManagement.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProviderConfigModel {
    @NotNull
    private List<Schema> schemas = List.of(Schema.SERVICEPROVIDERCONFIG);
    private String documentationUri;
    private Map<String, Object> patch = Map.of("supported", false);
    private Map<String, Object> bulk = Map.of("supported", false);
    private Map<String, Object> filter = Map.of("supported", false);
    private Map<String, Object> changePassword = Map.of("supported", false);
    private Map<String, Object> sort = Map.of("supported", true);
    private Map<String, Object> etag = Map.of("supported", false);
    private Meta meta = Meta.builder()
            .resourceType("ServiceProviderConfig")
            .created(Instant.now().toString())
            .lastModified(Instant.now().toString())
            .build();
}
