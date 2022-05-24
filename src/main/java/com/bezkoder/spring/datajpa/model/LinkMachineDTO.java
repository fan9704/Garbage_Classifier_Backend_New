package com.bezkoder.spring.datajpa.model;
import lombok.Getter;
import lombok.Setter;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkMachineDTO {
    private  long id;
    private  long  current_user;
}
