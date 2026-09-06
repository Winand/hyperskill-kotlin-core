/**
 * https://hyperskill.org/learn/step/38850
 * https://hyperskill.org/learn/step/38856
 */
package org.winand

import kotlin.concurrent.Volatile
import kotlin.concurrent.thread

object SharedCounter {
    @Volatile
    var value: Int = 0
    @Synchronized
    fun increment() {
        value++
    }
    @Synchronized
    fun decrement() {
        value--
    }
}

fun main() {
    val thread1 = thread(start = true, name = "Thread-1") {
        println("Thread 1 started")
        Thread.sleep(500)
        println("Thread 1 finished")
    }
    val thread2 = thread(start = true, name = "Thread-2") {
        println("Thread 2 started")
        Thread.sleep(500)
        println("Thread 2 finished")
    }
    thread1.join()
    thread2.join()
    println("Threads finished")

    // -------------------------
    val threads = List(10) {
        Thread {
            for (i in 1..100) {
                SharedCounter.increment()
                // Set a conditional breakpoint here to trigger when SharedCounter.value is a specific value
                // Use Evaluate Expression to inspect SharedCounter.value
                Thread.sleep(10) // Simulate work
                SharedCounter.decrement()
                // Set a watchpoint on SharedCounter.value
            }
        }
    }
    threads.forEach { it.start() }
    threads.forEach { it.join() }
    println("Final counter value: ${SharedCounter.value}")
    // Use a profiler to check for performance issues
}