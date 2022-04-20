package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.model.Garbage_type;
import com.bezkoder.spring.datajpa.repository.GarbageTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GarbageTypeService {

    @Autowired
    private GarbageTypeRepository garbageTypeRepository;

    public List<Garbage_type> findAll() {


        List<Garbage_type> garbage_types = new ArrayList<Garbage_type>();
        garbageTypeRepository.findAll().forEach(e -> garbage_types.add(e));

        return garbage_types;
    }

    public Long count() {

        return garbageTypeRepository.count();
    }

    public void deleteById(Long userId) {

        garbageTypeRepository.deleteById(userId);
    }
}