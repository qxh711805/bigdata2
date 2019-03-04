package com.rimi.spark

import org.apache.spark.{SparkConf, SparkContext}

object UnionDemo {

  def main(args: Array[String]): Unit = {

    val a1 = Array(1,2,3,3,5,2,7)

    val a2 = Array(3,4,5,6,7,8)


    val conf = new SparkConf()
    conf.setAppName("myApp")
    conf.setMaster("local")
    val sc = SparkContext.getOrCreate(conf)

    val rdd1 = sc.parallelize(a1)
    val rdd2 = sc.parallelize(a1)

    // 合并
    val rdd3 = rdd1.union(rdd2)
    // 交集
    val rdd4 = rdd1.intersection(rdd2)
    // 去重复
    val rdd5 = rdd1.distinct()

    rdd3.collect().foreach(println)
    rdd4.collect().foreach(println)
    rdd5.collect().foreach(println)


  }

}
