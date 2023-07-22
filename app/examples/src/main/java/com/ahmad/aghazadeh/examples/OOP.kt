package com.ahmad.aghazadeh.examples

fun main() {

//    val car = Car("Red", "BMW")
//    car.drive()
//
//    val truck = Truck("Blue", "Volvo")
//    truck.drive()
//    truck.speedUp()
//
//    val button = Button("First")
//    button.onClick("New Message")
//
//    val name: String = "Ahmad"
//
//    val newString = name.append(" Use Extention Method")
//
//    println(newString)
//
//    println(" Use Extention Method".removeFirstAndLastChar())
//
//    val ahmad = Person(name = "Ahmad", lastName = "Aghazadeh", age = 41)
//    val hamed = Person(name = "Hamed", lastName = "Roshangar", age = 41)
//    val shahrooz = Person(name = "Shahrooz", lastName = "Ansari", age = 41)
//
//    val listOfPerson = listOf(ahmad, hamed, shahrooz)
//    val finderPerson = Finder<Person>(listOfPerson)
//    finderPerson.findItem(hamed) {
//        println(it)
//    }
//
//    val listOfString = listOf<String>("Ahmad", "Aghazadeh", "Ahmad", "A")
//    val finder = Finder(listOfString)
//
//    finder.findItem("Ahmad") {
//        println(it)
//    }
//
//    val listofInt = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9)
//    val finderint = Finder<Int>(listofInt)
//    finderint.findItem(3) {
//        println(it)
//    }

    var input = Result.ERROR
    getResult(input)

    Repository.startFetch()
    getResult(result = Repository.getCurrentLoadState())
    Repository.finishFetch()
    getResult(result = Repository.getCurrentLoadState())

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

class Finder<T>(private val list: List<T>) {
    fun findItem(element: T, foundItem: (element: T?) -> Unit) {
        val itemFoundList = list.filter {
            it == element
        }
        if (itemFoundList.isNullOrEmpty()) {
            foundItem(null)
        } else {
            foundItem(itemFoundList[0])
        }
    }
}

fun getResult(result: Result) {
    return when (result) {
        Result.SUCCESS -> println("SUCCESS")
        Result.FAILURE -> println("FAILURE")
        Result.ERROR -> println("ERROR")
        Result.IDLE -> println("IDLE")
        Result.LOADING -> println("LOADING")
    }
}

enum class Result {
    SUCCESS,
    FAILURE,
    ERROR,
    IDLE,
    LOADING
}

object Repository {
    private var LoadState: Result = Result.IDLE
    private var dataFetched: String? = null
    fun startFetch() {
        LoadState = Result.LOADING
        dataFetched = "data"
    }

    fun finishFetch() {
        LoadState = Result.SUCCESS
        dataFetched = null
    }

    fun errorFetch() {
        LoadState = Result.ERROR
        dataFetched = null
    }

    fun getCurrentLoadState(): Result {
        return LoadState
    }
}