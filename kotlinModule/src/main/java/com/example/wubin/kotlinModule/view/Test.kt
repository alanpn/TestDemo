package com.example.wubin.kotlinModule.view

import android.util.Log
import java.util.*
import kotlin.coroutines.*

/**
 * @author wubin
 * @description
 * @date 2019-08-23
 */

data class Person(val name: String, val age: Int? = null)  // 可为空的类型( Int? )

fun main() {

//    val persons = listOf(Person("LiLei", 10), Person("b", 20), Person("c"))
//
//    val oldest = persons.maxBy { it.age ?: 0 } // lambda表达式 如果 age等于null三元操作字符返回0
//
//    println("oldest is ${oldest}")

//    MyObject.print()
//
//    MyObject1.output()
//
//    CompanionTest().test()
//    CompanionTest.tt()
//
//    CompanionTest1().output()
//    CompanionTest1.output()
//
//    InnerClass().callB()
//
//    // nestedMethod()
//
//    //    innerMethod()
//
//    val name = "aaa"
//    println("xx $name xx")
//    println("xx${name}xx")
//    println("${name + "xx"}")

//    var user = User("last", "first")
//    println(user.fullName)
//
//    user.fullName = "last.first"
//    println(user.first + "++" + user.last)

//    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))

    // 支持多个分隔符，特殊字符不需要转义
//    println("12.34.5-778".split(".", "-"))

//    val str: String = "123"
//    str.substringBefore('@')

//    funLet()

    funOrder()

}

fun funOrder() {
    println("start ...........")

    Thread(Runnable {
        Thread.sleep(1000)
        println("thread ...........")
    }).start()

    println("end ...........")
}

/**
 * 链式调用
 * let 可以将执行结果向下传递 而also不行
 */
private fun funLet() {
    val str = "123"
    str.let {
        println(it)
        it.reversed()
    }.let {
        println(it)
        it.length
    }.also {
        println(it)
    }
}

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int {
    if (e is Num) {
//        val n = e as Num
//        return n.value
        return e.value
    }
    if (e is Sum) {
        return eval(e.right) + eval(e.left)
    }
    throw IllegalAccessException("unknow expression")
}

// 最好规定返回类型 否则String也可以返回
fun eval1(e: Expr): Int =
        when (e) {
            is Num -> e.value
            is Sum -> eval(e.right) + eval(e.left)
            else -> throw IllegalAccessException("unknow expression")
        }

/**
 * 密封类
 */
sealed class Expr1 {
    class Num(val value: Int) : Expr1()
    class Sum(val left: Expr1, val right: Expr1) : Expr1()
}

fun eval2(e: Expr1): Int =
        when (e) {
            is Expr1.Num -> e.value
            is Expr1.Sum -> eval2(e.right) + eval2(e.left)
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

class InnerClass {

    var a = 100
    var c = 1

    inner class B {

        var a = 200

        var b = 111

        fun test() {

            var a = 300

            // 如无匹配自动向外层搜索
            println(c)

            // 因为fun没有相同变量 输出111
            println(b)

            // 因为fun有相同变量会覆盖 输出300
            println("${a}")

            // 外面的a 输出200
            println("sfsf:  ${this@InnerClass.a}")

            // 下面两个结果相同 输出100
            println("sfsf:  ${this@B.a} == ${this.a}")

            // 修改值
            a = 400
            println(a)

            // 修改值
            this.a = 500
            println("${this.a}")

            // 修改值
            this@InnerClass.a = 600
            println("${this@InnerClass.a}")

        }

    }

    fun callB() {
        B().test()
    }
}

private fun nestedMethod() {

    var a = 0
    var obj = object {

        fun test() {
            a++
        }
    }
    obj.test()

    // 输出1
    println(a)

}

fun max(a: Int, b: Int): Int = if (a > b) a else b

/**
 * 局部函数
 */
fun innerMethod() {

    fun test1(): String {
        return "a"
    }

    println(test1())

    fun test2(n: Int): Int {
        return n * n
    }
    println(test2(5))

}

private fun argsMethod1() {

    argsMethod("aaa", "bbb", "ccc")

    var arr = arrayOf("aaa", "bbb", "ccc")
    argsMethod(*arr)
}

/**
 * 可变参数
 */
fun argsMethod(vararg books: String) {

    println(books.contentToString()) // 打印

    for (b in books) {
        println(b)
    }

    for (i in books.indices) {
        println("第 " + i + " 个元素 : " + books[i])
    }

}

/**
 * switch
 */
private fun switchMethod() {
    var souce = "A"

    when (souce) {
        "A" -> println(" ")
        else -> println(" ")
    }

    when (souce) {
        "A", "B" -> println(" ")
        else -> println(" ")
    }

    var date = java.util.Date()
    when (date) {
        java.util.Date() -> println(" ")
        else -> println(" ")
    }

    val score = "B"
    val str = when (score) {
        "A" -> "grate"
        "B" -> "good"
        else -> "thanks"
    }

    // 输出aa
    val num = 60
    when (num) {
        in 0..60 -> println("bad")
        in 60..100 -> println("good")
        else -> println("good")
    }

}

/**
 * 类型判断
 */
fun realInputType(inputType: Any) {
    when (inputType) {
        is String -> println("String")
        is Int -> println("Int")
        is Double -> println("Double")
        else -> println("can't recognize")
    }
}

/**
 * 正则
 */
private fun regex() {
    var str = "3423424"
    str = "seefs"
    println(str.contains(Regex("\\d{3}")))
}

/**
 * 字符模版
 */
private fun model() {
    var str = "sfsfdsdfs"
    println("${str}的长度是${str.length}")
}

/**
 * elvis运算  if的简写版 用于非空判断
 */
private fun elvis() {
    var b: String? = null
    var len = b?.length ?: -1
    println("sdfsf : " + len)
    println(add(5, 10) ?: 0)
}

/**
 * for循环
 */
private fun forMethod() {

    var result = ""
    // 包含0，包含5
    for (i in 0..5) {
        result += i.toString() + " "
    }
    println("result : ${result}")

    // 包含0 不包含5
    result = ""
    for (i in 0 until 5) {
        result += i.toString() + " "
    }
    println("result : ${result}")

    // 倒序 5到0 的顺序 包含5 包含0
    result = ""
    for (i in 5 downTo 0) {
        result += i.toString() + " "
    }
    println("result : ${result}")

    // 步长
    result = ""
    for (i in 5 downTo 0 step 2) {
        result += i.toString() + " "
    }
    println("result : ${result}")

}

private fun forMethod2() {
    val myArr: Array<String?> = arrayOf("aa", null, "bb", null, "cc")
//        val myArr1: Array<String> = arrayOf("aa", "") // 因为String 没有? 所以不能包含null
    for (item in myArr) {
        // 当item 不等于null 的时候才打印
        item?.let { println("sdfsdfsf : " + item) }
    }

    println(Arrays.toString(myArr))

    var arr = intArrayOf(1, 2, 4)
    var a = arrayOf(1, 2, 3)

}

private fun forMethod3() {
    val arr = arrayOf("aa", "bb", "ccc")

    // 打印结果 IndexedValue(index=0, value=aa)
    for (e in arr.withIndex()) {
        println(e.index.toString() + " " + e.value)
    }

    for ((index, e) in arr.withIndex()) {
        println(index.toString() + " " + e)
    }

    for (e in arr) {
        println(e)
    }

    for (index in arr.indices) {
        println(index)
    }

    var books = arrayOf("aa", "bb", "ccc")
    for (book in books) {
        println(book)
    }
    books.forEach { println(it) }

}

fun arrayMethod() {

    // 是否包含
    var arr = arrayOf("aa", "bb")
    println("aa" in arr)

    var list = mutableListOf<String>()
    list.add("aa")


    var tmp1 = listOf<Int>() // 不可变 只能在初始化时赋值
    var tmp = arrayListOf<Int>() // 可变
    tmp.add(1)

}

fun add(a: Int, b: Int): Int? {
    return a + b
}

/**
 * 形参 默认值
 */
fun add1(a: Int = 1, b: Int = 3): Int? {
    return a + b
}

/**
 * 带接收者的匿名函数
 */
fun noNameMethod() {
    println(6.factorial())
}

val factorial = fun Int.(): Int {
    if (this < 0) {
        return -1
    } else if (this == 1) {
        return 1
    } else {
        var result = 1
        for (i in 1..this) {
            result *= i
        }
        return result
    }
}

var User.fullName: String
    get() = "${first}.${last}"
    set(value) {
        println("set method")
        if ("." !in value || value.indexOf(".") != value.lastIndexOf(".")) {
            println("illeagal")
        } else {
            var arr = value.split(".")
            first = arr[0]
            last = arr[1]
        }
    }

/**
 * 因为接口的特殊性 突破了java的多继承 ??
 */
class C : IF1, IF2 {}

interface IF2 {
    fun test1() = println("test1")
}

interface IF1 {
    fun test() = println("test")
}

/**
 * interface 与 abstracct 的区别
 */
interface IF {

    val a: Int
//    var b: Int = 5 // 失败 interface 不能初始化值

    fun test()
    fun test1() {

    }
}

abstract class AB {

    abstract val a: Int
    var b: Int = 5

    abstract fun test()
    fun test1() {

    }
}



