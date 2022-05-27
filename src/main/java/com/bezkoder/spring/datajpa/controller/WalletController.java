package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.User;
import com.bezkoder.spring.datajpa.model.Wallet;
import com.bezkoder.spring.datajpa.dto.WalletDTO;
import com.bezkoder.spring.datajpa.repository.UserRepository;
import com.bezkoder.spring.datajpa.repository.WalletRepository;
import com.bezkoder.spring.datajpa.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class WalletController {
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private WalletService walletService;
    @Autowired
    private UserRepository userRepository;
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
    @GetMapping("/walletInfo/{username}")
    public ResponseEntity<List<Wallet>> getWalletRecordByUsername(@PathVariable("username") String username){
        try{
            User userData = userRepository.findByUserName(username);
            List<Wallet> wallets =walletRepository.findAllByUserid(userData.getId());
            return new ResponseEntity<>(wallets, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/walletValue/{username}")
    public ResponseEntity<BigDecimal> getWalletValueByUsername(@PathVariable("username") String username){
        try{
            User userData = userRepository.findByUserName(username);
            BigDecimal wallets =walletRepository.getCurrentCash((int)userData.getId());
            return new ResponseEntity<>(wallets, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/wallet")
    public ResponseEntity<List<Wallet>> createWalletRecord(@RequestBody WalletDTO walletDTO){
        try{
            User userData = userRepository.findByUserName(walletDTO.getUsername());
            walletRepository.save(new Wallet(walletDTO.getChange_value(),walletDTO.getDescription(),userData));
            return new ResponseEntity<>(walletRepository.findAllByUserid(userData.getId()), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
