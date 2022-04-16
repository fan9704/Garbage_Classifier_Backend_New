package com.bezkoder.spring.datajpa.model;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
public class MachineDTO {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String location;
    private boolean user_lock;
    private boolean machine_lock;

}
