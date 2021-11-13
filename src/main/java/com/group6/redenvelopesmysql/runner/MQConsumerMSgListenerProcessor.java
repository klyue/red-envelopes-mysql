package com.group6.redenvelopesmysql.runner;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author Yang Xichun
 * @date 2021/11/14 0:04
 */
@Slf4j
public class MQConsumerMSgListenerProcessor implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if (list.size() == 0) {
            log.info("MQ 接收消息为空，直接返回成功");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = list.get(0);
        log.info("MQ 接收到的消息为：{}" + messageExt.toString());

        try {
            String topic = messageExt.getTopic();
            String tags = messageExt.getTags();
            String body = new String(messageExt.getBody(), "utf-8");
            log.info("MQ 消息 topic={}, tags={}, 消息内容={}", topic, tags, body);
        } catch (Exception e) {
            log.error("获取 MQ 消息内容异常：{}", e);
        }

        // TODO 消费消息队列中的消息并写入数据，写入失败则删除 Redis 缓存中的相应数据

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
