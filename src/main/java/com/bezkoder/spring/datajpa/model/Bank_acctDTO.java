package com.bezkoder.spring.datajpa.model;

import lombok.Getter;
import lombok.Setter;
import lombok.*;


@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Bank_acctDTO {
//    private long id;
//    private  Bank_type bank_type;
    private  long bank_type;
    private String account_code;
    private long user;
}