package com.rimi.spark

import org.apache.spark.{SparkConf, SparkContext}

import scala.util.Random

object WC {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("WC")
    conf.setMaster("local")
    val sc = SparkContext.getOrCreate(conf)

    val rdd1 = sc.textFile("C:\\Users\\admin\\Desktop\\wc\\wc.txt",3)

    //("hello","hello")
    val rdd2 = rdd1.flatMap(_.split(" "))

    // (("hello_1",1))
    val rdd3 = rdd2.map(words => {
      (words + "_" + Random.nextInt(3), 1)
    })

      // ((hello_1,10))
    val rdd4 = rdd3.reduceByKey(_ + _)

    // ((hello,10))
    val rdd5 = rdd4.map(result => {
      val key = result._1.split("_")(0)
      (key, result._2)
    })

    val rdd6 = rdd5.reduceByKey(_+_)
    //打印
    rdd6.collect().foreach(println)


  }

}
