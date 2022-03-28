package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Bank_type;
import com.bezkoder.spring.datajpa.repository.Bank_typeRepository;
import com.bezkoder.spring.datajpa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Bank_typeController {

    @Autowired
    private Bank_typeService bank_typeService;
    @Autowired
    private Bank_typeRepository bank_typeRepository;
    @GetMapping("/bank_types")
    public List<Bank_type> allBank_types() {

        return bank_typeService.findAll();
    }

    @GetMapping("/bank_type/{id}")
    public ResponseEntity<Bank_type> getBank_typeById(@PathVariable("id") long id) {
        Optional<Bank_type> bank_typeData = bank_typeRepository.findById(id);

        if ( bank_typeData.isPresent()) {
            return new ResponseEntity<>( bank_typeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/bank_type")
    public ResponseEntity<Bank_type> createBank_type(@RequestBody Bank_type bank_type) {
        try {
            Bank_type _bank_type = bank_typeRepository
                    .save(new Bank_type(bank_type.getBank_name(), bank_type.getBank_code()));
            return new ResponseEntity<>(_bank_type, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/bank_type/{id}")
    public ResponseEntity<Bank_type> patchBank_type(@PathVariable("id") long id, @RequestBody Bank_type bank_type) {
        Optional<Bank_type> bank_typeData = bank_typeRepository.findById(id);

        if (bank_typeData.isPresent()) {
            System.out.println(bank_type.getBank_name()+bank_type.getBank_code());//Check Variable
            Bank_type _bank_type = bank_typeData.get();
            if(bank_type.getBank_name()!=null || bank_type.getBank_name()!= ""){
                _bank_type.setBank_name(bank_type.getBank_name());
            }
            if(bank_type.getBank_code()!=0 ){
                _bank_type.setBank_code(bank_type.getBank_code());
            }
            return new ResponseEntity<>(bank_typeRepository.save(_bank_type), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/bank_type/{id}")
    public ResponseEntity<Bank_type> updateBank_type(@PathVariable("id") long id, @RequestBody Bank_type bank_type) {
        Optional<Bank_type> bank_typeData = bank_typeRepository.findById(id);

        if (bank_typeData.isPresent()) {
            System.out.println(bank_type.getBank_name()+bank_type.getBank_code());//Check Variable
            Bank_type _bank_type = bank_typeData.get();
                _bank_type.setBank_name(bank_type.getBank_name());
                _bank_type.setBank_code(bank_type.getBank_code());
            return new ResponseEntity<>(bank_typeRepository.save(_bank_type), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/bank_type/{id}")
    public void delete(@PathVariable String id) {
        bank_typeService.deleteById(Long.parseLong(id));
    }
}