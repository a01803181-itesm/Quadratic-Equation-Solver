package com.example.quadraticequationsolver.model

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class Solver {
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
    private fun solveRadicand(a: Float, b: Float, c: Float): Float = b.pow(2) - 4 * a * c
}