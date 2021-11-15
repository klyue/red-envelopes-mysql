package com.group6.redenvelopesmysql.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Yang Xichun
 * @date 2021/11/12 22:32
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "wallet")
public class Wallet {

    /**
     * 钱包所属的用户的 ID
     */
    @Id
    @Column(name = "uid")
    private int uid;

    /**
     * 钱包余额
     */
    @Column(name = "balance")
    private int balance;

}
