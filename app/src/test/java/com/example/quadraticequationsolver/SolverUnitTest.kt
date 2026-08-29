package com.example.quadraticequationsolver

import com.example.quadraticequationsolver.model.Solver
import org.junit.Test
import org.junit.Assert.*
import kotlin.math.abs
import kotlin.math.sqrt

class SolverUnitTest {
    /**
     * Tests the [Solver.solve] method for a quadratic equation that results in complex roots.
     *
     * This test case uses coefficients (a=10, b=2, c=4) where the discriminant is negative.
     * It verifies that the solver correctly calculates and separates the rational part (-b / 2a)
     * and the irrational/imaginary part (sqrt(|D|) / 2a) of the roots.
     */
    @Test
    fun irrationalCase() {
        val a = 10f
        val b = 2f
        val c = 4f
        val model = Solver()
        val radicand: Float = abs((b * b) - (4f * a * c))
        val irrationalCoefficient: Float = sqrt(radicand) / (2f * a)
        val rationalCoefficient: Float = -b / (2f * a)
        val result = model.solve(a, b, c)
        assertNotEquals(0f, result.x1.irrationalCoefficient)
        assertNotEquals(0f, result.x2.irrationalCoefficient)
        assertEquals(irrationalCoefficient, result.x1.irrationalCoefficient)
        assertEquals(-irrationalCoefficient, result.x2.irrationalCoefficient)
        assertEquals(rationalCoefficient, result.x1.rationalCoefficient)
        assertEquals(rationalCoefficient, result.x2.rationalCoefficient)
    }

    /**
     * Tests the [Solver.solve] method for a quadratic equation that results in real roots.
     *
     * This test case uses coefficients (a=2, b=10, c=4) where the discriminant is positive.
     * It verifies that the solver correctly calculates the two roots as rational numbers
     */
    @Test
    fun rationalCase() {
        val a = 2f
        val b = 10f
        val c = 4f
        val model = Solver()
        val radicand: Float = (b * b) - (4f * a * c)
        val x1: Float = (-b + sqrt(radicand)) / (2f * a)
        val x2: Float = (-b - sqrt(radicand)) / (2f * a)
        val result = model.solve(a, b, c)
        assertEquals(0f, result.x1.irrationalCoefficient)
        assertEquals(0f, result.x2.irrationalCoefficient)
        assertEquals(x1, result.x1.rationalCoefficient)
        assertEquals(x2, result.x2.rationalCoefficient)
    }
}