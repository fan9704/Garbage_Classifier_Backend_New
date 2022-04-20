package com.bezkoder.spring.datajpa.repository;

import com.bezkoder.spring.datajpa.model.Transfer_money_record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TransferMoneyRecordRepository extends JpaRepository<Transfer_money_record, Long> {
}
