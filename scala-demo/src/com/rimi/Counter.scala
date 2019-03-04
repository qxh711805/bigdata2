package com.rimi



class Counter (int:Int) {

  // 自动生成get和set
  var value = 0
  // 自动生成get

  val value2= 0

  // 辅助构造器
//  def this(int:Int) {
//    this(int)//调用主构造器
//    this.value = int
//  }

//  def this(int:Int,int1:Int) {
//    this(int)
//  }


  def increment(value:Int): Unit = {
    this.value = value
  }

  def current:Int = this.value

}

object Test {
  def main(args: Array[String]): Unit = {
//    val counter = new Counter(12)
//
//    val zhang = new Person("zhang",12)
//    val name = zhang.getName()
  }
}
