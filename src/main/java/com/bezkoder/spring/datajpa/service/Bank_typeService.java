package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.model.Bank_type;
import com.bezkoder.spring.datajpa.repository.Bank_typeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class  Bank_typeService {

    @Autowired
    private Bank_typeRepository bank_typeRepository;

    public List<Bank_type> findAll() {


        List<Bank_type> bank_types = new ArrayList<Bank_type>();
        bank_typeRepository.findAll().forEach(e -> bank_types.add(e));

        return bank_types;
    }

    public Long count() {

        return bank_typeRepository.count();
    }

    public void deleteById(Long userId) {

        bank_typeRepository.deleteById(userId);
    }
}