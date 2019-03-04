package com.rimi.spark

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

object MapPartitionsDemo {

  def main(args: Array[String]): Unit = {

    val arr = List(1,2,3,4,5,6,7)

    val conf = new SparkConf()

    conf.setAppName("WordApp")
    conf.setMaster("local")
    val sc = SparkContext.getOrCreate(conf)
    val rdd = sc.parallelize(arr)

    val rdd3 = rdd.mapPartitions(a => {
      val arr = ArrayBuffer[Int]()
      while (a.hasNext) {
        val unit = a.next()
        val i = unit * 2
        arr += i
      }
      arr.iterator
    })
    rdd3.collect().foreach(println)

  }

}
