/**
 * https://hyperskill.org/learn/step/15503
 */
package org.winand

fun whatever() {
    val rangeStart = 'C'
    val rangeEnd = 'Y'
    val findLetter = 'Q'
    for (c in rangeStart..rangeEnd) {
        if (c == findLetter) {
            println(
                "Character $findLetter is within range $rangeStart–$rangeEnd"
            )
            return
        }
    }
    println("Character $findLetter is not within range $rangeStart–$rangeEnd")
}

fun fibo() {
    var fibonacciPrevious = 1
    var fibonacciCurrent = 1
    var fibonacciSum = fibonacciPrevious + fibonacciCurrent

    while(true) {
        val tmp = fibonacciPrevious + fibonacciCurrent
        fibonacciPrevious = fibonacciCurrent
        fibonacciCurrent = tmp
        fibonacciSum += fibonacciCurrent
    }
}

fun main() {
    fibo()
    whatever()

    class Cat(val name: String, val age: Int) {
        val enoughCat = false // Of course, it's a false, there are never enough cats!
        init {
            check(!enoughCat) { "You cannot add a new cat" }  // state check may raise IllegalStateException
            // positive age is an example of an *invariant*
            require(age >= 0) { "Invalid age: $age" }  // argument check may raise IllegalArgumentException
        }
    }
    val casper = Cat("Casper", -1)
}
