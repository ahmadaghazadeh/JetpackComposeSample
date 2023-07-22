package com.ahmad.aghazadeh.examples

fun main() {

    val car = Car("Red", "BMW")
    car.drive()

    val truck = Truck("Blue", "Volvo")
    truck.drive()
    truck.speedUp()

    val button = Button("First")
    button.onClick("New Message")

    val name: String = "Ahmad"

    val newString = name.append(" Use Extention Method")

    println(newString)

    println(" Use Extention Method".removeFirstAndLastChar())

    val person = Person(name = "Ahmad", lastName = "Aghazadeh", age = 41)
    println(person)
}

data class Person(val name: String, val lastName: String, val age: Int)

open class Car(private var color: String, private var model: String) {

//    init {
//        color = "Red"
//        model = "BMW"
//    }

    fun drive() {
        println("Driving $color $model")
    }

    open fun speedUp() {
        println("Speed up")
    }
}

class Truck(color: String, model: String) : Car(color, model) {
    override fun speedUp() {
        super.speedUp()
        println("xx")
    }
}

class Button(val label: String) : ClickEvent {
    override fun onClick(message: String) {
        println("Clicked by $message")
    }

}

interface ClickEvent {
    fun onClick(message: String)
}

fun String.append(toAppend: String): String {
    return this + toAppend
}

fun String.removeFirstAndLastChar(): String {
    return substring(1, length - 1)
}

