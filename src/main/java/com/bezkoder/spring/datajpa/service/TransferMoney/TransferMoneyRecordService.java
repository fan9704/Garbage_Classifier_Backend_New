package com.bezkoder.spring.datajpa.service.TransferMoney;

import com.bezkoder.spring.datajpa.dto.Transfer_money_recordDTO;
import com.bezkoder.spring.datajpa.model.Transfer_money_record;
import com.bezkoder.spring.datajpa.model.User;
import com.bezkoder.spring.datajpa.repository.TransferMoneyRecordRepository;
import com.bezkoder.spring.datajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service

public class TransferMoneyRecordService {

    @Autowired
    private TransferMoneyRecordRepository transferMoneyRecordRepository;
    @Autowired
    private UserRepository userRepository;

    private TransferState transferState = new CheckTransferCashState(this);

    public ResponseEntity<Transfer_money_record> getTransfer_money_recordById(long id) {
        Optional<Transfer_money_record> garbage_recordData = transferMoneyRecordRepository.findById(id);
        if ( garbage_recordData.isPresent()) {
            return new ResponseEntity<>( garbage_recordData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity createTransfer_money_record(Transfer_money_recordDTO transfer_money_recordDTO) {
        try {
            handleTransfer(transfer_money_recordDTO);
            return transferState.getResponseEntity();
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Transfer_money_record> patchTransfer_money_record(long id,Transfer_money_recordDTO transfer_money_recordDTO) {
        Optional<Transfer_money_record> transfer_money_recordData = transferMoneyRecordRepository.findById(id);
        Optional<User> userOptional =userRepository.findById(transfer_money_recordDTO.getReceiver());
        if (!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (transfer_money_recordData.isPresent()) {
            Transfer_money_record _transfer_money_record = transfer_money_recordData.get();
            _transfer_money_record.setReceiver(userOptional.get());
            if(transfer_money_recordDTO.getAmount().compareTo(BigDecimal.ZERO) >= 0  ){
                _transfer_money_record.setAmount(transfer_money_recordDTO.getAmount());
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            if(transfer_money_recordDTO.getBank_name()!=""){
                _transfer_money_record.setBank_name(transfer_money_recordDTO.getBank_name());
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(transferMoneyRecordRepository.save(_transfer_money_record), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    public ResponseEntity<Transfer_money_record> updateTransfer_money_record(long id,Transfer_money_recordDTO transfer_money_recordDTO) {
        Optional<Transfer_money_record> transfer_money_recordData = transferMoneyRecordRepository.findById(id);
        User user =userRepository.findById(transfer_money_recordDTO.getReceiver()).get();
        if (transfer_money_recordData.isPresent()) {
            Transfer_money_record _transfer_money_record = transfer_money_recordData.get();
            _transfer_money_record.setReceiver(user);
            _transfer_money_record.setAmount(transfer_money_recordDTO.getAmount());
            _transfer_money_record.setBank_name(transfer_money_recordDTO.getBank_name());

            return new ResponseEntity<>(transferMoneyRecordRepository.save(_transfer_money_record), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    //Utils Layer
    public List<Transfer_money_record> findAll() {

        List<Transfer_money_record> garbage_types = new ArrayList<Transfer_money_record>();
        transferMoneyRecordRepository.findAll().forEach(e -> garbage_types.add(e));

        return garbage_types;
    }

    public Long count() {

        return transferMoneyRecordRepository.count();
    }

    public void deleteById(Long userId) {

        transferMoneyRecordRepository.deleteById(userId);
    }

    public void setTransferState(TransferState transferState) {
        this.transferState = transferState;
    }

    //Todo:revise method name
    void handleTransfer(Transfer_money_recordDTO transfer_money_recordDTO){
        try {
            transferState.handle(transfer_money_recordDTO);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    //    public void saveRecord(User user, BigDecimal amount){
//        String accountCode = user.getBank_acct().getAccount_code();
//        transferMoneyRecordRepository.save(new Transfer_money_record(user,amount,accountCode));
//    }
}
