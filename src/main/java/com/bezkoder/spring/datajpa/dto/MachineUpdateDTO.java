
package com.bezkoder.spring.datajpa.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
import java.sql.Blob;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MachineUpdateDTO {//TODO:apply in MachineController

    private String location;
    private boolean user_lock;
    private boolean machine_lock;
    private Blob machinePicture;

}
