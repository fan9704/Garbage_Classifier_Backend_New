package com.bezkoder.spring.datajpa.service.TransferMoney;

import com.bezkoder.spring.datajpa.dto.Transfer_money_recordDTO;
import com.bezkoder.spring.datajpa.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public class CheckTransferCashState extends TransferState {

    @Autowired
    WalletRepository walletRepository;

    CheckTransferCashState(TransferMoneyRecordService transferMoneyRecordService){
        super(transferMoneyRecordService);
    }
    
    @Override
    public void handle(Transfer_money_recordDTO transfer_money_recordDTO) {
        BigDecimal currentCash = walletRepository.getCurrentCash((int) transfer_money_recordDTO.getReceiver());
        if (currentCash.compareTo(transfer_money_recordDTO.getAmount()) >= 0) {
            transferMoneyRecordService.setTransferState(new TransferingMoneyState(transferMoneyRecordService));
            transferMoneyRecordService.handleTransfer(transfer_money_recordDTO);

        }
    }
    
    @Override
    public ResponseEntity getResponseEntity() {
        return  new ResponseEntity<>("Wallet has not enough cash",HttpStatus.BAD_REQUEST);
    }
}
