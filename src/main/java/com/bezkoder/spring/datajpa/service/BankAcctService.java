package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.model.Bank_acct;
import com.bezkoder.spring.datajpa.repository.BankAcctRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class  BankAcctService {

    @Autowired
    private BankAcctRepository bankAcctRepository;

    public List<Bank_acct> findAll() {


        List<Bank_acct> bank_accts = new ArrayList<Bank_acct>();
        bankAcctRepository.findAll().forEach(e -> bank_accts.add(e));

        return bank_accts;
    }

    public Long count() {

        return bankAcctRepository.count();
    }

    public void deleteById(Long userId) {

        bankAcctRepository.deleteById(userId);
    }
}