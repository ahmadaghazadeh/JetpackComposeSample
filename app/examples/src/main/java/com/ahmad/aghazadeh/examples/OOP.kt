package com.ahmad.aghazadeh.examples

fun main() {

    val car = Car("Red", "BMW")
    car.drive()

    val truck = Truck("Blue", "Volvo")
    truck.drive()
    truck.speedUp()
}

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