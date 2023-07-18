package com.ahmad.aghazadeh.examples

fun main() {
    val myName = "something" // immutable
    var canChangeValue = "Android" // mutable
    print("Print $canChangeValue and $myName")

    var name: String = "Ahmad"
    print("Hello $name")

    println()
    val amount = 55

    when (amount) {
        in 1..100 -> print("This amount is between 1 and 100")
        999 -> print("Really Close")
        1000 -> print("Rich but not there")
        1100 -> print("You've made it!")
        else -> {
            print("$amount is just not going to work")
        }
    }

    calculate(1, 100, "Message")
    calculate(message = "Message")
}

fun calculate(first: Int = 1, second: Int = 100, message: String) {
    for (i in first..second) {
        println("$message %i")
    }
}

fun calculateCatAge(age: Int): Int {
    return age * 7
}

fun calculateCatAge2(age: Int): Int = age * 7
