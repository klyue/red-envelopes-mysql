package com.group6.redenvelopesmysql.config;

import com.group6.redenvelopesmysql.mq.MQConsumerMsgListenerProcessor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列消费者配置
 *
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

    private String accessKey;

    private String secretKey;

    @Autowired
    private MQConsumerMsgListenerProcessor consumerMsgListenerProcessor;

    @Bean
    @ConditionalOnProperty(prefix = "rocketmq.consumer", value = "isOnOff", havingValue = "on")
    public DefaultMQPushConsumer defaultConsumer() throws MQClientException {
        log.info("defaultConsumer 正在创建------------------------------------------");
        log.info(accessKey);
        log.info(secretKey);
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(null, groupName,
                new AclClientRPCHook(new SessionCredentials(accessKey, secretKey)));
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        consumer.registerMessageListener(consumerMsgListenerProcessor);
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
            log.error("defaultConsumer 创建失败！");
        }

        return consumer;
    }

}
