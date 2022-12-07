package com.bezkoder.spring.datajpa.service.TransferMoney;

import com.bezkoder.spring.datajpa.dto.Transfer_money_recordDTO;
import com.bezkoder.spring.datajpa.model.Transfer_money_record;
import com.bezkoder.spring.datajpa.model.User;
import com.bezkoder.spring.datajpa.model.Wallet;
import com.bezkoder.spring.datajpa.repository.TransferMoneyRecordRepository;
import com.bezkoder.spring.datajpa.repository.UserRepository;
import com.bezkoder.spring.datajpa.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public class TransferingMoneyState extends TransferState{

    @Autowired
    TransferMoneyRecordRepository transferMoneyRecordRepository;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    UserRepository userRepository;


    TransferingMoneyState(TransferMoneyRecordService transferMoneyRecordService){
        super(transferMoneyRecordService);
    }

    @Override
    void handle(Transfer_money_recordDTO transfer_money_recordDTO) {
        try {
            User user =userRepository.findById(transfer_money_recordDTO.getReceiver()).get();

            Transfer_money_record _garbage_record = transferMoneyRecordRepository
                    .save(new Transfer_money_record(user,transfer_money_recordDTO.getAmount(),transfer_money_recordDTO.getBank_name()));
            walletRepository.save(new Wallet(transfer_money_recordDTO.getAmount().multiply(new BigDecimal(-1)) ,"Transfer to bank",user));

            transferMoneyRecordService.setTransferState(new TransferSuccessState(transferMoneyRecordService,_garbage_record));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    ResponseEntity getResponseEntity() {
        return new ResponseEntity("Unexpect error happen when transfer money", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
