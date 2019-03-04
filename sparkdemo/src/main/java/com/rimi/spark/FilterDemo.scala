package com.rimi.spark

import org.apache.spark.{SparkConf, SparkContext}

object FilterDemo {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()

    conf.setAppName("WordApp")
    conf.setMaster("local")
    val sc = SparkContext.getOrCreate(conf)

    val rdd = sc.textFile("C:\\Users\\admin\\Desktop\\wc\\wc.txt")

//    ["word","helloe"]

    val rdd2 = rdd.flatMap(_.split(" "))

    val rdd3 = rdd2.filter(word => word.contains("hello"))

    val rdd4 = rdd3.map((_,1))

    val rdd5 = rdd4.reduceByKey(_ + _)

    rdd5.collect().foreach(a => {
      println(a._1 + ":" + a._2)
    })

  }
}
