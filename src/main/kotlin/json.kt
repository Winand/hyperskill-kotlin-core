package org.example

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Human(var name: String, var age: Int, var friends: Array<String>, var grades: Map<String, String>)

fun main() {
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val human = Human("Mike", 20, arrayOf("Alex", "Valery", "Ann"), mapOf("CS" to "B"))
    val humanAdapter = moshi.adapter(Human::class.java)
    val str = humanAdapter.toJson(human)
    val human2: Human? = humanAdapter.fromJson(str)

    val type = Types.newParameterizedType(List::class.java, Human::class.java)
    val humanListAdapter = moshi.adapter<List<Human?>>(type)
    val strHumans = humanListAdapter.toJson(listOf(human, null, human2))
    val humans: List<Human?>? = humanListAdapter.fromJson(strHumans)
    println(strHumans)
}