package com.example.quadraticequationsolver.model

/**
 * Represents a single root of a quadratic equation, expressed in the form:
 * `rationalCoefficient + (irrationalCoefficient * sqrt(discriminant))`.
 *
 * @property rationalCoefficient The rational part of the solution.
 * @property irrationalCoefficient The coefficient of the square root part of the solution.
 */
data class Solution(
    val rationalCoefficient: Float = 0f,
    val irrationalCoefficient: Float = 0f
)