package com.group6.redenvelopesmysql.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Yang Xichun
 * @date 2021/11/12 22:35
 */
@Entity
@Table(name = "envelope")
public class RedEnvelope {
    @Id
    @Column(name = "eid")
    private int eid;

    @Column(name = "uid")
    private int uid;

    @Column(name = "value")
    private int value;

    @Column(name = "opened")
    private boolean opened;

    @Column(name = "snatch_time")
    private long snatchTime;

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public long getSnatchTime() {
        return snatchTime;
    }

    public void setSnatchTime(long snatchTime) {
        this.snatchTime = snatchTime;
    }

    @Override
    public String toString() {
        return "Envelope{" +
                "eid=" + eid +
                ", uid=" + uid +
                ", value=" + value +
                ", opened=" + opened +
                ", snatchTime=" + snatchTime +
                '}';
    }

}
