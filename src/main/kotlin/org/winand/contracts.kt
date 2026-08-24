package org.winand

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun String?.isNotNull(): Boolean {
    contract {
        returns(true) implies(this@isNotNull != null)
    }
    return this != null
}

//-----------------------------------------------------------
abstract class Animal {
    abstract fun doAnimalStuff()
}

class Cat : Animal() {
    override fun doAnimalStuff() { /* ... */
    }

    fun makeSound() {
        println("Meow!")
    }
}

class Dog : Animal() {
    override fun doAnimalStuff() { /* ... */
    }
}

@OptIn(ExperimentalContracts::class)
fun Animal.isCat(): Boolean {
    contract {
        returns(true) implies (this@isCat is Cat)
    }
    return this is Cat
}

//-----------------------------------------------------------
@OptIn(ExperimentalContracts::class)
fun myRun(block: () -> Unit) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return block()
}

//-----------------------------------------------------------
fun main() {
//    val s: String? = "hello"
//    if (s.isNotNull())
//        println(s.length)

    val cat: Animal = Cat()
    if (cat.isCat())
        cat.makeSound()

    val x: Int
    myRun { x = 10 }
    println(x)


    val b = readln().toBoolean()
    require(b) { "Should be 'true'!" }
    check(b) { "Should be 'true'!" }
    if (b) println(b)
    assert(false) { "Should be 'true'!" }  // disabled by default, pass -ea to JVM to enable
}