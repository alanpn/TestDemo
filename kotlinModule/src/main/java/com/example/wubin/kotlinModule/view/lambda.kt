package com.example.wubin.kotlinModule.view

val people = listOf(Employee("Alice", 29), Employee("Bob", 31))

fun main() {

//    fun1()
    //    run(::fun1)

//    fun2()

//    fun3()

//    fun4()

    fun5()

}


fun fun5() {
    // 可以用于对同一个对象执行多个操作而无需重复这个对象名
    println(alphabet())
}

fun alphabet(): String {
    val stringBuffer = StringBuffer()
    return with(stringBuffer) {

        for (letter in 'A'..'Z') {
            append(letter)
        }

        append("\nOK , I know it")

        toString()
    }
}

fun fun4() {
    // 是否所有元素都符合条件
    println(people.all { p: Employee -> p.age < 30 }) // false

    // 是否有至少一个匹配条件
    println(people.any { p: Employee -> p.age < 30 }) // true

    // 找出符合条件的元素 如果有多个元素只返回第一个 没有返回null
    // find 与 firstOrNull 相同
    println(people.firstOrNull { p: Employee -> p.age < 30 }) // Employee(name=Alice, age=29)
    println(people.find { p: Employee -> p.age > 35 }) // null

    // 有几个元素满足条件
    println(people.count { p: Employee -> p.age < 30 }) // 1

    // 分组
    println(people.groupBy { it.age }) // {29=[Employee(name=Alice, age=29)], 31=[Employee(name=Bob, age=31)]}

    // 处理嵌套集合中的元素
    val books = listOf(Book("alice", listOf("bill", "gates")), Book("grage", listOf("bill", "jobs")))
    // toSet 好处是去重
    println(books.flatMap { it.authors }.toSet()) // [bill, gates, jobs]

}

fun fun3() {
    println(people.filter { it.age > 30 })
    println(people.map { it.name })

    println(people.filter { it.age > 30 }.map { it.name })
    // asSequence 无需创建集合来保存过程中的中间结果 节省内存??
    println(people.asSequence().filter { it.age > 30 }.map { it.name }.toList())

    println(people.filter { it.age == people.maxBy(Employee::age)?.age })

    people.forEachIndexed { i, p -> print(" ${i} ${p.name} ") };println()
    people.forEach { print(" ${it.name} ${it.age} ") };println()

    val number = mapOf(0 to "aa", 1 to "bb")
    println(number.mapValues { it.value.toUpperCase() })

}

private fun fun2() {
    val sum = { x: Int, y: Int -> x + y }
    val sum1 = { x: Int, y: Int -> println(x + y) }
    val sum2 = { x: Int, y: Int ->
        val z = x + y
        println(z)
    }
    println(sum(1, 2))
    sum1(1, 2)
    sum2(1, 2)
}

private fun fun1() {

    println(people.maxBy { it.age })
    println(people.minBy { it.age })
    println(people.maxBy { p: Employee -> p.age })
    println(people.joinToString {
        it.name
    })

    // 增加分隔符
    println(people.joinToString(separator = " ", transform = { p: Employee -> p.name }))
    // 增加分隔符 上下效果一致 写法不同
    println(people.joinToString(" ") { p: Employee -> p.name })

//    val getAge = { p: Employee -> p.age }
    val getAge = Employee::age
    println(people.maxBy(getAge))

}

data class Employee(val name: String, val age: Int)
data class Book(val title: String, val authors: List<String>)