package com.example.wubin.kotlinModule.view

/**
 * kotlin 取消 static 关键字
 * object 类似于 static类
 * companion 类似于 在 class 里 建立一个static类
 */
fun main() {

    MyObject.print()

    MyObject1.output()

    CompanionTest().test()
    CompanionTest.tt()

    CompanionTest1().output()
    CompanionTest1.output()

}

/**
 * 对象表达式
 */
object MyObject {

    fun print() {
        println("print......")
    }

}

object MyObject1 : IO {

    override fun output() {
        println("print......")
    }

}

/**
 * 伴生对象
 * only one companion object is allowed per class
 */
class CompanionTest {

    fun test() {
        println("this is test")
    }

    companion object NoName {
        fun tt() {
            println("this is tt")
        }
    }

}

class CompanionTest1 : IO {

    override fun output() {
        println("this is outside print")
    }

    companion object NoName2 : IO {
        override fun output() {
            println("this is companion print")
        }

    }

}

interface IO {
    fun output()
}