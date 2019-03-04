package com.rimi.spark

import org.apache.spark.{SparkConf, SparkContext}

object MyFirstDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()

    conf.setAppName("WordApp")
    conf.setMaster("local")
    val sc = SparkContext.getOrCreate(conf)

    val rdd = sc.textFile("C:\\Users\\admin\\Desktop\\wc\\wc.txt")

    val rdd2 = rdd.flatMap(line => {
      println("flatMap:start")
      line.split(" ")
    })

    val rdd3 = rdd2.map(words => {
      println("map:start")
      (words,1)
    })

    val rdd4 = rdd3.reduceByKey((a,b) => {
      println("reduceByKey:start")
      a + b
    })

    val array = rdd4.collect()
    array.foreach(a => {
      println(a._1 + ":" + a._2)
    })

  }

}
