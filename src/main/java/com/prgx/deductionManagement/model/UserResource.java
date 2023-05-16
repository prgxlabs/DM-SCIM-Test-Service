package com.prgx.deductionManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserResource {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotNull
    private List<Schema> schemas;
    @Nullable
    private String externalId;
    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    private Meta meta;
    @NotNull
    @Column(nullable = false, unique = true)
    private String username;
    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    private Name name;
    @Nullable
    private String displayName;
    @Nullable
    private String nickName;
    @Nullable
    private String profileUrl;
    @Nullable
    private Boolean active;
    @Nullable
    @OneToMany(cascade = CascadeType.ALL)
    private List<Email> emails;
    @Nullable
    @OneToMany(cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers;
    @Nullable
    @OneToMany(cascade = CascadeType.ALL)
    private List<InstantMessage> ims;
    @Nullable
    @OneToMany(cascade = CascadeType.ALL)
    private List<Photo> photos;
    @Nullable
    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;
    @Nullable
    @OneToMany(cascade = CascadeType.ALL)
    private List<UserGroupRef> groups;
    @Nullable
    @OneToMany(cascade = CascadeType.ALL)
    private List<UserRoleRef> roles;
    @Nullable
    @OneToMany(cascade = CascadeType.ALL)
    private List<X509Certificate> x509Certificates;
}