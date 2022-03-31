package com.bezkoder.spring.datajpa.repository;
import com.bezkoder.spring.datajpa.model.Bank_acct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BankAcctRepository extends JpaRepository<Bank_acct, Long> {

}
