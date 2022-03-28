package com.bezkoder.spring.datajpa.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Machine")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Nullable
    @Column(name = "location")
    private String location;
    @Column(name= "user_lock",columnDefinition = "boolean default false")
    private boolean user_lock;
    @Column(name= "machine_lock",columnDefinition = "boolean default false")
    private boolean machine_lock;
    @ManyToOne
    @Nullable
    @JoinColumn(name = "user_id")
    private  User current_user;

    public Machine(String location,boolean user_lock,boolean machine_lock,User current_user){
        this.location=location;
        this.user_lock=user_lock;
        this.machine_lock=machine_lock;
        this.current_user=current_user;
    }

}
