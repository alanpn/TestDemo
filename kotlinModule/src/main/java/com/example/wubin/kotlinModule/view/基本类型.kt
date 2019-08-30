package com.example.wubin.kotlinModule.view

/**
 * @author wubin
 * @description
 * @date 2019-08-26
 */

fun main() {

    正则()

//    elvis()

//    安全调用()

//    空安全()

//    精度转换()

//    char的加减法()

//    类型转换()

//    divisionByZero()

//    toJavaClass()

}

fun 正则() {

    val str = "fjnu886"
    val flag = str.contains(Regex("\\d{3}")) // 是否包含3个连续的数字
    println(flag)

}

fun elvis() {

    /* if else 的简化版 */

    // 一般写法
    var c = "fkit"
    println(if (c != null) c.length else -1)

    // elvis写法
    var a = "fkit"
    println(a?.length ?: -1)

    var b: String? = null
    println(b?.length ?: -1)

    val d = b?.length ?: throw NullPointerException("aaaa")

}

fun 空安全() {

    var str = "aabb"
    var num: Int? = str.toIntOrNull()
//    num = null // 正确 因为Int? 表示不确定是否是Int类型
//    var num1: Int = str.toInt() // 会报空指针异常
//    var num2: Int? = str.toInt() // 会报空指针异常
//    var num3 = str.toIntOrNull()

    println(num) // 返回null7

    var b: String = "aa"
    println(b.length)

}

private fun 安全调用() {

    var c: String? = null
    println(c?.length) // 返回null

    val arr = listOf("aa", null, "bb")
    /*  aa bb */
    for (item in arr) {
        /* 区别在于第一种会排除null */
        item?.let { print("${it} ") }
    }
    println()

    /* aa null bb  */
    for (item in arr) {
        /* 会打印null */
        item.let { print("${it} ") }
    }
    println()

}

private fun 精度转换() {

    var a: Float = 1.9f
    var b: Double = a.toDouble()
    println("b等于 ${b}") // 1.899999976158142 精度丢失 所以还是同类型为好

    var c = 2f
    println(c.toDouble()) // 2.0 精度没丢失

    var d = 1.9
    var e: Float = d.toFloat()
    println(d) // 2.0 精度没丢失

}

private fun char的加减法() {

    /* 不能使用a+b 否则会当int计算 否则报错 */
    var a = 'a'
    var b = 'i'
    println(a + 120)
    println(a - 1)

}

private fun 类型转换() {

    var result = ""
    for (i in 0..5) {
        val num = (Math.random() * 26 + 97).toInt()
        result += num.toChar()
    }
    println(result)

}

private fun toJavaClass() {

    var a = 5
    var b: Short = 6
    var c = a.toLong() + b.toInt()
    print(c.javaClass) // long 获取java类型

}

private fun divisionByZero() {

    /* 只有浮点数才能除以0 否则会报java.lang.ArithmeticException: / by zero*/
    println(1.0 / 0) // Infinity 正无穷大
    println(-1.0 / 0) // -Infinity 负无穷大
    println(Math.sqrt(-1.0)) // NaN 非数
    print(5 / 0.0 == 1.0 / 0) // true 所有正无穷大 负无穷大指都相等

}