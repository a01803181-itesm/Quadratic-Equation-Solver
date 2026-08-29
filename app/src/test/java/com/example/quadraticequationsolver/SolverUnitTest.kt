package com.example.quadraticequationsolver

import com.example.quadraticequationsolver.model.Solver
import org.junit.Test
import org.junit.Assert.*
import kotlin.math.abs
import kotlin.math.sqrt

class SolverUnitTest {
    @Test
    fun irrationalCase() {
        val a = 10
        val b = 2
        val c = 4
        val model = Solver(a = a, b = b, c = c)
        val radicand: Float = abs((b * b) - (4f * a * c))
        val irrationalCoefficient: Float = sqrt(radicand) / (2f * a)
        val rationalCoefficient: Float = -b / (2f * a)
        assertNotEquals(0f, model.result.x1.irrationalCoefficient)
        assertNotEquals(0f, model.result.x2.irrationalCoefficient)
        assertEquals(irrationalCoefficient, model.result.x1.irrationalCoefficient)
        assertEquals(-irrationalCoefficient, model.result.x2.irrationalCoefficient)
        assertEquals(rationalCoefficient, model.result.x1.rationalCoefficient)
        assertEquals(rationalCoefficient, model.result.x2.rationalCoefficient)
    }

    @Test
    fun rationalCase() {
        val a = 2
        val b = 10
        val c = 4
        val model = Solver(a = a, b = b, c = c)
        val radicand: Float = (b * b) - (4f * a * c)
        val x1: Float = (-b + sqrt(radicand)) / (2f * a)
        val x2: Float = (-b - sqrt(radicand)) / (2f * a)
        assertEquals(0f, model.result.x1.irrationalCoefficient)
        assertEquals(0f, model.result.x2.irrationalCoefficient)
        assertEquals(x1, model.result.x1.rationalCoefficient)
        assertEquals(x2, model.result.x2.rationalCoefficient)
    }
}