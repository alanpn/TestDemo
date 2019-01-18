import java.util.*

fun main(args: Array<String>) {

    realInputType(1)
    realInputType(1.0)
    realInputType("saa")

    var arr = emptyArray<String>()
    Arrays.toString(arr)

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
