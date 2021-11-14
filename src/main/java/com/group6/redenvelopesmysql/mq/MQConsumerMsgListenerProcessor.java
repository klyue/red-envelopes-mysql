package com.group6.redenvelopesmysql.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group6.redenvelopesmysql.entity.RedEnvelope;
import com.group6.redenvelopesmysql.mq.data.InsertMessage;
import com.group6.redenvelopesmysql.mq.data.UpdateMessage;
import com.group6.redenvelopesmysql.repository.RedEnvelopeRepository;
import com.group6.redenvelopesmysql.repository.WalletRepository;
import com.group6.redenvelopesmysql.service.RedEnvelopesService;
import com.sun.xml.internal.messaging.saaj.soap.Envelope;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Yang Xichun
 * @date 2021/11/14 0:04
 */
@Slf4j
@Component
public class MQConsumerMsgListenerProcessor implements MessageListenerConcurrently {

    @Autowired
    private RedEnvelopesService redEnvelopesService;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if (list.size() == 0) {
            log.info("MQ 接收消息为空，直接返回成功");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = list.get(0);
        log.info("MQ 接收到的消息为：{}" + messageExt.toString());

        String topic;
        String tags = "insert";
        String body = "";
        try {
            topic = messageExt.getTopic();
            tags = messageExt.getTags();
            body = new String(messageExt.getBody(), "utf-8");
            log.info("MQ 消息 topic={}, tags={}, 消息内容={}", topic, tags, body);
        } catch (Exception e) {
            log.error("获取 MQ 消息内容异常：{}", e.getMessage());
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }

        // TODO 消费消息队列中的消息并写入数据，写入失败则删除 Redis 缓存中的相应数据

        ObjectMapper mapper = new ObjectMapper();
        if (tags.equals("insert")) {
            InsertMessage insertMessage;
            try {
                insertMessage = mapper.readValue(body, InsertMessage.class);
            } catch (Exception e) {
                log.error("JSON 解析失败：{}", e.getMessage());
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
            RedEnvelope redEnvelope = new RedEnvelope();
            redEnvelope.setEid(insertMessage.getE());
            redEnvelope.setUid(insertMessage.getU());
            redEnvelope.setValue(insertMessage.getV());
            redEnvelope.setOpened(false);
            redEnvelope.setSnatchTime(insertMessage.getT());

            redEnvelopesService.insert(redEnvelope);
        } else if (tags.equals("update")) {
            UpdateMessage updateMessage;
            try {
                updateMessage = mapper.readValue(body, UpdateMessage.class);
            } catch (Exception e) {
                log.error("JSON 解析失败：{}", e.getMessage());
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }

            redEnvelopesService.update(updateMessage.getE(), updateMessage.getU(), updateMessage.getV());
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
