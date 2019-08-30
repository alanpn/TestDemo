package com.example.wubin.kotlinModule.view

/**
 * @author wubin
 * @description
 * @date 2019-08-29
 */
fun main() {

    var user = User("first", "last")
    println(user.fullName)

}

class User(first: String, last: String) {

    var first: String = first
    var last: String = last

    var fullName: String
        get() = "${first}.${last}"
        set(value) {
            if ("." !in value || value.indexOf(".") != value.lastIndexOf(".")) {
                println("输入的fullName 不合法")
            } else {
                var arr = value.split(".")
                first = arr.get(0)
                last = arr.get(1)
            }
        }

    val fullName1: String get() = "${first} ${last}"

}