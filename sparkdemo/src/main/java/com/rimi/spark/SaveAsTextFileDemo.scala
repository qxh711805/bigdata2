package com.rimi.spark

import org.apache.spark.{SparkConf, SparkContext}

object SaveAsTextFileDemo {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()

    conf.setAppName("WordApp")
    conf.setMaster("local")
    val sc = SparkContext.getOrCreate(conf)

    val rdd = sc.textFile("C:\\Users\\admin\\Desktop\\wc\\wc.txt",3)

    val rdd2 = rdd.flatMap(_.split(" ")).map((_,1)).reduceByKey(_ + _)

    rdd2.saveAsTextFile("C:\\Users\\admin\\Desktop\\wc\\out")
  }

}
