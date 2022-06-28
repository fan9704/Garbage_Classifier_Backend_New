package com.bezkoder.spring.datajpa.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Garbage_recordDTO {
    private  long garbage_type;
    private  double weight;
    private  long user;
    private  long machine_id;

}