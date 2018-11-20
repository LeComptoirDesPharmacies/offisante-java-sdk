package fr.lecomptoirdespharmacies.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class UserCredentials extends BaseEntity{

    @JsonProperty("UserName")
    private String name;

    @JsonProperty("Password")
    private String password;

    @JsonProperty("AppKey")
    private String key;
}
