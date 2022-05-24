package com.bezkoder.spring.datajpa.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String username;
    private String password;
}
