package com.example.quadraticequationsolver.viewmodel

import androidx.lifecycle.ViewModel
import com.example.quadraticequationsolver.model.Coefficients
import com.example.quadraticequationsolver.model.Result
import com.example.quadraticequationsolver.model.Solver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel responsible for managing the state and logic of the quadratic equation solver.
 *
 * This class acts as a bridge between the [Solver] model and the UI, handling updates to
 * equation coefficients and triggering the calculation of roots. It exposes the current
 * [SolverState] via a [StateFlow] to ensure the UI stays synchronized with the data.
 */
class SolverVM : ViewModel() {
    val model: Solver = Solver()

    private val _state: MutableStateFlow<SolverState> = MutableStateFlow(SolverState())
    val state: StateFlow<SolverState> = _state

    /**
     * Updates the quadratic coefficient (a) in the current state while preserving the
     * linear and constant coefficients.
     *
     * @param a The new value for the quadratic coefficient.
     */
    fun updateQuadraticCoefficient(a: Float) {
        val b: Float = _state.value.coefficients.linear
        val c: Float = _state.value.coefficients.constant
        _state.value = _state.value.copy(coefficients = Coefficients(a, b, c))
    }

    /**
     * Updates the linear coefficient (b) in the current state while preserving the
     * quadratic and constant coefficients.
     *
     * @param b The new value for the linear coefficient.
     */
    fun updateLinearCoefficient(b: Float) {
        val a: Float = _state.value.coefficients.quadratic
        val c: Float = _state.value.coefficients.constant
        _state.value = _state.value.copy(coefficients = Coefficients(a, b, c))
    }

    /**
     * Updates the constant coefficient (c) in the current equation state.
     *
     * @param c The new value for the constant coefficient.
     */
    fun updateConstantCoefficient(c: Float) {
        val a: Float = _state.value.coefficients.quadratic
        val b: Float = _state.value.coefficients.linear
        _state.value = _state.value.copy(coefficients = Coefficients(a, b, c))
    }

    /**
     * Solves the quadratic equation based on the current coefficients stored in the state.
     *
     * This function retrieves the quadratic (a), linear (b), and constant (c) coefficients
     * from the view state, uses the [model] to calculate the roots, and updates the
     * [_state] with the resulting [Result].
     */
    fun solve() {
        val coefficients: Coefficients = _state.value.coefficients
        val result: Result = model.solve(
            coefficients.quadratic,
            coefficients.linear,
            coefficients.constant
        )
        _state.value = _state.value.copy(result = result)
    }
}