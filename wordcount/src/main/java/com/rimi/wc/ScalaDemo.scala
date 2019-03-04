package com.rimi.wc

import org.apache.spark.{SparkConf, SparkContext}

object ScalaDemo {

  def main(args: Array[String]): Unit = {
    // 创建配置文件
    val conf = new SparkConf().setAppName("wordcount").setMaster("local")
    // 创建sparkcontext
    val sc = SparkContext.getOrCreate(conf)
    // 读取文件得到数据集
    val file = sc.textFile("C:\\Users\\admin\\Desktop\\wc\\wc.txt")
    // 对数据集进行处理,取出每个单词   ["hello","spark","hello","scala"]
//    file.flatMap(line => line.split(" "))
    val flatmap = file.flatMap(_.split(" "))
    // 进一步处理 [("hello",1),("spark",1),("hello",1),("scala",1)]
//    flatmap.map(map => (map,1))
    val map = flatmap.map((_,1))
    // 进行聚合操作 ,[("hello",2),("spark",1),("scala",1)]
//    map.reduceByKey(_ + _)
    val reduce = map.reduceByKey((a,b) => a + b)
    // 打印
    reduce.collect().foreach(println)
//    reduce.saveAsTextFile("C:\\Users\\admin\\Desktop\\wc\\out")
  }

}
