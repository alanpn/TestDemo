/**
 * class 属性扩展
 */
fun main(args: Array<String>) {

    var user = User("last", "first")
    println(user.fullName)

    user.fullName = "last.first"
    println(user.first + "++" + user.last)

}

class User(var first: String, var last: String)

var User.fullName: String
    get() = "${first}.${last}"
    set(value) {
        println("set method")
        if ("." !in value || value.indexOf(".") != value.lastIndexOf(".")) {
            println("illeagal")
        } else {
            var arr = value.split(".")
            first = arr[0]
            last = arr[1]
        }
    }

