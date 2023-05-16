package com.prgx.deductionManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
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
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class Name {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    @JsonIgnore
    private UUID id;
    @Transient
    @PostConstruct
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String formatted() {
        StringBuilder formattedName = new StringBuilder();
        if(honorificPrefix!=null && !honorificPrefix.trim().isEmpty()){
            formattedName.append(honorificPrefix.trim()).append(" ");
        }
        formattedName.append(givenName.trim()).append(" ");
        if(middleName!=null && !middleName.trim().isEmpty()){
            formattedName.append(middleName.trim());
        }
        formattedName.append(familyName.trim()).append(" ");
        if(honorificSuffix!=null && !honorificSuffix.trim().isEmpty()){
            formattedName.append(honorificSuffix.trim()).append(" ");
        }
        return formattedName.toString();
    }
    @NotNull
    @Size.List({
            @Size(min = 2, message = "{validation.user.name.size.short}"),
            @Size(max = 50, message = "{validation.user.name.size.long}")
    })
    private String familyName;
    @NotNull
    @Size.List({
            @Size(min = 2, message = "{validation.user.name.size.short}"),
            @Size(max = 50, message = "{validation.user.name.size.long}")
    })
    private String givenName;
    @Nullable
    private String middleName;
    @Nullable
    private String honorificPrefix;
    @Nullable
    private String honorificSuffix;
}
