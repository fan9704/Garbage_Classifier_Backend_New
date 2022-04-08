package com.bezkoder.spring.datajpa.repository;
import com.bezkoder.spring.datajpa.model.Garbage_record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface GarbageRecordRepository extends JpaRepository<Garbage_record, Long> {

}