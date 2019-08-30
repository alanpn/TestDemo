package com.example.wubin.kotlinModule.view

/**
 * @author wubin
 * @description
 * @date 2019-08-26
 */
fun main() {

//    分支()

}

private fun 分支() {

    var score = 'b'
    when (score) {
        'a' -> println("优")
        'b' -> println("良")
        'c' -> println("中等")
        'd' -> println("不及格")
        else -> println("垃圾")
    }

    when (score) {
        'a', 'b' -> println("优")
        'c', 'd' -> {
            println("中等")
            println("加油")
        }
        else -> println("垃圾")
    }

    val result =
            when (score) {
                'a', 'b' -> "优"
                'c', 'd' -> "中等"
                else -> "垃圾"
            }
    println(result)

    val arr = 50
    when (arr) {
        in 1..50 -> println("50以内")
        in 1..100 -> println("100以内")
    }

    val inputClass: Any = 26
    when (inputClass) {
        is String -> println("String")
        is Int -> println("Int")
        is Double -> println("Double")
    }

    val input = "45a"
    when {
        input.matches(Regex("\\d+")) -> println("全是数字")
        input.matches(Regex("[a-zA-Z]+")) -> println("全是字母")
        input.matches(Regex("[a-zA-Z0-9]+")) -> println("字母和数字")
        else -> println("其它")
    }

}