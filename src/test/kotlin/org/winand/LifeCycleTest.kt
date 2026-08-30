/**
 * https://hyperskill.org/learn/step/25116
 */
package org.winand

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

// By default PER_METHOD is used. PER_CLASS is needed for
// beforeAll/afterAll, otherwise they should be static:
// put in `companion object {  }` with @JvmStatic annotation
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LifeCycleTest {
    constructor() {
        println("Test Class Constructor")
    }

    @BeforeAll
    fun beforeAll() {
        println("Before the test fixture")
    }

    @AfterAll
    fun afterAll() {
        println("After the test fixture")
    }

    @BeforeEach
    fun beforeEach() {
        println("Before each test")
    }

    @AfterEach
    fun afterEach() {
        println("After each test")
    }

    @Test
    fun test1() {
        println("Test 1")
    }

    @Test
    fun test2() {
        println("Test 2")
    }
}
