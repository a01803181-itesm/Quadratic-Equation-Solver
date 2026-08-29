package com.example.quadraticequationsolver.model

/**
 * Data class representing the coefficients of a quadratic equation in the standard form:
 * ax² + bx + c = 0.
 *
 */
data class Coefficients(
    val quadratic: Float = 0f,
    val linear: Float = 0f,
    val constant: Float = 0f
)