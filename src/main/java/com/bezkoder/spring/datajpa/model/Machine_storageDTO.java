package com.bezkoder.spring.datajpa.model;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Machine_storageDTO {
    private int id;
//    private  Machine machine_id;
    private  int machine_id;
//    private  Garbage_type garbage_type;
    private  int garbage_type;
    private double storage;
}
