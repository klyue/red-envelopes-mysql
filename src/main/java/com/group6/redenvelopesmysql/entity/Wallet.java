package com.group6.redenvelopesmysql.entity;

import javax.persistence.*;

/**
 * @author Yang Xichun
 * @date 2021/11/12 22:32
 */
@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @Column(name = "uid")
    private int uid;

    @Column(name = "balance")
    private int balance;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", balance=" + balance +
                '}';
    }
}
