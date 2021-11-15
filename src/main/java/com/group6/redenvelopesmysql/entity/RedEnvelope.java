package com.group6.redenvelopesmysql.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Yang Xichun
 * @date 2021/11/12 22:35
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "red_envelope")
public class RedEnvelope {

    /**
     * 红包 ID
     */
    @Id
    @Column(name = "eid")
    private int eid;

    /**
     * 用户 ID
     */
    @Column(name = "uid")
    private int uid;

    /**
     * 红包金额
     */
    @Column(name = "value")
    private int value;

    /**
     * 红包是否已打开
     */
    @Column(name = "opened")
    private boolean opened;

    /**
     * 用户抢到红包的时间
     */
    @Column(name = "snatch_time")
    private long snatchTime;

}
