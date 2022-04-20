package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Garbage_type;
import com.bezkoder.spring.datajpa.model.Machine;
import com.bezkoder.spring.datajpa.model.Machine_storage;
import com.bezkoder.spring.datajpa.model.Machine_storageDTO;
import com.bezkoder.spring.datajpa.repository.GarbageTypeRepository;
import com.bezkoder.spring.datajpa.repository.MachineRepository;
import com.bezkoder.spring.datajpa.repository.MachineStorageRepository;
import com.bezkoder.spring.datajpa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MachineStorageController {

    @Autowired
    private MachineStorageService machine_storageService;
    @Autowired
    private MachineStorageRepository machine_storageRepository;
    @Autowired
    private MachineRepository machineRepository;
    @Autowired
    private GarbageTypeRepository garbageTypeRepository;
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
    public ResponseEntity<Machine_storage> createMachine_storage(@RequestBody Machine_storageDTO machine_storageDTO) {
        try {
            Machine machine=machineRepository.findById(machine_storageDTO.getMachine_id()).get();
            Garbage_type garbage_type=garbageTypeRepository.findById(machine_storageDTO.getGarbage_type()).get();
            Machine_storage _machine_storage = machine_storageRepository
                    .save(new Machine_storage(machine,garbage_type,machine_storageDTO.getStorage()));
            return new ResponseEntity<>(_machine_storage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/machine_storage/{id}")
    public ResponseEntity<Machine_storage> patchMachine_storage(@PathVariable("id") long id, @RequestBody Machine_storageDTO machine_storageDTO) {
        Optional<Machine_storage> machine_storageData = machine_storageRepository.findById(id);
        Machine machine=machineRepository.findById(machine_storageDTO.getMachine_id()).get();
        Garbage_type garbage_type=garbageTypeRepository.findById(machine_storageDTO.getGarbage_type()).get();
        if (machine_storageData.isPresent()) {
            System.out.println(machine_storageDTO.getMachine_id());
            Machine_storage _machine_storage = machine_storageData.get();
            if(machine_storageDTO.getMachine_id()!=machine_storageData.get().getMachine_id().getId() ){
                _machine_storage.setMachine_id(machine);
            }
            if(machine_storageDTO.getGarbage_type()!=machine_storageData.get().getGarbage_type().getId()){
                _machine_storage.setGarbage_type(garbage_type);
            }
            return new ResponseEntity<>(machine_storageRepository.save(_machine_storage), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/machine_storage/{id}")
    public ResponseEntity<Machine_storage> updateMachine_storage(@PathVariable("id") long id, @RequestBody Machine_storageDTO machine_storageDTO) {
        Optional<Machine_storage> machine_storageData = machine_storageRepository.findById(id);
        Machine machine=machineRepository.findById(machine_storageDTO.getMachine_id()).get();
        Garbage_type garbage_type=garbageTypeRepository.findById(machine_storageDTO.getGarbage_type()).get();
        if (machine_storageData.isPresent()) {
            System.out.println(machine_storageDTO.getMachine_id());
            Machine_storage _machine_storage = machine_storageData.get();
            _machine_storage.setMachine_id(machine);
            _machine_storage.setGarbage_type(garbage_type);
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