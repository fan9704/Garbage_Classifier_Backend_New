package com.bezkoder.spring.datajpa.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Nullable
    @Column(name = "change_value")
    private double change_value;//"- transfer money {transfer account}" "+ recycle {recycle_type} add {total_amount}"

    @Column(name = "description")
    private String description;

    @ManyToOne
    @Nullable
    @JoinColumn(name = "user_id")
    private  User user_id;

//    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp//Use Create time
    @Column(name= "time_stamp")
    private Timestamp time_stamp;


    public Wallet(double change_value,String description,User user_id,Timestamp time_stamp){
        this.change_value=change_value;
        this.description=description;
        this.user_id=user_id;
        this.time_stamp=time_stamp;
    }

}
