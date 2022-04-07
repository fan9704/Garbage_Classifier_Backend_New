package com.bezkoder.spring.datajpa.model;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Transfer_money_record")
public class Transfer_money_record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "receiver")
    private  User receiver;

    @Column(name = "amount")
    private double amount;

    @CreationTimestamp//Use Create time
    @Column(name= "time_stamp")
    private Timestamp time_stamp;

    @Column(name="bank_name")
    private String bank_name;

    public Transfer_money_record(User receiver,double amount,Timestamp time_stamp,String bank_name){
        this.receiver=receiver;
        this.time_stamp=time_stamp;
        this.time_stamp=time_stamp;
        this.bank_name=bank_name;
    }

}

