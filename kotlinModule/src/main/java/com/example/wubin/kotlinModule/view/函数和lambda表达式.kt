package com.example.wubin.kotlinModule.view

/**
 * @author wubin
 * @description
 * @date 2019-08-27
 */
fun main() {

//    单函数表达式()

//    命名参数()

//    形参默认值()

//    可变参数()

//    局部函数()

//    使用函数类型()

//    使用函数类型作为形参类型()

//    使用函数类型作为返回值类型()

//    使用lambda表达式代替局部函数()

    调用lambda表达式()

}

fun 调用lambda表达式() {

    var s1 = { n: Int -> n * n }
    println(s1(2))

    var s2 = { n: Int -> n * n }(3)
    println(s2)

    var s3: (Int) -> Int = { n -> n * n }
    println(s3(4))

    var s4: (Int) -> Int = { it * it }
    println(s4(5    ))

}

fun 使用lambda表达式代替局部函数() {

    var myfun = getMathFunc1("square")
    println(myfun(3))

    myfun = getMathFunc1("cube")
    println(myfun(3))

    myfun = getMathFunc1("factorial")
    println(myfun(3))

}

fun getMathFunc1(type: String): (Int) -> Int {

    when (type) {
        "square" -> return { n: Int ->
            n * n
        }
        "cube" -> return { n: Int ->
            n * n * n
        }
        else -> return { n: Int ->
            var result = 1
            for (index in 2..n) {
                result *= index
            }
            result
        }
    }

}

fun 使用函数类型作为返回值类型() {

    var mathFunc = getMathFunc("square")
    println(mathFunc(3))

    mathFunc = getMathFunc("factorial")
    println(mathFunc(3))

    mathFunc = getMathFunc("cube")
    println(mathFunc(3))

    println(getMathFunc("square")(3))

}

fun getMathFunc(type: String): (Int) -> Int {

    fun square(n: Int): Int {
        return n * n
    }

    fun cube(n: Int): Int {
        return n * n * n
    }

    fun factorial(n: Int): Int {
        var result = 1
        for (index in 2..n) {
            result *= index
        }
        return result
    }

    when (type) {
        "square" -> return ::square
        "cube" -> return ::cube
        else -> return ::factorial
    }

}

fun 使用函数类型作为形参类型() {

    var data = arrayOf(1, 2, 3)
    println(map(data, ::square).contentToString())
    println(map(data, ::cube).contentToString())
    println(map(data, ::factorial).contentToString())

}

fun map(data: Array<Int>, fn: (Int) -> Int): Array<Int> {

    var result = Array<Int>(data.size, { 0 })
    for (index in data.indices) {
        result[index] = fn(data[index])
    }

    return result

}

fun square(n: Int): Int {
    return n * n
}

fun cube(n: Int): Int {
    return n * n * n
}

fun factorial(n: Int): Int {
    var result = 1
    for (index in 2..n) {
        result *= index
    }
    return result
}

fun 使用函数类型() {

    /* 定义myfun类型 可省略 */
    var myfun: (Int, Int) -> Int

    // 将 plus方法 赋值给 myfun
    myfun = ::plus
    println(myfun(1, 1))

    myfun = ::plus1
    println(myfun(1, 1))

}

fun 局部函数() {

    getMathFunc("square", 10)
    getMathFunc("cube", 10)
    getMathFunc("factorial", 3)
    getMathFunc("", 1)

}

fun getMathFunc(type: String, num: Int) {

    fun square(n: Int): Int {
        return n * n
    }

    fun cube(n: Int): Int {
        return n * n * n
    }

    fun factorial(n: Int): Int {
        var result = 1
        for (index in 2..n) {
            result *= index
        }
        return result
    }

    var result = when (type) {
        "square" -> square(num)
        "cube" -> cube(num)
        "factorial" -> factorial(num)
        else -> -1
    }

    println(result)

}

fun 可变参数() {

    test("a")
    test("a", "b")

    var arr = arrayOf(1, 2, 3)
    test1(arr)

}

fun test1(arr: Array<Int>) {
    arr.forEach { print("${it} ") }
}

fun test(vararg strs: String) {
    strs.forEach { print("${it} ") }
    println()
}

fun 形参默认值() {

    sayHi()
    sayHi("say")
    sayHi(message = "hi")
    sayHi("say", "hi")
    sayHi(message = "hi", name = "say")

}

fun sayHi(name: String = "Hello", message: String = "Kotlin") {
    println("${name} ${message}")
}

private fun 命名参数() {

    plus3(1, 2)
    plus3(1, b = 2)
    plus3(b = 2, a = 1)

}

private fun 单函数表达式() {

    println(plus(1, 2))
    println(plus1(1, 2))

}

fun plus(a: Int, b: Int): Int {
    return a + b
}

fun plus1(a: Int, b: Int): Int = a + b

fun plus3(a: Int, b: Int) {
    println("${a}  ${b}")
}