package com.bezkoder.spring.datajpa.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Machine_storageDTO {
    private  long machine_id;
    private  long garbage_type;
    private double storage;
}
