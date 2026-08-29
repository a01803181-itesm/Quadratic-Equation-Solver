package com.example.quadraticequationsolver.viewmodel

import com.example.quadraticequationsolver.model.Coefficients
import com.example.quadraticequationsolver.model.Result

/**
 * Represents the UI state for the quadratic equation solver.
 *
 * @property coefficients The input coefficients (a, b, and c) for the quadratic equation.
 * @property result The calculated result of the quadratic equation, including roots and discriminant.
 */
data class SolverState(
    val coefficients: Coefficients = Coefficients(),
    val result: Result = Result()
)
