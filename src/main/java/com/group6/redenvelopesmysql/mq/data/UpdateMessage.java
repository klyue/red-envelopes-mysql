package com.group6.redenvelopesmysql.mq.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Yang Xichun
 * @date 2021/11/14 16:23
 */
@Getter
@Setter
@ToString
public class UpdateMessage {

    /**
     * eid
     */
    private int e;

    /**
     * uid
     */
    private int u;

    /**
     * value
     */
    private int v;

}
