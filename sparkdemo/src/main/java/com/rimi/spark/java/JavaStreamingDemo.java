package com.rimi.spark.java;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

public class JavaStreamingDemo {
    public static void main(String[] args) throws InterruptedException {

        //  创建本地 streamContext
        SparkConf conf = new SparkConf();
        conf.setMaster("local[2]");
        conf.setAppName("JavaStreamingDemo");
        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(5));

        // 监听本地端口
        JavaReceiverInputDStream<String> lines = jssc.socketTextStream("hd1", 9999);

        JavaDStream<String> flatMap = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                String[] s1 = s.split(" ");
                return Arrays.asList(s1).iterator();
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
