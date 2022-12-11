package com.bezkoder.spring.datajpa.service.TransferMoney;

import com.bezkoder.spring.datajpa.dto.Transfer_money_recordDTO;
import com.bezkoder.spring.datajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Qualifier("CheckUserExistState")
public class CheckUserExistState extends TransferState {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CheckAmountFormatState checkAmountFormatState;



    @Override
    void handle(Transfer_money_recordDTO transfer_money_recordDTO,TransferMoneyRecordService transferMoneyRecordService) {

        if (userRepository.existsById(transfer_money_recordDTO.getReceiver())){
            transferMoneyRecordService.setTransferState(checkAmountFormatState);
            transferMoneyRecordService.handleTransfer(transfer_money_recordDTO);
        }
    }

    @Override
    ResponseEntity getResponseEntity() {
        return new ResponseEntity("user not found", HttpStatus.NOT_FOUND);

    }
}
