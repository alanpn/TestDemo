package com.example.wubin.kotlinModule.view

import java.util.*

/**
 * @author wubin
 * @description
 * @date 2019-08-29
 */
fun main() {

//    赋值()
//
//    继承()

//    类型判断()

    强转类型()

}

fun 强转类型() {

    val a: Any = "Hello"

    // 强转 失败会报错
    val b = a as String

    // 不会报错 失败返回null
    val c = a as? String

    // 用 as? ?. 访问不会报错
    val fruit = Fruit()
    var tmp = fruit as? Base
    println(tmp?.name)

}

fun 类型判断() {

    /*
        编译类型为Any 实际类型为String
        不写Any编译报错 就无法测试 hello is Date
     */
    val hello: Any = "Hello"

    println("类型检查 ${hello is String}")
    println("类型检查 ${hello !is Test}")
    when (hello) {
        is String -> println("String")
        is Date -> println("Date")
    }

    // 自动编译自动判断
    var a: Any = String
    if (a is String && a.length == 3) { // 是String而且长度为3

    }

    if (a !is String || a.length == 0) return // 不是String 或者 是String且长度为0


}

private fun 赋值() {

    var user = User("first", "last", "name")
    println(user.fullName)
    user.fullName = "sdfsdf" // 不符合规定 不能设值
    println(user.fullName)
    user.fullName = "aa.bb"
    println(user.fullName)

}

private fun 继承() {

    Test()
    Test("aa")
    Test("aa", 12)
    Test().test()

}

class User(first: String, last: String, name: String) {

    var first: String = first
    var last: String = last

    /*
        在kotlin定义一个普通属性时，kotlin会为该属性生成一个field字段 称为幕后字段
        重写getter、setter 方法时 使用field显示引用了幕后字段
     */
    var name = name
        set(newNmae) {
            if (newNmae.length > 6 || newNmae.length < 2) {
                println("输入的 name 不合法")
            } else {
                field = newNmae
            }
        }

    var fullName: String
        get() = "${first}.${last}"
        set(value) {
            if ("." !in value || value.indexOf(".") != value.lastIndexOf(".")) {
                println("输入的 fullName 不合法")
            } else {
                var arr = value.split(".")
                first = arr.get(0)
                last = arr.get(1)
            }
        }

    val fullName1: String get() = "${first} ${last}"

    val fullName2: String
        get() {
            return "${first} ${last}"
        }

}

class Test : Base, Bar {

    constructor() {
        println("Test 无参构造器")
    }

    // 调用父类的构造器
    constructor(name: String) : super() {
        println("有参构造器 name = ${name}")
    }

    // 调用本身的 String 构造器
    constructor(name: String, age: Int) : this(name) {
        println("有参构造器 name = ${name} age=${age}")
    }

    /*
        Base 无参构造器
        Test 无参构造器
        Bar test()
        Base test()
     */
    override fun test() {
        super<Bar>.test()
        super<Base>.test()
    }

}

open class Base {

    var name: String = "哈哈哈"

    constructor() {
        println("Base 无参构造器")
    }

    open fun test() {
        println("Base test()")
    }

}

// 可以把kotlin接口看成java的抽象类
interface Bar {

    fun test() {
        println("Bar test()")
    }

}

class Fruit {

}