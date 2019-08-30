package com.example.wubin.kotlinModule.view

/**
 * @author wubin
 * @description
 * @date 2019-08-29
 */
fun main() {

    var rn: (Dog) -> Unit = Dog::run
    val d = Dog()
    rn(d)

    var et = Dog::eat
    et(d, "肉骨头")

    var jp: (Dog, String) -> Int = Dog::jump
    println(jp(d, "hhh "))

    Dog().run()

}

class Dog {
    fun run() {
        println("run....")
    }

    fun eat(food: String) {
        println("eating " + food)
    }

    fun jump(food: String): Int {
        return -1
    }
}