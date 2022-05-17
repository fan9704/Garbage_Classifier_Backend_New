package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.*;
import com.bezkoder.spring.datajpa.repository.GarbageTypeRepository;
import com.bezkoder.spring.datajpa.repository.MachineRepository;
import com.bezkoder.spring.datajpa.repository.MachineStorageRepository;
import com.bezkoder.spring.datajpa.repository.UserRepository;
import com.bezkoder.spring.datajpa.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MachineController {

    @Autowired
    private MachineService machineService;
    @Autowired
    private MachineRepository machineRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GarbageTypeRepository garbageTypeRepository;
    @Autowired
    private MachineStorageRepository machineStorageRepository;
    @GetMapping("/machines")
    public List<Machine> allMachines() {
        return machineRepository.findAll();
    }

    @GetMapping("/machine/{id}")
    public ResponseEntity<Machine> getMachineById(@PathVariable("id") long id) {
        Optional<Machine> machineData = machineRepository.findById(id);

        if ( machineData.isPresent()) {
            return new ResponseEntity<>( machineData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/machine")
    public ResponseEntity<Machine> createMachine(@RequestBody MachineDTO machine) {
        try {
            Machine _machine;
            _machine = machineRepository
                    .save(new Machine(machine.getLocation(),false,false));
            List<Garbage_type> garbageTypeList=garbageTypeRepository.findAll();
            for (Garbage_type g: garbageTypeList) {
                machineStorageRepository.save(new Machine_storage(_machine,g,0.0));
            }
            return new ResponseEntity<>(_machine, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/machine/user/{id}")//Patch Machine current user
    public ResponseEntity<Machine> patchMachineUser(@PathVariable("id") long id, long user_id) {
        Optional<Machine> machineData = machineRepository.findById(id);
        Optional<User> userData = userRepository.findById(user_id);
        if (machineData.isPresent()) {
            Machine _machine = machineData.get();
            User _user=userData.get();
            _machine.setCurrent_user(_user);
            return new ResponseEntity<>(machineRepository.save(_machine), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/machine/link")
    public ResponseEntity<Machine> LinkMachine(@RequestBody LinkMachineDTO linkMachineDTO) {
        Optional<Machine> machineData = machineRepository.findById(linkMachineDTO.getId());
        User userData=userRepository.findById(linkMachineDTO.getCurrent_user()).get();
        if (machineData.isPresent()) {
            Machine _machine = machineData.get();
            if(userData!=machineData.get().getCurrent_user()){
                _machine.setUser_lock(true);
                _machine.setCurrent_user(userData);
            }
            return new ResponseEntity<>(machineRepository.save(_machine), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/machine/unlink")
    public ResponseEntity<Machine> UnlinkMachine(@RequestBody LinkMachineDTO linkMachineDTO) {
        Optional<Machine> machineData = machineRepository.findById(linkMachineDTO.getId());
        User anonymousUser=userRepository.findById((long)0).get();
        if (machineData.isPresent()) {
            Machine _machine = machineData.get();
            _machine.setUser_lock(false);
            _machine.setCurrent_user(anonymousUser);
            return new ResponseEntity<>(machineRepository.save(_machine), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/machine/{id}")
    public ResponseEntity<Machine> updateMachine(@PathVariable("id") long id, @RequestBody Machine machine) {
        Optional<Machine> machineData = machineRepository.findById(id);

        if (machineData.isPresent()) {
            System.out.println(machine.getLocation()+machine.getCurrent_user());//Check Variable
            Machine _machine = machineData.get();
            _machine.setLocation(machine.getLocation());
            _machine.setCurrent_user(machine.getCurrent_user());
            return new ResponseEntity<>(machineRepository.save(_machine), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/machine/{id}")
    public void deleteMachine(@PathVariable String id) {
        machineService.deleteById(Long.parseLong(id));
    }
}