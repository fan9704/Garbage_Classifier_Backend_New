package com.bezkoder.spring.datajpa.repository;
import com.bezkoder.spring.datajpa.model.Machine_storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface Machine_storageRepository extends JpaRepository<Machine_storage, Long> {

}