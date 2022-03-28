package com.bezkoder.spring.datajpa.repository;

import com.bezkoder.spring.datajpa.model.Garbage_type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Garbage_typeRepository extends JpaRepository<Garbage_type, Long>{
}