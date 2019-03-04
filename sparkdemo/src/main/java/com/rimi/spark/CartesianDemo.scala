package com.rimi.spark

import org.apache.spark.{SparkConf, SparkContext}

object CartesianDemo {

  def main(args: Array[String]): Unit = {
    val a1 = Array(1,2,3,4)
    val a2 = Array(3,4,5)

    val conf = new SparkConf()
    conf.setAppName("WordApp")
    conf.setMaster("local")
    val sc = SparkContext.getOrCreate(conf)

    val rdd1 = sc.parallelize(a1)
    val rdd2 = sc.parallelize(a2)

    val rdd3 = rdd1.cartesian(rdd2)
    rdd3.collect().foreach(println)


  }

}
