package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Machine;
import com.bezkoder.spring.datajpa.model.MachineDTO;
import com.bezkoder.spring.datajpa.model.User;
import com.bezkoder.spring.datajpa.repository.MachineRepository;
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
    @GetMapping("/machines")
    public List<Machine> allMachines() {

        return machineService.findAll();
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


    @PostMapping("/machine")//TODO: Auto Create Machine storage
    public ResponseEntity<Machine> createMachine(@RequestBody MachineDTO machine) {
        try {
            Machine _machine;
            _machine = machineRepository
                    .save(new Machine(machine.getLocation(),false,false));
            
            return new ResponseEntity<>(_machine, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/machine/user/{id}")//Patch Machine current user TODO:Should change Integer to int or Long
    public ResponseEntity<Machine> patchMachineUser(@PathVariable("id") long id,  long user_id) {
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
    @PatchMapping("/machine/{id}")
    public ResponseEntity<Machine> patchMachine(@PathVariable("id") long id, @RequestBody Machine machine) {
        Optional<Machine> machineData = machineRepository.findById(id);

        if (machineData.isPresent()) {
            System.out.println(machine.getLocation()+machine.getCurrent_user());//Check Variable
            Machine _machine = machineData.get();
            if(machine.getLocation()!=null || machine.getLocation()!= ""){
                _machine.setLocation(machine.getLocation());
            }
            if(machine.getCurrent_user()!=null){
                _machine.setLocation(machine.getLocation());//user id 0 is anonymous user
            }
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