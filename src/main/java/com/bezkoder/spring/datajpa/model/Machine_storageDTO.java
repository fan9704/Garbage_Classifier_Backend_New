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
//    private long id;
//    private  Machine machine_id;
    private  long machine_id;
//    private  Garbage_type garbage_type;
    private  long garbage_type;
    private double storage;
}
