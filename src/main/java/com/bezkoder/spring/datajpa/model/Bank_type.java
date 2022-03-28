package com.bezkoder.spring.datajpa.model;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Bank_type")
public class Bank_type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "bank_name")
    private String bank_name;
    @Column(name= "bank_code")
    private int bank_code;

    public Bank_type(String bank_name,int bank_code){
        this.bank_name=bank_name;
        this.bank_code=bank_code;
    }

}
