package com.rimi.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Demo {
    public static void main(String[] args) {

        // 创建配置信息
        Properties properties = new Properties();
        // 设置kafka集群
        properties.put("bootstrap.servers","hd1:9092");
        // 设置组ID
        properties.put("group.id","group02");
//        properties.put("auto.offset.reset","earliest");
        // 是否自动提交
        properties.put("enable.auto.commit",false);
        // 反序列化
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        // 创建消费者
        Consumer<String,String> myConsumer = new KafkaConsumer<String, String>(properties);
        // 设置接收主题
        myConsumer.subscribe(Collections.singletonList("myTopic"));
        // 创建死循环,接收数据
        while (true) {
            // 拉取数据
            ConsumerRecords<String, String> records = myConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                // 打印消息
                System.out.println(record.value());
            }
        }
    }
}
