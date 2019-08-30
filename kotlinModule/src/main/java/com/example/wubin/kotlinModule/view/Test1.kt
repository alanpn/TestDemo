package com.example.wubin.kotlinModule.view

/**
 * @author wubin
 * @description
 * @date 2019-08-23
 */

data class Person(val name: String, val age: Int? = null)  // 可为空的类型( Int? )

fun main() {

    val persons = listOf(Person("LiLei", 10), Person("b", 20), Person("c"))

    val oldest = persons.maxBy { it.age ?: 0 } // lambda表达式 如果 age等于null三元操作字符返回0

    println("oldest is ${oldest}")

}