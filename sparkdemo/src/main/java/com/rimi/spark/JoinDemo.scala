package com.rimi.spark

import org.apache.spark.{SparkConf, SparkContext}

object JoinDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("WordApp")
    conf.setMaster("local")
    val sc = SparkContext.getOrCreate(conf)

    val rdd1 = sc.textFile("C:\\Users\\admin\\Desktop\\wc\\1.txt")
    val rdd2 = sc.textFile("C:\\Users\\admin\\Desktop\\wc\\2.txt")
    val rdd3 = rdd1.map(line => {
      val lines = line.split(" ")
      (lines(0), lines(1))
    })
    val rdd4 = rdd2.map(line => {
      val lines = line.split(" ")
      (lines(0), lines(1))
    })

    val rdd5 = rdd3.join(rdd4)
    rdd5.collect().foreach(println)


  }

}
