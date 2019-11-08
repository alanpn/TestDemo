package com.example.wubin.kotlinModule.view

/**
 * @author wubin
 * @description
 * @date 2019-08-26
 */
fun main() {

//    基本运算符()

//    比较()

//    区间运算符()

}

fun 区间运算符() {

    val arr = listOf(1, 2, 3, 4)

    // 1 2 3 4
    for (num in arr) {
        print("${num} ")
    }
    println()

    // 1 2 3 4
    for (num in 0 until arr.size) {
        print("${arr.get(num)} ")
    }
    println()

    // 1 2 3 4 5 6
    for (num in 1..6) {
        print("${num} ")
    }
    println()

    // 1 2 3 4 5
    for (num in 1 until 6) {
        print("${num} ")
    }
    println()

    // 6 5 4 3 2 1
    for (num in 6 downTo 1) {
        print("${num} ")
    }
    println()

    // 1 3 5
    for (num in 1 until 6 step 2) {
        print("${num} ")
    }
    println()

    // 6 4 2
    for (num in 6 downTo 1 step 2) {
        print("${num} ")
    }
    println()

}

fun 比较() {

    val a = java.lang.String("aaa")
    val b = java.lang.String("aaa")
    println(a == b) // true
    println(a === b) // false只有在此时才去判断是否为同一个对象

}

private fun 基本运算符() {
    var arr = arrayOf("1", "2", "4")

    println("1" in arr)
    println(arr.contains("1"))

    println(arr.get(2))
    println(arr[2])
}