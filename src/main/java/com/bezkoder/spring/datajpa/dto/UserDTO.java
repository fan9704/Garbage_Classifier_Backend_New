package com.bezkoder.spring.datajpa.dto;

import lombok.*;


@Data
public class UserDTO {
    private String userName;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private Boolean active;

}
