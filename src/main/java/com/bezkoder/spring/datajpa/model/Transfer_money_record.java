package com.bezkoder.spring.datajpa.model;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name= "time_stamp")
    private Date time_stamp;

    @Column(name="bank_name")
    private String bank_name;

    public Transfer_money_record(User receiver,double amount,Date time_stamp,String bank_name){
        this.receiver=receiver;
        this.time_stamp=time_stamp;
        this.time_stamp=time_stamp;
        this.bank_name=bank_name;
    }

}

