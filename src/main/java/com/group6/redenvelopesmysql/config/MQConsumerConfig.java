package com.group6.redenvelopesmysql.config;

import com.group6.redenvelopesmysql.runner.MQConsumerMSgListenerProcessor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Yang Xichun
 * @date 2021/11/13 23:23
 */
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "rocketmq.consumer")
@Slf4j
public class MQConsumerConfig {
    private String groupName;
    private String namesrvAddr;
    private String topics;
    // 消费者线程数据量
    private Integer consumeThreadMin;
    private Integer consumeThreadMax;
    private Integer consumeMessageBatchMaxSize;

    @Autowired
    private MQConsumerMSgListenerProcessor consumerMSgListenerProcessor;

    @Bean
    @ConditionalOnProperty(prefix = "rocketmq.consumer", value = "isOnOff", havingValue = "on")
    public DefaultMQPushConsumer defaultConsumer() throws MQClientException {
        log.info("defaultConsumer 正在创建------------------------------------------");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        consumer.registerMessageListener(consumerMSgListenerProcessor);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        try {
            String[] topicArr = topics.split(";");
            for (String topic : topicArr) {
                String[] tagArr = topic.split("~");
                consumer.subscribe(tagArr[0], tagArr[1]);
            }
            consumer.start();
            log.info("defaultConsumer 创建成功, groupName={}, topics={}, namesrvAddr={}",groupName, topics, namesrvAddr);
        } catch (MQClientException e) {
            log.error("defaultConsumer 创建失败！")；
        }

        return consumer;
    }

}
