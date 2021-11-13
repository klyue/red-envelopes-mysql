package com.group6.redenvelopesmysql.runner;

import org.springframework.boot.CommandLineRunner;

/**
 * @author Yang Xichun
 * @date 2021/11/12 22:55
 */
public class MQRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // TODO 消费消息队列中的消息并写入数据，写入失败则删除 Redis 缓存中的相应数据
    }
}
