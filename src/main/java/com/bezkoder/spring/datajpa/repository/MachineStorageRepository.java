package com.bezkoder.spring.datajpa.repository;
import com.bezkoder.spring.datajpa.model.Garbage_type;
import com.bezkoder.spring.datajpa.model.Machine;
import com.bezkoder.spring.datajpa.model.Machine_storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MachineStorageRepository extends JpaRepository<Machine_storage, Long> {
//    Machine_storage findByMachine(Machine machine);
//    Machine_storage findByGarbageType(Garbage_type garbage_type);
    Machine_storage findOneByMachineAndGarbageType(Machine machine,Garbage_type garbage_type);
}