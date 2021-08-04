package com.volkov.consumer.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DemoUserDTO {

    @JsonProperty("username")
    String username;
    @JsonProperty("firstname")
    String firstname;

    public DemoUserDTO(@JsonProperty("username") String username, @JsonProperty("firstname") String firstname) {
        this.username = username;
        this.firstname = firstname;
    }

}
