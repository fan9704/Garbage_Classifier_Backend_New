package com.bezkoder.spring.datajpa.service.TransferMoney;

import com.bezkoder.spring.datajpa.dto.Transfer_money_recordDTO;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import javax.transaction.NotSupportedException;

public abstract class TransferState {
    // Todo: revise method name
    abstract void handle(Transfer_money_recordDTO transfer_money_recordDTO,TransferMoneyRecordService transferMoneyRecordService) throws NotSupportedException;
    abstract ResponseEntity getResponseEntity();




}
