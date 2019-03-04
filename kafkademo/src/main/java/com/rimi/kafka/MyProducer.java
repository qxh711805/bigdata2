package com.rimi.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.Future;

/**
 * 消息生产者
 */
public class MyProducer {

    /**
     * 发送消息
     * @param msg
     */
    public void sendMsg(String msg) {

        //  创建配置信息

        Properties pro = new Properties();
        // kafka服务器信息
        pro.put("bootstrap.servers", "10.0.0.20:9092");
        // 配置同步
        pro.put("acks", "1");
        // 发送失败后,重新发送
        pro.put("retries",0);
        // 序列化
        pro.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        pro.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        // 创建消息生产者
        Producer<String,String> myProducer = new KafkaProducer<String, String>(pro);
        // 创建生产者记录
        ProducerRecord<String, String> record = new ProducerRecord<>("topicA", msg);
        // 发送
        Future<RecordMetadata> send = myProducer.send(record);
        // 关闭
        myProducer.close();
    }

    public static void main(String[] args) {
        MyProducer myProducer = new MyProducer();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入:");
            String s = scanner.nextLine();
            myProducer.sendMsg(s);
            System.out.println("发送的消息:" + s);
        }
    }
}
