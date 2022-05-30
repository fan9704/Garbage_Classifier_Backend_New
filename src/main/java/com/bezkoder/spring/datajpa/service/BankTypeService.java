package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.model.Bank_type;
import com.bezkoder.spring.datajpa.repository.BankTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class  BankTypeService {

    @Autowired
    private BankTypeRepository bankTypeRepository;
    public List<Bank_type> findAll() {


        List<Bank_type> bank_types = new ArrayList<Bank_type>();
        bankTypeRepository.findAll().forEach(e -> bank_types.add(e));

        return bank_types;
    }
    public Long count() {

        return bankTypeRepository.count();
    }
    public void deleteById(Long userId) {

        bankTypeRepository.deleteById(userId);
    }
}