package com.bezkoder.spring.datajpa.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
import javax.persistence.*;
import java.sql.Blob;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MachineDTO {

    private String location;
    private Boolean user_lock;
    private Boolean machine_lock;
    @Lob
    private Blob machinePicture;
    private Garbage_recordDTO garbage_records;
    private Machine_storageDTO machineStorages;

    public Boolean isUser_lock(){
        return this.user_lock;
    }
    public Boolean isMachine_lock(){
        return this.machine_lock;
    }
}
