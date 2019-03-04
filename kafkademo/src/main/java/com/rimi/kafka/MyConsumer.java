package com.rimi.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * 消费者
 */
public class MyConsumer {

    /**
     * 消费
     */
    public void poll(){
        // 创建配置信息
        Properties properties = new Properties();
        // 设置kafka集群
        properties.put("bootstrap.servers","10.0.0.20:9092");
        // 设置组ID
        properties.put("group.id","group01");
//        properties.put("auto.offset.reset","latest");//读取最近的数据
        properties.put("auto.offset.reset","earliest");//从头开始
        // 是否自动提交
        properties.put("enable.auto.commit",false);
        // 反序列化
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        // 创建消费者
        Consumer<String,String> myConsumer = new KafkaConsumer<String, String>(properties);
        // 设置接收主题
        myConsumer.subscribe(Collections.singletonList("topicA"));
        // 创建死循环,接收数据
        while (true) {
            // 拉取数据
            ConsumerRecords<String, String> records = myConsumer.poll(Duration.ofMillis(10000000));
            // 方式一 创建迭代器
//            Iterator<ConsumerRecord<String, String>> iterator = records.iterator();
//            while (iterator.hasNext()) {
//                // 获取记录
//                ConsumerRecord<String, String> next = iterator.next();
//                // 获取消息内容
//                String value = next.value();
//                // 打印内容
//                System.out.println(value);
//            }
            // 方式二
            for (ConsumerRecord<String, String> record : records) {
                // 打印消息
                System.out.println(record.value());
            }
        }
    }
}
