package com.bezkoder.spring.datajpa.model;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Machine_storage")
public class Machine_storage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private  Machine machine_id;

    @ManyToOne
    @JoinColumn(name = "garbage_type")
    private  Garbage_type garbage_type;

    @CreationTimestamp//Use Create time
    @Column(name= "time_stamp")
    private Timestamp time_stamp;

    @Column(name = "storage")
    private double storage;
    public Machine_storage(Machine machine_id,Garbage_type garbage_type,Timestamp time_stamp,double storage){
           this.machine_id=machine_id;
           this.garbage_type=garbage_type;
           this.time_stamp=time_stamp;
           this.storage=storage;
    }

}
