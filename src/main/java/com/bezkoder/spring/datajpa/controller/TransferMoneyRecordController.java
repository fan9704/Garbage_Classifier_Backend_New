package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Transfer_money_record;
import com.bezkoder.spring.datajpa.repository.TransferMoneyRecordRepository;
import com.bezkoder.spring.datajpa.service.TransferMoneyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransferMoneyRecordController {

    @Autowired
    private TransferMoneyRecordService transfer_money_recordService;
    @Autowired
    private TransferMoneyRecordRepository transfer_money_recordRepository;
    @GetMapping("/transfer_money_records")
    public List<Transfer_money_record> allTransfer_money_records() {

        return transfer_money_recordService.findAll();
    }

    @GetMapping("/transfer_money_record/{id}")
    public ResponseEntity<Transfer_money_record> getTransfer_money_recordById(@PathVariable("id") long id) {
        Optional<Transfer_money_record> garbage_recordData = transfer_money_recordRepository.findById(id);

        if ( garbage_recordData.isPresent()) {
            return new ResponseEntity<>( garbage_recordData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/transfer_money_record")
    public ResponseEntity<Transfer_money_record> createTransfer_money_record(@RequestBody Transfer_money_record transfer_money_record) {
        try {
            Transfer_money_record _garbage_record = transfer_money_recordRepository
                    .save(new Transfer_money_record(transfer_money_record.getReceiver(),transfer_money_record.getAmount(),transfer_money_record.getTime_stamp(),transfer_money_record.getBank_name()));
            return new ResponseEntity<>(_garbage_record, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/transfer_money_record/{id}")
    public ResponseEntity<Transfer_money_record> patchTransfer_money_record(@PathVariable("id") long id, @RequestBody Transfer_money_record transfer_money_record) {
        Optional<Transfer_money_record> transfer_money_recordData = transfer_money_recordRepository.findById(id);

        if (transfer_money_recordData.isPresent()) {
            System.out.println(transfer_money_record.getReceiver());//Check Variable
            System.out.println(transfer_money_record.getAmount());
            System.out.println(transfer_money_record.getTime_stamp());
            System.out.println(transfer_money_record.getBank_name());
            Transfer_money_record _transfer_money_record = transfer_money_recordData.get();
            if(transfer_money_record.getReceiver()!=null){
                _transfer_money_record.setReceiver(transfer_money_record.getReceiver());
            }
            if(transfer_money_record.getAmount().compareTo(BigDecimal.ZERO) != 0  ){
                _transfer_money_record.setAmount(transfer_money_record.getAmount());
            }
            return new ResponseEntity<>(transfer_money_recordRepository.save(_transfer_money_record), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/transfer_money_record/{id}")
    public ResponseEntity<Transfer_money_record> updateTransfer_money_record(@PathVariable("id") long id, @RequestBody Transfer_money_record transfer_money_record) {
        Optional<Transfer_money_record> transfer_money_recordData = transfer_money_recordRepository.findById(id);

        if (transfer_money_recordData.isPresent()) {
            System.out.println(transfer_money_record.getReceiver());//Check Variable
            System.out.println(transfer_money_record.getAmount());
            System.out.println(transfer_money_record.getTime_stamp());
            System.out.println(transfer_money_record.getBank_name());
            Transfer_money_record _transfer_money_record = transfer_money_recordData.get();
            _transfer_money_record.setReceiver(transfer_money_record.getReceiver());
            _transfer_money_record.setAmount(transfer_money_record.getAmount());
            _transfer_money_record.setTime_stamp(transfer_money_record.getTime_stamp());
            _transfer_money_record.setBank_name(transfer_money_record.getBank_name());

            return new ResponseEntity<>(transfer_money_recordRepository.save(_transfer_money_record), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/transfer_money_record/{id}")
    public void delete(@PathVariable String id) {
        transfer_money_recordService.deleteById(Long.parseLong(id));
    }
}