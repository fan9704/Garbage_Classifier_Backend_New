package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Garbage_record;
import com.bezkoder.spring.datajpa.repository.GarbageRecordRepository;
import com.bezkoder.spring.datajpa.service.GarbageRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GarbageRecordController {

    @Autowired
    private GarbageRecordService garbage_recordService;
    @Autowired
    private GarbageRecordRepository garbage_recordRepository;
    @GetMapping("/garbage_records")
    public List<Garbage_record> allGarbage_records() {

        return garbage_recordService.findAll();
    }

    @GetMapping("/garbage_record/{id}")
    public ResponseEntity<Garbage_record> getGarbage_recordById(@PathVariable("id") long id) {
        Optional<Garbage_record> garbage_recordData = garbage_recordRepository.findById(id);

        if ( garbage_recordData.isPresent()) {
            return new ResponseEntity<>( garbage_recordData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/garbage_record")
    public ResponseEntity<Garbage_record> createGarbage_record(@RequestBody Garbage_record garbage_record) {
        try {
            Garbage_record _garbage_record = garbage_recordRepository
                    .save(new Garbage_record(garbage_record.getGarbage_type(), garbage_record.getWeight(),garbage_record.getUser(),garbage_record.getTime_stamp(),garbage_record.getMachine_id()));
            return new ResponseEntity<>(_garbage_record, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/garbage_record/{id}")
    public ResponseEntity<Garbage_record> patchGarbage_record(@PathVariable("id") long id, @RequestBody Garbage_record garbage_record) {
        Optional<Garbage_record> garbage_recordData = garbage_recordRepository.findById(id);

        if (garbage_recordData.isPresent()) {
            System.out.println(garbage_record.getGarbage_type());//Check Variable
            System.out.println(garbage_record.getWeight());
            System.out.println(garbage_record.getUser());
            System.out.println(garbage_record.getTime_stamp());
            System.out.println(garbage_record.getMachine_id());
            Garbage_record _garbage_record = garbage_recordData.get();
            if(garbage_record.getGarbage_type()!=null){
                _garbage_record.setGarbage_type(garbage_record.getGarbage_type());
            }
            if(garbage_record.getWeight()!=0 ){
                _garbage_record.setWeight(garbage_record.getWeight());
            }
            return new ResponseEntity<>(garbage_recordRepository.save(_garbage_record), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/garbage_record/{id}")
    public ResponseEntity<Garbage_record> updateGarbage_record(@PathVariable("id") long id, @RequestBody Garbage_record garbage_record) {
        Optional<Garbage_record> garbage_recordData = garbage_recordRepository.findById(id);

        if (garbage_recordData.isPresent()) {
            System.out.println(garbage_record.getGarbage_type());//Check Variable
            System.out.println(garbage_record.getWeight());
            System.out.println(garbage_record.getUser());
            System.out.println(garbage_record.getTime_stamp());
            System.out.println(garbage_record.getMachine_id());
            Garbage_record _garbage_record = garbage_recordData.get();
            _garbage_record.setGarbage_type(garbage_record.getGarbage_type());
            _garbage_record.setWeight(garbage_record.getWeight());
            _garbage_record.setUser(garbage_record.getUser());
            _garbage_record.setTime_stamp(garbage_record.getTime_stamp());
            _garbage_record.setMachine_id(garbage_record.getMachine_id());

            return new ResponseEntity<>(garbage_recordRepository.save(_garbage_record), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/garbage_record/{id}")
    public void delete(@PathVariable String id) {
        garbage_recordService.deleteById(Long.parseLong(id));
    }
}