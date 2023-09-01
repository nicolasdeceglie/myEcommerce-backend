package com.nicolas.myEcommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private long id;
    private String name;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String Address;
    private String phoneNumber;
    private String createDate;
    @JsonIgnore
    private List<String> authorities;
}
