package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.model.Machine_storage;
import com.bezkoder.spring.datajpa.repository.Machine_storageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class  Machine_storageService {

    @Autowired
    private Machine_storageRepository machine_storageRepository;

    public List<Machine_storage> findAll() {
        List<Machine_storage> machine_storages = new ArrayList<Machine_storage>();
        machine_storageRepository.findAll().forEach(e -> machine_storages.add(e));

        return machine_storages;
    }

    public Long count() {

        return machine_storageRepository.count();
    }

    public void deleteById(Long userId) {

        machine_storageRepository.deleteById(userId);
    }
}