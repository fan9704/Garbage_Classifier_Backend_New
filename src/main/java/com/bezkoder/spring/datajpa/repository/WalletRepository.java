package com.bezkoder.spring.datajpa.repository;
import com.bezkoder.spring.datajpa.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Query(value="select sum(change_value) from Wallet where user_id = :userId", nativeQuery=true)
    BigDecimal getCurrentCash(int userId);
}