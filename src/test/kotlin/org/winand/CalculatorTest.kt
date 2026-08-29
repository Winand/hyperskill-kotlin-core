package org.winand

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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
}
