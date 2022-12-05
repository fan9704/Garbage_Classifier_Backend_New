package com.bezkoder.spring.datajpa.repository;
import com.bezkoder.spring.datajpa.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Blob;
import java.util.List;


@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

    List<Machine> findAllMachineByLocation(String location);
    Blob findMachinePictureByLocation(String location);
}