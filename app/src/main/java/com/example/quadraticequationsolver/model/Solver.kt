package com.example.quadraticequationsolver.model

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class Solver(a: Int, b: Int, c: Int) {
    private val coefficients: Coefficients = Coefficients(a, b, c)
    val result: Result = Solve()
    private fun Solve(): Result {
        var irrational = 0f
        val rational: Float = -coefficients.linear / (2f * coefficients.quadratic)
        var rationalRest = 0f
        if (SolveRadicand() < 0f) {
            irrational = sqrt(abs(SolveRadicand())) / (2f * coefficients.quadratic)
        } else {
            rationalRest = sqrt(SolveRadicand()) / (2f * coefficients.quadratic)
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
    private fun SolveRadicand(): Float = coefficients.linear.toFloat().pow(2) - 4 * coefficients.quadratic * coefficients.constant
}