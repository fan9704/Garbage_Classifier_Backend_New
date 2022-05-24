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
@NoArgsConstructor
public class Transfer_money_recordDTO {
    private  long receiver;
    private BigDecimal amount;
    private String bank_name;

}
