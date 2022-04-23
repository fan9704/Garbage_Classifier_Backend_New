package com.bezkoder.spring.datajpa.repository;
import com.bezkoder.spring.datajpa.model.Bank_acct;
import com.bezkoder.spring.datajpa.model.Bank_type;
import com.bezkoder.spring.datajpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BankAcctRepository extends JpaRepository<Bank_acct, Long> {
//    List<Bank_acct> findByBankType(Bank_type bank_type);
    Bank_acct findOneByUser(User user);
}
