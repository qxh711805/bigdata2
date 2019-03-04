package com.rimi

object Demo2 {

  def main(args: Array[String]): Unit = {

    //映射
    val map = Map(12 -> "zhang",13 -> "li" ,14 -> "wang")
    val map2 = Map((12,"zhang"),(13,"li"),(14,"wang"))
    val map3 = new scala.collection.mutable.HashMap[Int,String]

    // 根据key取value
    val value = map(12)
    println(value)
    // 迭代map
    for ((k,v) <- map) {
      println(k + ":" + v)
    }

    for(v <- map.values) {
      println(v)
    }

    for (k <- map.keys) {
      println(k)
    }
//    map2(12) = "zhao"
    println(map2(12))

    map3 += ((13,"搜索"))
    println(map3)

    // 更新
     map3(13) = "we"
     println(map3)


    // 元组
    val  t = (1,2,1,1,1,1,1)
    val t1 = (Int,Int,Int)
    println(t.getClass)


    val h = Array("niulan","zhouyu","zhangshanfeng")
    val w = Array("zhinv","xiaoqiao")
    val tuples = h.zip(w)
    for ( t  <- tuples ) {
      println(t._1 + ":" + t._2)
    }

  }

}
