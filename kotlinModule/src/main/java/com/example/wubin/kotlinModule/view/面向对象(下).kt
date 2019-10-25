package com.example.wubin.kotlinModule.view

/**
 * @author wubin
 * @description
 * @date 2019-09-04
 */
fun main() {

//    扩展()

//    对象表达式()

//    伴生对象()

//    枚举()

    对象()

}

private fun 对象() {

    Payroll.add()
    println(Payroll.name)
    Payroll.name1 = "123"

    A.bar()
    A.name = "sdf"

}

/**
 * 伴生对象 相当于java的 静态方法
 */
class A {

    companion object {
        var name = "sdf"
        fun bar() = println("bar")
    }

    // 匿名函数 取代 匿名内部类
    fun init() {
        a.test()
    }

    object a : SelfAdapter() {}
}

open class SelfAdapter {
    fun test() {}
}

/**
 * 对象声明 单例
 */
object Payroll {
    val name = "123"
    var name1 = ""
    fun add() = println("sdfsfd")
}

fun 伴生对象() {

    MyClass.test()

    println(MyClass.name)

    MyClass.test1()

}

/**
 * 不能是抽象类
 */
fun 对象表达式() {

    var obj = object : Outputable1 {
        override fun test() {
        }
    }

    var obj1 = object {

        var name = ""

        fun test() {
            println("")
        }
    }

}

// 宏替换 不能使用Int
const val MAX_AGE = 100

private fun 扩展() {

    Raw().info()
    RawSub().info()

}

open class Raw {
    fun test() {
        println("test")
    }
}

class RawSub : Raw() {

    fun sub() {
        println("sub")
    }

}

fun Raw.info() {
    println("info")
}

fun Raw.info1() = println("info")

abstract class Shape {

    var color = ""

    abstract val type: String

    abstract fun calP(): Double

    fun print() {

    }

    constructor()

    constructor(color: String) {
        this.color = color
    }

}

interface Outputable {

    // 只读属性定义了getter 非抽象属性
    val name: String
        get() = "输出设备"

    // 抽象属性
    val brand: String

    var cate: String

    // 抽象方法
    fun out()

    // 非抽象方法
    fun print(vararg msgs: String) {
        for (msg in msgs) {
            print(msg)
        }
    }

}

interface Outputable1 {
    fun test()
}

/**
 * 伴生对象
 */
class MyClass {

    companion object MyObject1 : Outputable1 {

        val name = "name"

        override fun test() {

        }

    }
}

fun MyClass.MyObject1.test1() {
    println()
}

fun 枚举() {

    println(Gender.MALE)
    val g = Gender.valueOf("MALE")
    println("${g} ${g.sex}")
    g.info()

    Gender.MALE.info()

    println(Operation.Plus.eval(5.0, 3.0))

    println(Color.RED.rgb())

}

enum class Gender(val sex: String) {

    MALE("男"), FEMALE("女");

    fun info() {
        when (this) {
            MALE -> println("天行健，君子以自强不息")
            FEMALE -> print("地势坤，君子以厚德载物")
        }
    }

    fun info1() = when (this) {
        MALE -> "天行健，君子以自强不息"
        FEMALE -> "地势坤，君子以厚德载物"
    }

}

enum class Operation {

    Plus {
        override fun eval(x: Double, y: Double) = x + y
    },
    Times {
        override fun eval(x: Double, y: Double): Double {
            return x * y
        }
    };

    abstract fun eval(x: Double, y: Double): Double
}

enum class Color(val r: Int, val g: Int, val b: Int) {

    RED(255, 0, 0), YELLOW(255, 255, 0), GREEN(0, 255, 0), BLUE(0, 0, 255);

    fun rgb() = (r * 256 + g) * 256 + b

}





