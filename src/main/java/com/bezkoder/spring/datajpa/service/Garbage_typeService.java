package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.model.Garbage_type;
import com.bezkoder.spring.datajpa.repository.Garbage_typeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class  Garbage_typeService {

    @Autowired
    private Garbage_typeRepository garbage_typeRepository;

    public List<Garbage_type> findAll() {


        List<Garbage_type> garbage_types = new ArrayList<Garbage_type>();
        garbage_typeRepository.findAll().forEach(e -> garbage_types.add(e));

        return garbage_types;
    }

    public Long count() {

        return garbage_typeRepository.count();
    }

    public void deleteById(Long userId) {

        garbage_typeRepository.deleteById(userId);
    }
}