package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Bank_acct;
import com.bezkoder.spring.datajpa.repository.Bank_acctRepository;
import com.bezkoder.spring.datajpa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Bank_acctController {

    @Autowired
    private Bank_acctService bank_acctService;
    @Autowired
    private Bank_acctRepository bank_acctRepository;
    @GetMapping("/bank_accts")
    public List<Bank_acct> allBank_accts() {

        return bank_acctService.findAll();
    }

    @GetMapping("/bank_acct/{id}")
    public ResponseEntity<Bank_acct> getBank_acctById(@PathVariable("id") long id) {
        Optional<Bank_acct> bank_acctData = bank_acctRepository.findById(id);

        if ( bank_acctData.isPresent()) {
            return new ResponseEntity<>( bank_acctData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/bank_acct")
    public ResponseEntity<Bank_acct> createBank_acct(@RequestBody Bank_acct bank_acct) {
        try {
            Bank_acct _bank_acct = bank_acctRepository
                    .save(new Bank_acct(bank_acct.getBank_type(), bank_acct.getAccount_code()));
            return new ResponseEntity<>(_bank_acct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/bank_acct/{id}")
    public ResponseEntity<Bank_acct> patchBank_type(@PathVariable("id") long id, @RequestBody Bank_acct bank_acct) {
        Optional<Bank_acct> bank_acctData = bank_acctRepository.findById(id);

        if (bank_acctData.isPresent()) {
            System.out.println(bank_acct.getBank_type());//Check Variable
            System.out.println(bank_acct.getAccount_code());
            Bank_acct _bank_acct = bank_acctData.get();
            if(bank_acct.getBank_type()!=null ){
                _bank_acct.setBank_type(bank_acct.getBank_type());
            }
            if(bank_acct.getAccount_code()!=0 ){
                _bank_acct.setAccount_code(bank_acct.getAccount_code());
            }
            return new ResponseEntity<>(bank_acctRepository.save(_bank_acct), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/bank_acct/{id}")
    public ResponseEntity<Bank_acct> updateBank_type(@PathVariable("id") long id, @RequestBody Bank_acct bank_acct) {
        Optional<Bank_acct> bank_acctData = bank_acctRepository.findById(id);

        if (bank_acctData.isPresent()) {
            System.out.println(bank_acct.getBank_type());//Check Variable
            System.out.println(bank_acct.getAccount_code());
            Bank_acct _bank_acct = bank_acctData.get();
            _bank_acct.setBank_type(bank_acct.getBank_type());
            _bank_acct.setAccount_code(bank_acct.getAccount_code());
            return new ResponseEntity<>(bank_acctRepository.save(_bank_acct), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/bank_acct/{id}")
    public void delete(@PathVariable String id) {
        bank_acctService.deleteById(Long.parseLong(id));
    }
}