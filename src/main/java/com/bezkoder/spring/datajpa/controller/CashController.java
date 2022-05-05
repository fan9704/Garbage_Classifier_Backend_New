package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.CashDTO;
import com.bezkoder.spring.datajpa.model.User;
import com.bezkoder.spring.datajpa.model.Wallet;
import com.bezkoder.spring.datajpa.model.Bank_acct;
import com.bezkoder.spring.datajpa.repository.TransferMoneyRecordRepository;
import com.bezkoder.spring.datajpa.repository.UserRepository;
import com.bezkoder.spring.datajpa.repository.WalletRepository;
import com.bezkoder.spring.datajpa.repository.BankAcctRepository;
import com.bezkoder.spring.datajpa.service.TransferMoneyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CashController {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransferMoneyRecordService transferMoneyRecordService;
    @Autowired
    BankAcctRepository bankAcctRepository;

    @GetMapping("/cash/{id}")
    public ResponseEntity<HashMap<String, BigDecimal>> getCash(@PathVariable("id") int id) {
        HashMap<String, BigDecimal> res = new HashMap<>();
        BigDecimal cash = walletRepository.getCurrentCash(id);
        if (cash == null) {
            cash = new BigDecimal("0");
        }
        res.put("cash", cash);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/cash")
    public ResponseEntity transferMoney(@RequestBody CashDTO CashDTO) {
        long userId = CashDTO.getUserId();
        Optional<User> findUser = userRepository.findById( userId);
        if (!findUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = findUser.get();
        BigDecimal receiveCash = new BigDecimal(CashDTO.getCash());
        BigDecimal currentCash = walletRepository.getCurrentCash((int)userId);
        Bank_acct acct = bankAcctRepository.findOneByUser(user);
        if (
                currentCash.compareTo(receiveCash) == -1
                        || receiveCash.compareTo(BigDecimal.ZERO) != 1
                        || acct == null
        ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BigDecimal transfer = receiveCash.multiply(new BigDecimal("-1"));
        String description = String.format("transfer %s dollar", receiveCash.toString());
        transferMoneyRecordService.saveRecord(user, receiveCash);
        walletRepository.save(new Wallet(transfer, description, user));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}