package com.rimi

object TestAction {
//   def main(args: Array[String]): Unit = {
////    val action = Map(("open",DoNothingAction),("save",DoNothingAction))
////    for (a <- action ) {
////      println(a._2.description)
////    }
////    val z1 = new Person("z",12)
//    val z2 = Person("z",12)
//
////   println(z1.getClass)
////   println(z2.getClass)
//
//    val ints = Array(10)
//    val array = new Array(10)
//    println(ints)
//    println(array)
//  }

  def main(args: Array[String]): Unit = {
    for (e <-Em.values) {
      println(e)
    }
  }


}
