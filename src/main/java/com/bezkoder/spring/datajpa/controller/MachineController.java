package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Machine;
import com.bezkoder.spring.datajpa.repository.MachineRepository;
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


    @PostMapping("/machine")
    public ResponseEntity<Machine> createMachine(@RequestBody Machine machine) {
        try {
            Machine _machine = machineRepository
                    .save(new Machine(machine.getLocation(),false,false,machine.getCurrent_user()));
            return new ResponseEntity<>(_machine, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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
            if(machine.getCurrent_user()!=null ){
                _machine.setLocation(machine.getLocation());
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