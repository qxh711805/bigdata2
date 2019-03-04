package com.rimi

class Person private (var name:String, var age:Int) {

}

object Person{

  def Person(name:String,age:Int):Person = {
    println("调用Person方法")
    new Person(name,age)
  }

  def apply(name:String,age:Int):Person = {
    println("调用apply方法")
    new Person(name,age)
  }
}
