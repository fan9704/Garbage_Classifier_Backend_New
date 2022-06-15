package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.dto.WalletDTO;
import com.bezkoder.spring.datajpa.model.User;
import com.bezkoder.spring.datajpa.model.Wallet;
import com.bezkoder.spring.datajpa.repository.UserRepository;
import com.bezkoder.spring.datajpa.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private UserRepository userRepository;
    public ResponseEntity<HashMap<String, BigDecimal>> getCash(int id) {
        HashMap<String, BigDecimal> res = new HashMap<>();
        BigDecimal cash = walletRepository.getCurrentCash(id);
        if (cash == null) {
            cash = new BigDecimal("0");
        }
        res.put("cash", cash);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    public ResponseEntity<List<Wallet>> getWalletRecordByUsername(String username){
        try{
            User userData = userRepository.findByUserName(username);
            List<Wallet> wallets =walletRepository.findAllByUserid(userData.getId());
            return new ResponseEntity<>(wallets, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<BigDecimal> getWalletValueByUsername(String username){
        try{
            User userData = userRepository.findByUserName(username);
            BigDecimal wallets =walletRepository.getCurrentCash((int)userData.getId());
            return new ResponseEntity<>(wallets, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<Wallet>> createWalletRecord(WalletDTO walletDTO){
        try{
            User userData = userRepository.findByUserName(walletDTO.getUsername());
            walletRepository.save(new Wallet(walletDTO.getChange_value(),walletDTO.getDescription(),userData));
            return new ResponseEntity<>(walletRepository.findAllByUserid(userData.getId()), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Utils Layer
    public List<Wallet> findAll() {


        List<Wallet> garbage_types = new ArrayList<Wallet>();
        walletRepository.findAll().forEach(e -> garbage_types.add(e));

        return garbage_types;
    }
    public List<Wallet> findAllByUserid(long userId){
        List<Wallet> wallets = new ArrayList<Wallet>();
        walletRepository.findAllByUserid(userId).forEach(e -> wallets.add(e));
       return  wallets;
    }

    public Long count() {

        return walletRepository.count();
    }

    public void deleteById(Long userId) {

        walletRepository.deleteById(userId);
    }
}