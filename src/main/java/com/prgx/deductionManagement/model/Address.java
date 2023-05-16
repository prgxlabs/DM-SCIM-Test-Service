package com.prgx.deductionManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    @JsonIgnore
    private UUID id;
    private String type;
    private String streetAddress;
    private String locality;
    private String region;
    private String postalCode;
    private String country;
    @Nullable
    @JsonProperty("primary")
    private Boolean isPrimary;
    @Transient
    @PostConstruct
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String formatted (){
        return streetAddress.trim()+" "+
                locality.trim()+" "+
                region.trim()+" "+
                postalCode.trim()+" "+
                country.trim();
    }
}
