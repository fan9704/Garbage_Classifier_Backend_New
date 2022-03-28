package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.model.Bank_acct;
import com.bezkoder.spring.datajpa.repository.Bank_acctRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class  Bank_acctService {

    @Autowired
    private Bank_acctRepository bank_acctRepository;

    public List<Bank_acct> findAll() {


        List<Bank_acct> bank_accts = new ArrayList<Bank_acct>();
        bank_acctRepository.findAll().forEach(e -> bank_accts.add(e));

        return bank_accts;
    }

    public Long count() {

        return bank_acctRepository.count();
    }

    public void deleteById(Long userId) {

        bank_acctRepository.deleteById(userId);
    }
}