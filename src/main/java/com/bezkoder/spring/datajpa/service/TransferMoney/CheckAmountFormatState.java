package com.bezkoder.spring.datajpa.service.TransferMoney;

import com.bezkoder.spring.datajpa.dto.Transfer_money_recordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.transaction.NotSupportedException;

@Component
@Qualifier("CheckAmountFormatState")
public class CheckAmountFormatState extends TransferState  {

    @Autowired
    CheckTransferCashState checkTransferCashState;
    @Override
    void handle(Transfer_money_recordDTO transfer_money_recordDTO, TransferMoneyRecordService transferMoneyRecordService) throws NotSupportedException {
        if (transfer_money_recordDTO.getAmount().intValue() > 0){
            transferMoneyRecordService.setTransferState(checkTransferCashState);
            transferMoneyRecordService.handleTransfer(transfer_money_recordDTO);
        }
    }

    @Override
    ResponseEntity getResponseEntity() {
        return  new ResponseEntity<>("Can not transfer less than or equal to zero", HttpStatus.BAD_REQUEST);
    }
}
