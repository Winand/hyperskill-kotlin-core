package org.example

data class Musician(var name: String = "", var band: String = "", var role: String = "")

fun main() {
    val musician = Musician()

    val num = musician.apply {
        name = readln()
        band = readln()
        role = readln()
    }.run {
        println(this)
        name.length
    }.let {
        val res = it / 4
        println(res)
        res
    }.let {
        it + 10
    }
    val c = "a b c".split(' ')
    val c3 = c.run {
        println(size)
        map { it.lowercase() to it.length }
    }
    val c2 = c.run {
        println(size)
        map(String::lowercase)
    }
//    val c4 = c.apply { return 7 }
}
