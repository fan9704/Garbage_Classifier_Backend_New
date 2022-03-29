package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Machine_storage;
import com.bezkoder.spring.datajpa.repository.Machine_storageRepository;
import com.bezkoder.spring.datajpa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Machine_storageController {

    @Autowired
    private Machine_storageService machine_storageService;
    @Autowired
    private Machine_storageRepository machine_storageRepository;
    @GetMapping("/machines_storage")
    public List<Machine_storage> allMachine_storages() {

        return machine_storageService.findAll();
    }

    @GetMapping("/machine_storage/{id}")
    public ResponseEntity<Machine_storage> getMachine_storageById(@PathVariable("id") long id) {
        Optional<Machine_storage> machine_storageData = machine_storageRepository.findById(id);

        if ( machine_storageData.isPresent()) {
            return new ResponseEntity<>( machine_storageData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/machine_storage")
    public ResponseEntity<Machine_storage> createMachine_storage(@RequestBody Machine_storage machine_storage) {
        try {
            Machine_storage _machine_storage = machine_storageRepository
                    .save(new Machine_storage(machine_storage.getMachine_id(),machine_storage.getGarbage_type(),machine_storage.getTime_stamp(),machine_storage.getStorage()));
            return new ResponseEntity<>(_machine_storage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/machine/{id}")
    public ResponseEntity<Machine_storage> patchMachine_storage(@PathVariable("id") long id, @RequestBody Machine_storage machine_storage) {
        Optional<Machine_storage> machine_storageData = machine_storageRepository.findById(id);

        if (machine_storageData.isPresent()) {
            System.out.println(machine_storage.getMachine_id());
            System.out.println(machine_storage.getTime_stamp());//Check Variable
            Machine_storage _machine_storage = machine_storageData.get();
            if(machine_storage.getMachine_id()!=null ){
                _machine_storage.setMachine_id(machine_storage.getMachine_id());
            }
            if(machine_storage.getTime_stamp()!=null ){
                _machine_storage.setTime_stamp(machine_storage.getTime_stamp());
            }
            return new ResponseEntity<>(machine_storageRepository.save(_machine_storage), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/machine_storage/{id}")
    public ResponseEntity<Machine_storage> updateMachine_storage(@PathVariable("id") long id, @RequestBody Machine_storage machine_storage) {
        Optional<Machine_storage> machine_storageData = machine_storageRepository.findById(id);

        if (machine_storageData.isPresent()) {
            System.out.println(machine_storage.getMachine_id());
            System.out.println(machine_storage.getTime_stamp());//Check Variable
            Machine_storage _machine_storage = machine_storageData.get();
            _machine_storage.setMachine_id(machine_storage.getMachine_id());
            _machine_storage.setTime_stamp(machine_storage.getTime_stamp());
            return new ResponseEntity<>(machine_storageRepository.save(_machine_storage), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/machine_storage/{id}")
    public void deleteMachine(@PathVariable String id) {
        machine_storageService.deleteById(Long.parseLong(id));
    }
}