package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.model.Machine_storage;
import com.bezkoder.spring.datajpa.repository.MachineStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class  Machine_storageService {

    @Autowired
    private MachineStorageRepository machineStorageRepository;

    public List<Machine_storage> findAll() {
        List<Machine_storage> machine_storages = new ArrayList<Machine_storage>();
        machineStorageRepository.findAll().forEach(e -> machine_storages.add(e));

        return machine_storages;
    }

    public Long count() {

        return machineStorageRepository.count();
    }

    public void deleteById(Long userId) {

        machineStorageRepository.deleteById(userId);
    }
}