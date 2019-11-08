
1。4个访问控制符
    private 只能在该类内部或文件内部被访问
    internal 该类的内部或文件的内部或同一模块内被访问
    protected 该类内部或文件内部或子类被访问 取消包访问权限
    public 任意地方访问

    默认访问控制符public

2。类和方法
    kotlin中默认类和方法是final。
    如果你允许创建一个类的子类，需要使用open 修饰符来标示这个类

3.接口与抽象类
    interface IF {

        val a: Int
    //    var b: Int = 5 // 失败 interface 不能初始化值

        fun test()
        fun test1() {

        }
    }

    abstract class AB {

        abstract val a: Int
        var b: Int = 5

        abstract fun test()
        fun test1() {

        }
    }

4。因为接口的特殊性 突破了java的多继承 ??
    class A : IF1, IF2 {

    }

    interface IF2 {
        fun test1() {
            println("test1")
        }
    }

    interface IF1 {
        fun test() {
            println("test")
        }
    }

5.