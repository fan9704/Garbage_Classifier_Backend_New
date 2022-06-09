package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Bank_acct;
import com.bezkoder.spring.datajpa.dto.Bank_acctDTO;
import com.bezkoder.spring.datajpa.model.Bank_type;
import com.bezkoder.spring.datajpa.model.User;
import com.bezkoder.spring.datajpa.repository.BankAcctRepository;
import com.bezkoder.spring.datajpa.repository.BankTypeRepository;
import com.bezkoder.spring.datajpa.repository.UserRepository;
import com.bezkoder.spring.datajpa.service.BankAcctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BankAcctController {

    @Autowired
    private BankAcctService bank_acctService;
    @GetMapping("/bank_accts")
    public List<Bank_acct> allBank_accts() {

        return bank_acctService.findAll();
    }
    @GetMapping("/back_acct/username/{username}")
    public ResponseEntity<Bank_acct> getBank_acctByUsername(@PathVariable("username") String username) {
        return bank_acctService.getBank_acctByUsername(username);
    }
    @GetMapping("/bank_acct/{id}")
    public ResponseEntity<Bank_acct> getBank_acctById(@PathVariable("id") long id) {
        return bank_acctService.getBank_acctById(id);
    }
    @PostMapping("/bank_acct")
    public ResponseEntity<Bank_acct> createBank_acct(@RequestBody Bank_acctDTO bank_acctDTO) {
        return bank_acctService.createBank_acct(bank_acctDTO);
    }
    @PatchMapping("/bank_acct/{id}")
    public ResponseEntity<Bank_acct> patchBank_type(@PathVariable("id") long id, @RequestBody Bank_acct bank_acct) {
        return bank_acctService.patchBank_type(id,bank_acct);
    }
    @PatchMapping("/bank_acct/user/")
    public ResponseEntity<Bank_acct> patchUserBankAcct(@RequestBody Bank_acctDTO bank_acctDTO) {
        return bank_acctService.patchUserBankAcct(bank_acctDTO);
    }
    @PutMapping("/bank_acct/{id}")
    public ResponseEntity<Bank_acct> updateBank_type(@PathVariable("id") long id, @RequestBody Bank_acct bank_acct) {
        return bank_acctService.updateBank_type(id,bank_acct);
    }
    @DeleteMapping("/bank_acct/{id}")
    public void delete(@PathVariable String id) {
        bank_acctService.deleteById(Long.parseLong(id));
    }
}