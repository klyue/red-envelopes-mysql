package com.group6.redenvelopesmysql.mq.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * insert 消息对象
 *
 * @author Yang Xichun
 * @date 2021/11/14 16:15
 */
@Getter
@Setter
@ToString
public class InsertMessage {

    /**
     * eid，红包 ID
     */
    private int e;

    /**
     * uid，用户 ID
     */
    private int u;

    /**
     * value，红包金额
     */
    private int v;

    /**
     * snatch_time，用户抢到红包的时间
     */
    private long t;

}
