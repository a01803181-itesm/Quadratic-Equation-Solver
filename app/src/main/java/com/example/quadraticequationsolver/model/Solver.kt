package com.example.quadraticequationsolver.model

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * A utility class responsible for solving quadratic equations of the form:
 * ax² + bx + c = 0.
 *
 * This class calculates the roots (x1 and x2) using the quadratic formula,
 * providing support for both real and complex (imaginary) solutions.
 */
class Solver {
    /**
     * Solves a quadratic equation of the form ax² + bx + c = 0.
     *
     * This function calculates the two roots of the equation using the quadratic formula.
     * It handles both real and complex (imaginary) solutions, returning them encapsulated
     * within a [Result] object.
     *
     * @param a The coefficient of the quadratic term (x²).
     * @param b The coefficient of the linear term (x).
     * @param c The constant term.
     * @return A [Result] containing the two solutions (x1 and x2).
     */
    fun solve(a: Float, b: Float, c: Float): Result {
        var irrational = 0f
        val rational: Float = -b / (2 * a)
        var rationalRest = 0f
        if (solveRadicand(a, b, c) < 0f) {
            irrational = sqrt(abs(solveRadicand(a, b, c))) / (2 * a)
        } else {
            rationalRest = sqrt(solveRadicand(a, b, c)) / (2 * a)
        }
        return Result(
            x1 = Solution(
                rationalCoefficient = rational + rationalRest,
                irrationalCoefficient = irrational
            ),
            x2 = Solution(
                rationalCoefficient = rational - rationalRest,
                irrationalCoefficient = if (irrational == 0f) 0f else -irrational
            )
        )
    }
    /**
     * Calculates the discriminant ($b^2 - 4ac$) of the quadratic equation.
     *
     * The discriminant is used to determine the nature of the roots (real or complex).
     *
     * @param a The coefficient of the quadratic term ($x^2$).
     * @param b The coefficient of the linear term ($x$).
     * @param c The constant term.
     * @return The calculated value of the radicand.
     */
    private fun solveRadicand(a: Float, b: Float, c: Float): Float = b.pow(2) - 4 * a * c
}