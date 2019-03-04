package com.rimi.spark.java;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import scala.Tuple2;

import java.util.*;

/**
 * sparkStreaming 集成 kafka
 */
public class JavaSparkStreamingIntegrationKafka {

    public static void main(String[] args) throws InterruptedException {

        // kafka配置信息
        Map<String,Object> kafkaParams = new HashMap<>();
        // kafka 服务器地址
        kafkaParams.put("bootstrap.servers","10.0.0.155:9092");
        // kafka 数据的解码类
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer",StringDeserializer.class);

        //  kafka 消费者组
        kafkaParams.put("group.id","spark_kafka_1");
        // kafka 消费的位置
        kafkaParams.put("auto.offset.reset","latest");
        // kafka 是否自动提交
        kafkaParams.put("enable.auto.commit",false);

        // 创建消费主题
        List<String> topicA = Arrays.asList("topicA");

        SparkConf conf = new SparkConf().setAppName("spark_kafka").setMaster("local[2]");

        // 创建 JavaStreamContext
        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(10));


        // 使用 kafkaUtils 创建输入流
        JavaInputDStream<ConsumerRecord<Object, Object>> dStream = KafkaUtils.createDirectStream(
                jssc, // StreamingContext
                LocationStrategies.PreferConsistent(),// 位置策略
                ConsumerStrategies.Subscribe(topicA, kafkaParams)//消费者策略
        );

        JavaDStream<String> flatMap = dStream.flatMap(new FlatMapFunction<ConsumerRecord<Object, Object>, String>() {
            @Override
            public Iterator<String> call(ConsumerRecord<Object, Object> objectObjectConsumerRecord) throws Exception {
                String line = (String) objectObjectConsumerRecord.value();
                return Arrays.asList(line.split(" ")).iterator();
            }
        });

        JavaPairDStream<String, Integer> map = flatMap.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s, 1);
            }
        });

        JavaPairDStream<String, Integer> reduce = map.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });
        reduce.print();

        // 开启计算
        jssc.start();

        // 等待
        jssc.awaitTermination();

    }
}
