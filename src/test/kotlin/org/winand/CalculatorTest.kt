/**
 * Parameterized test https://hyperskill.org/learn/step/25384
 */
package org.winand

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.CsvFileSource
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.ValueSource

// PER_CLASS because argFactory method source should be static
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculatorTest {
    @Test
    fun `when adding 1 and 2 expect 3`() {
        val result = Calculator.add(1, 2)
        assertEquals(3, result)
    }

    @Test
    fun `when subtracting 6 from 11 expect 5`() {
        val result = Calculator.subtract(11, 6)
        assertEquals(5, result)
    }

    @Test
    fun `when multiplying 3 by 2 expect 6`() {
        val result = Calculator.multiply(3, 2)
        assertEquals(6, result)
    }

    @Test
    fun `when dividing 14 by 7 expect 2`() {
        val result = Calculator.divide(14, 7)
        assertEquals(2, result)
    }

    @Test
    fun `when dividing by 0 expect IllegalArgumentException`() {
        // Java API org.junit.jupiter.api.Assertions.assertThrows
        // assertThrows(IllegalArgumentException::class.java) { ... }
        assertThrows<IllegalArgumentException> {
            Calculator.divide(14, 0)
        }
    }

    @Test
    fun `when 8 is checked for evenness expect true`() {
        val result = Calculator.isEven(8)
        assertTrue(result)
    }

    @ParameterizedTest(name = "subtest {index}: maxOf({0}, {1}) == {2}")
    @CsvSource("2, 1, 2", "1, 2, 2", "2, 2, 2")
    fun testMaxOf(first: Int, second: Int, expected: Int) {
        val result = Calculator.maxOf(first, second)
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 2, 4, 1000])
    fun testIsEven(arg: Int) {
        assertTrue(Calculator.isEven(arg))
    }

    /**
     * test is called with an empty value of a String, List, Set, etc. type
     * @EmptySource/@NullSource can be combined with other source annotations
     * to provide null or empty value.
     */
    @ParameterizedTest
    @EmptySource
    fun testEmpty(arg: IntArray) {
        assertEquals(0, arg.size)
    }

    /**
     * test is called twice: with null and then with empty value.
     * the argument can be a String, List, Set, Map, etc.
     */
    @ParameterizedTest
    @NullAndEmptySource
    fun testNullAndEmpty(arg: List<String?>?) {
        // assertTrue(arg == null || arg.isEmpty())
        assertTrue(arg.isNullOrEmpty())
    }

    @ParameterizedTest
    @MethodSource("argFactory")
    @CsvFileSource(resources = ["/dataset.csv"], numLinesToSkip = 1)
    fun testStringLength(str: String, length: Int) {
        assertEquals(length, str.length)
    }

    fun argFactory() = listOf(
        arguments("apple", 5),
        arguments("watermelon", 10),
    )
}
