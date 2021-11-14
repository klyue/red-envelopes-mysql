package com.group6.redenvelopesmysql.repository;

import com.group6.redenvelopesmysql.entity.Wallet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yang Xichun
 * @date 2021/11/12 22:49
 */
public interface WalletRepository extends CrudRepository<Wallet, Integer> {

//    @Transactional
//    @Modifying
//    @Query("update Wallet w set w.balance = w.balance + ?1 where w.uid = ?2")
//    int updateBalance(int newBalance, int uid);

    Wallet findByUid(int uid);
}
