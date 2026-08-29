package com.example.quadraticequationsolver.model

/**
 * Represents the final outcome of a quadratic equation calculation, containing two solutions.
 *
 * @property x1 The first root of the quadratic equation.
 * @property x2 The second root of the quadratic equation.
 */
data class Result(
    val x1: Solution = Solution(),
    val x2: Solution = Solution()
)