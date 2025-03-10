package com.sgh.demo.kafka.kafka.utils;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Kafka Producer 工具类
 *
 * @author Song gh
 * @version 2024/3/14
 */
@Slf4j
@Component
public class KafkaProducerUtils {

    @Setter(AccessLevel.PRIVATE)
    private static KafkaTemplate<Object, Object> kafkaTemplate;

    protected KafkaProducerUtils(@Autowired KafkaTemplate<Object, Object> kafkaTemplate) {
        setKafkaTemplate(kafkaTemplate);
    }

    /**
     * 同步发送消息
     *
     * @param topic   消息所属的 topic
     * @param message 消息内容
     */
    public static void syncSend(String topic, String message) {
        try {
            kafkaTemplate.send(topic, message).get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Kafka 同步发送消息失败, 消息: {}\n报错: ", message, e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 同步发送消息
     *
     * @param topic   消息所属的 topic
     * @param key     消息所属的 key, key 相同的数据会被写入同一个 partition
     * @param message 消息内容
     */
    public static void syncSend(String topic, String key, String message) {
        try {
            kafkaTemplate.send(topic, key, message).get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Kafka 同步发送消息失败, 消息: {}\n报错: ", message, e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 异步发送消息并回调
     *
     * @param topic   消息所属的 topic
     * @param message 消息内容
     */
    public static void asyncSend(String topic, String message) {
        CompletableFuture<SendResult<Object, Object>> future = kafkaTemplate.send(topic, message);
        // 回调, 记录报错
        future.whenCompleteAsync((result, e) -> {
            if (e != null) {
                log.error("Kafka 异步发送消息失败, 消息: {}\n报错: ", message, e);
            }
        });
    }

    /**
     * 异步发送消息并回调
     *
     * @param topic   消息所属的 topic
     * @param key     消息所属的 key
     * @param message 消息内容
     */
    public static void asyncSend(String topic, String key, String message) {
        CompletableFuture<SendResult<Object, Object>> future = kafkaTemplate.send(topic, key, message);
        // 回调, 记录报错
        future.whenCompleteAsync((result, e) -> {
            if (e != null) {
                log.error("Kafka 异步发送消息失败, 消息: {}\n报错: ", message, e);
            }
        });
    }
}
