package com.bezkoder.spring.datajpa.model;

import lombok.Getter;
import lombok.Setter;
import lombok.*;


@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Back_acctDTO {
    private int id;
//    private  Bank_type bank_type;
    private  int bank_type;
    private String account_code;
}