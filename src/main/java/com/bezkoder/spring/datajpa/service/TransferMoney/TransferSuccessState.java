package com.bezkoder.spring.datajpa.service.TransferMoney;

import com.bezkoder.spring.datajpa.dto.Transfer_money_recordDTO;
import com.bezkoder.spring.datajpa.model.Transfer_money_record;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.NotSupportedException;

public class TransferSuccessState extends TransferState{

    Transfer_money_record transfer_money_record;

    TransferSuccessState(TransferMoneyRecordService transferMoneyRecordService, Transfer_money_record transfer_money_record) {
        super(transferMoneyRecordService);
        this.transfer_money_record = transfer_money_record;
    }

    @Override
    void handle(Transfer_money_recordDTO transfer_money_recordDTO) throws NotSupportedException {
        throw  new NotSupportedException("Transfer Finished");
    }

    @Override
    ResponseEntity getResponseEntity() {
        return  new ResponseEntity<>(transfer_money_record, HttpStatus.CREATED);
    }
}
