package com.bezkoder.spring.datajpa.service;


import com.bezkoder.spring.datajpa.model.Machine;
import com.bezkoder.spring.datajpa.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class  MachineService {

    @Autowired
    private MachineRepository machineRepository;

    public List<Machine> findAll() {


        List<Machine> machines = new ArrayList<Machine>();
        machineRepository.findAll().forEach(e -> machines.add(e));

        return machines;
    }

    public Long count() {

        return machineRepository.count();
    }

    public void deleteById(Long userId) {

        machineRepository.deleteById(userId);
    }
}