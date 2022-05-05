package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.model.Transfer_money_record;
import com.bezkoder.spring.datajpa.model.User;
import com.bezkoder.spring.datajpa.repository.BankAcctRepository;
import com.bezkoder.spring.datajpa.repository.TransferMoneyRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bezkoder.spring.datajpa.model.Bank_acct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class TransferMoneyRecordService {

    @Autowired
    private TransferMoneyRecordRepository transferMoneyRecordRepository;
    @Autowired
    private BankAcctRepository bankAcctRepository;

    public List<Transfer_money_record> findAll() {


        List<Transfer_money_record> garbage_types = new ArrayList<Transfer_money_record>();
        transferMoneyRecordRepository.findAll().forEach(e -> garbage_types.add(e));

        return garbage_types;
    }

    public Long count() {

        return transferMoneyRecordRepository.count();
    }

    public void deleteById(Long userId) {

        transferMoneyRecordRepository.deleteById(userId);
    }

    public void saveRecord(User user, BigDecimal amount){
        Bank_acct acct = bankAcctRepository.findOneByUser(user);
        String accountCode = acct.getAccount_code();
        transferMoneyRecordRepository.save(new Transfer_money_record(user,amount,accountCode));
    }
}
