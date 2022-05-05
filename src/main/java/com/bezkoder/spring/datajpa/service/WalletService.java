package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.model.User;
import com.bezkoder.spring.datajpa.model.Wallet;
import com.bezkoder.spring.datajpa.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

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