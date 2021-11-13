package com.group6.redenvelopesmysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class RedEnvelopesMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedEnvelopesMysqlApplication.class, args);
    }

}
