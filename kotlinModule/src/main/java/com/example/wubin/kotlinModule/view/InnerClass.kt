package com.example.wubin.kotlinModule.view

fun main(args: Array<String>) {

    InnerClass().callB()

    // nestedMethod()

}

class InnerClass {

    var a = 100
    var c = 1

    inner class B {

        var a = 200

        var b = 111

        fun test() {

            var a = 300

            // 如无匹配自动向外层搜索
            println(c)

            // 因为fun没有相同变量 输出111
            println(b)

            // 因为fun有相同变量会覆盖 输出300
            println("${a}")

            // 外面的a 输出200
            println("sfsf:  ${this@InnerClass.a}")

            // 下面两个结果相同 输出100
            println("sfsf:  ${this@B.a} == ${this.a}")

            // 修改值
            a = 400
            println(a)

            // 修改值
            this.a = 500
            println("${this.a}")

            // 修改值
            this@InnerClass.a = 600
            println("${this@InnerClass.a}")

        }

    }

    fun callB() {
        B().test()
    }
}

private fun nestedMethod() {

    var a = 0
    var obj = object {

        fun test() {
            a++
        }
    }
    obj.test()

    // 输出1
    println(a)

}





