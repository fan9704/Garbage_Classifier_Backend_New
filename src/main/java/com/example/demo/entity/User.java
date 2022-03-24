package com.example.demo.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "user")
public class User {
    private int id;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;
    @NonNull

    @Column(name = "password", nullable = false, length = 50)
    private String password;
    @NonNull

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @NonNull

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}