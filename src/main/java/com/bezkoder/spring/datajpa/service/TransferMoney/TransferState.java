package com.bezkoder.spring.datajpa.service.TransferMoney;

import com.bezkoder.spring.datajpa.dto.Transfer_money_recordDTO;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import javax.transaction.NotSupportedException;

@Setter
public abstract class TransferState {

    TransferMoneyRecordService transferMoneyRecordService;
    // Todo: revise method name
    abstract void handle(Transfer_money_recordDTO transfer_money_recordDTO) throws NotSupportedException;
    abstract ResponseEntity getResponseEntity();




}
