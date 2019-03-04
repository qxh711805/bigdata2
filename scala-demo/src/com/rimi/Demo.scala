package com.rimi

import scala.collection.mutable.ArrayBuffer

object Demo {
  // unit ==> void
  /**
    * main方法
    *
    * @param args
    */
  def main(args: Array[String]): Unit = {

    val array = new Array[Int](10)

    for (a <- array) {
      println(a)
    }
    val array1 = Array(1, 2, 3, 4, 5)

    for (a <- array1) {
      println(a)
    }

    // 取值
    array1(0) //去取第一个元素

    // 可变数组
    val b = ArrayBuffer[Int]()
    // 数据追加
    b += 1
    b += (1,2,3)

    b -= 1
    b ++= Array(4,5,6,7,8)
    b.insert(3,9,2,1,3,2)
    println(b)

    for (i <- 0 until b.length) {
      println(b(i))
    }
    println(b)
    val c = for (i <- b) yield i * 2
    println(c)

    // 求和
    val sum = c.sum
    println("和:"+sum)

    // 排序
    val sort = c.sortWith(_ > _)
    println(sort)


  }

}
