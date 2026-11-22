package org.winand
import java.math.BigDecimal
import java.math.BigInteger
//import java.math.BigInteger.TWO

fun main() {
//    val a = BigInteger(readln())
//    val b = BigInteger(readln())
//    val (q, r) = a.divideAndRemainder(b)
//    println("$a = $b * $q + $r")

    var first = BigDecimal("0.2")
    // decrement
        val decrement = --first //  -0.8
    // increment
        val increment = ++first //  0.2
    // unary minus, turning to opposite sign
        val reverse = -first  //  -0.2
    // absolute value
        val module = first.abs()  //  0.2
    // raise to the power, works only with Int
        val power = first.pow(3)
    println(decrement)
    println(increment)
    println(reverse)
    println(module)
    println(power)
    println((BigDecimal("0.2")++))
}