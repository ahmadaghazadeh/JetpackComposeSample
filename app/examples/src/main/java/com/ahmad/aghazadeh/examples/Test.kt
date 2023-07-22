package com.ahmad.aghazadeh.examples

fun main() {
//    exmapleOfDeclaration()
//
//    exampleOfBranch()
//
//    exampleOfFuction()
//
//    asyncTask("Hello", before = { println("Before") }, after = { println("After") })
//
//    exampleOfLists()

    // exampleOfSet()

    //exampleOfMap()

    //collectionEmpty()

    filtterCollection()

}

fun filtterCollection() {
    val myCollection = listOf("Ahmad", "Shahrooz", "Hamed", "Danial")
    val found = myCollection.filter {
        it.startsWith("S", ignoreCase = true) || it.endsWith("D", ignoreCase = true)
    }
    println(found)
}

fun collectionEmpty() {
    val MyCollection = emptyArray<String>()
    MyCollection[0] = ""
}

fun exampleOfMap() {
    // Key value pair
    val secretMap = mapOf("Up" to 1, "Down" to 2, "Left" to 3, "Right" to 4)
    println(secretMap)
    println(secretMap.keys + " " + secretMap.values)
    if ("Up" in secretMap) {
        println("Yes it is")
    }
    val myMutableMap = mutableMapOf(1 to "Ahmad", 2 to "Shahrooz")
    myMutableMap.put(3, "Hamed")
    myMutableMap[3] = "Hamed"
}

fun exampleOfSet() {
    // Set is unique
    val mySet = setOf(1, 2, 3, 4, 5, 1)
    println(mySet)

    val myMutableSet = mutableSetOf(1, 2, 3)
    myMutableSet.add(2)
    println(myMutableSet)

}

private fun exmapleOfDeclaration() {
    val myName = "something" // immutable
    var canChangeValue = "Android" // mutable
    print("Print $canChangeValue and $myName")

    var name: String = "Ahmad"
    print("Hello $name")
}

private fun exampleOfFuction() {
    calculate(1, 100, "Message")
    calculate(message = "Message")
    enhanceMessage(message = "") {
        add(12, 12)
    }

    enhanceMessage(message = "") {
        12
    }
}

private fun exampleOfBranch() {
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
}


private fun exampleOfLists() {
    val myListOfName = listOf("Ahmad", "Hamed", "Shahrozi")
    val myMutableListOfName = mutableListOf("Ahmad", "Hamed", "Shahrozi")
    myMutableListOfName.add("Ahmad")

    myListOfName.forEach { name ->
        println(name)
    }
    myMutableListOfName.removeAt(0)
    for (name in myMutableListOfName) {
        println(name)
    }

    println(myMutableListOfName.get(0))

    println(myMutableListOfName[0])

    calculate(1, 100, "Message")
    calculate(message = "Message")
    enhanceMessage(message = "") {
        add(12, 12)
    }

    enhanceMessage(message = "") {
        12
    }

    asyncTask("Hello", before = { println("Before") }, after = { println("After") })

 
}

fun calculate(first: Int = 1, second: Int = 100, message: String) {
    for (i in first..second) {
        println("$message %i")
    }
}

fun calculateCatAge(age: Int): Int {
    return age * 7
}

val calculateCatAgeLambda: (Int) -> Int = { age -> age * 7 }

val calculateCatAgeLambda2: (Int) -> Int = { it * 7 }


fun calculateCatAge2(age: Int): Int = age * 7

fun add(a: Int, b: Int): Int {
    return a + b
}

val addLambda: (Int, Int) -> Int = { a, b -> a + b }

fun showName(name: String) {
    print(name)
}

val showNameLambda: (String) -> Unit = { name -> print(name) }

fun enhanceMessage(message: String, funAsParameter: () -> Int) {
    print("$ message ${funAsParameter()}")
}

fun asyncTask(message: String, before: () -> Unit, after: () -> Unit) {
    before()
    println("${message}")
    after()
}
 