package com.example.wubin.kotlinModule.view

/**
 * @author wubin
 * @description
 * @date 2019-08-26
 */
fun main() {

//    创建数组()

//    List循环()

//    list集合()

//    map集合()

//    map操作()

//    map循环()

}

fun map循环() {

    var map = mapOf("100" to 1, "50" to 2, "150" to 3)

    for (en in map.entries) {
        println("key: ${en.key}  value: ${en.value}")
    }

    for (key in map.keys) {
        print("${key} ")
    }
    println()

    for (value in map.values) {
        print("${value} ")
    }
    println()

    for ((key, value) in map) {
        println("${key} ${value}")
    }

    map.forEach({ println("${it.key}   ${it.value}") })

    println(map.mapValues { entry -> "${entry.key} :: ${entry.value}" })

    println(map.mapValues { (key, value) -> "${key} :: ${value}" })

}

fun map操作() {

    var map = mutableMapOf("100" to 1, "50" to 2, "150" to 3)

    println("100" in map)

    println(map.maxBy { it.value })

    var map1 = mapOf("100" to 1, "250" to 2)
    println(map + map1)

}

fun map集合() {

    // 不可变数组 按添加顺序排列
    var map = mapOf("abc" to 1, "aaa" to 3, "sdfsfd" to 2)
    println(map)

    // 可变 按添加顺序排列
    var map1 = mutableMapOf("abc" to 1, "aaa" to 3, "sdfsfd" to 2)
    println(map1)

    // 不保证 K-V 顺序
    var map2 = hashMapOf("abc" to 1, "aaa" to 3, "sdfsfd" to 2)
    println(map2)

    // 按添加顺序排列
    var map3 = linkedMapOf("abc" to 1, "aaa" to 3, "sdfsfd" to 2)
    println(map3)

    // treeMap集合 对key由小到大排列
    var map4 = sortedMapOf("abc" to 1, "aaa" to 3, "sdfsfd" to 2)
    println(map4)

    println(map.toSortedMap())

    var map5 = mapOf("100" to 1, "50" to 2, "150" to 3)
    println(map5.toSortedMap(compareBy { it.toInt() }))

}

fun list集合() {

    // 不可变集合
    var list = listOf(1, 2, 4, null)

    // 不可变 非空集合
    var list1 = listOfNotNull(1, 2, 3)

    // 可变集合
    var list2 = mutableListOf(1, 2, 3)

    var list3 = arrayListOf(1, 2, 3)

}


private fun 创建数组() {

    var arr = arrayOf(1, 2, 3)
    var arr0_6 = intArrayOf(1, 3, 4) // byteArrayOf() doubleArrayOf()

    // 指定长度 元素为null的数组
    var arr1 = arrayOfNulls<String>(5)

    // 长度为0的空数组
    var arr2 = emptyArray<String>()

    /*
      指定长度 使用lambda表达式初始化数组元素
      a b c d e
    */
    var arr3 = Array(5, { (it + 97).toChar() })
    for (num in arr3) {
        print("${num} ")
    }
    println()

    var arr4 = Array(5, { '1' })
    for (num in arr4) {
        print("${num} ")
    }
    println()

    var arr5 = Array(5, { (Math.random() * 10).toInt() })
    for (num in arr5) {
        print("${num} ")
    }
    println()

}

private fun List循环() {

    var arr = arrayOf(1, 2, 3)

    for (i in 0 until arr.size) {
        print("${arr.get(i)} ")
    }
    println()

    for (i in 0 until arr.size) {
        print("${arr[i]} ")
    }
    println()

    // indices 索引区间 即 i>=0 && i<arr.size
    for (index in arr.indices) {
        print("${arr.get(index)} ")
    }
    println()

    for (index in arr.indices) {
        print("${arr.elementAt(index)} ")
    }
    println()

    println(arr.lastIndex)

    for ((index, value) in arr.withIndex()) {
        println("第${index + 1} 的值是 ${value}")
    }

    arr.forEach { print("${it} ") }
    println()

}