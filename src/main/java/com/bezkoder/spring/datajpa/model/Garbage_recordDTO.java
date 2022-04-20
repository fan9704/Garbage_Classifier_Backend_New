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
public class Garbage_recordDTO {
//    private int id;
//    private  Garbage_type garbage_type;
    private  long garbage_type;
    private  double weight;
//    private  User user;
    private  long user;
//    private Timestamp time_stamp;
//    private  Machine machine_id;
    private  long machine_id;

}