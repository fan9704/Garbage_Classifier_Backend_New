package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Garbage_type;
import com.bezkoder.spring.datajpa.repository.GarbageTypeRepository;
import com.bezkoder.spring.datajpa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GarbageTypeController {

    @Autowired
    private GarbageTypeService garbage_typeService;
    @Autowired
    private GarbageTypeRepository garbage_typeRepository;
    @GetMapping("/garbage_types")
    public List<Garbage_type> allGarbage_types() {

        return garbage_typeService.findAll();
    }

    @GetMapping("/garbage_type/{id}")
    public ResponseEntity<Garbage_type> getGarbage_typeById(@PathVariable("id") long id) {
        Optional<Garbage_type> garbage_typeData = garbage_typeRepository.findById(id);

        if ( garbage_typeData.isPresent()) {
            return new ResponseEntity<>( garbage_typeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/garbage_type")
    public ResponseEntity<Garbage_type> createGarbage_type(@RequestBody Garbage_type garbage_type) {
        try {
            Garbage_type _garbage_type = garbage_typeRepository
                    .save(new Garbage_type(garbage_type.getType_name(), garbage_type.getUnit_price()));
            return new ResponseEntity<>(_garbage_type, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/garbage_type/{id}")
    public ResponseEntity<Garbage_type> patchGarbage_type(@PathVariable("id") long id, @RequestBody Garbage_type garbage_type) {
        Optional<Garbage_type> garbage_typeData = garbage_typeRepository.findById(id);

        if (garbage_typeData.isPresent()) {
            System.out.println(garbage_type.getType_name()+garbage_type.getUnit_price());//Check Variable
            Garbage_type _garbage_type = garbage_typeData.get();
            if(garbage_type.getType_name()!=null || garbage_type.getType_name()!= ""){
                _garbage_type.setType_name(garbage_type.getType_name());
            }
            if(garbage_type.getUnit_price()!=0 ){
                _garbage_type.setUnit_price(garbage_type.getUnit_price());
            }
            return new ResponseEntity<>(garbage_typeRepository.save(_garbage_type), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/garbage_type/{id}")
    public ResponseEntity<Garbage_type> updateGarbage_type(@PathVariable("id") long id, @RequestBody Garbage_type garbage_type) {
        Optional<Garbage_type> garbage_typeData = garbage_typeRepository.findById(id);

        if (garbage_typeData.isPresent()) {
            System.out.println(garbage_type.getType_name()+garbage_type.getType_name());//Check Variable
            Garbage_type _garbage_type = garbage_typeData.get();
            _garbage_type.setType_name(garbage_type.getType_name());
            _garbage_type.setUnit_price(garbage_type.getUnit_price());
            return new ResponseEntity<>(garbage_typeRepository.save(_garbage_type), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/garbage_type/{id}")
    public void delete(@PathVariable String id) {
        garbage_typeService.deleteById(Long.parseLong(id));
    }
}