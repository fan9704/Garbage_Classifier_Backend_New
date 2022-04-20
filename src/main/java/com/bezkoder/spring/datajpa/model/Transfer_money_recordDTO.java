package com.bezkoder.spring.datajpa.model;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Transfer_money_recordDTO {
    private int id;
//    private  User receiver;
    private  int receiver;
    private BigDecimal amount;
//    private Timestamp time_stamp;
    private String bank_name;

}
