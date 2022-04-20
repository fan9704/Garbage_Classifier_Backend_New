package com.bezkoder.spring.datajpa.model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@Data
public class UserDTO {
//    private long id;
    private String userName;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private Boolean active;

}
