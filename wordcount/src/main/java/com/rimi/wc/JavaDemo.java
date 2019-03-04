package com.rimi.wc;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;

public class JavaDemo {
    public static void main(String[] args) {
        // 创建配置
        SparkConf conf = new SparkConf();
        // 设置应用名称
        conf.setAppName("wordcount");
        // 设置运行的模式
        conf.setMaster("local");
        // 创建sparkcontext == scala
        JavaSparkContext context = new JavaSparkContext(conf);
        JavaRDD<String> rdd = context.textFile("C:\\Users\\admin\\Desktop\\wc\\wc.txt");
        JavaRDD<String> words = rdd.flatMap((FlatMapFunction<String, String>) s -> Arrays.asList(s.split(" ")).iterator());

        JavaPairRDD<String, Integer> pairs = words.mapToPair((PairFunction<String, String, Integer>) s -> new Tuple2<>(s, 1));

        JavaPairRDD<String, Integer> wordCounts = pairs.reduceByKey((Function2<Integer, Integer, Integer>) (v1, v2) -> v1 + v2);

        wordCounts.foreach((VoidFunction<Tuple2<String, Integer>>) stringIntegerTuple2 -> System.out.println(stringIntegerTuple2._1 + ":" + stringIntegerTuple2._2));
    }
}
