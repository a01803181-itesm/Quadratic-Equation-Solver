package com.example.quadraticequationsolver.viewmodel

import androidx.lifecycle.ViewModel
import com.example.quadraticequationsolver.model.Coefficients
import com.example.quadraticequationsolver.model.Result
import com.example.quadraticequationsolver.model.Solver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SolverVM : ViewModel() {
    val model: Solver = Solver()

    private val _state: MutableStateFlow<SolverState> = MutableStateFlow(SolverState())
    val state: StateFlow<SolverState> = _state

    fun updateQuadraticCoefficient(a: Float) {
        val b: Float = _state.value.coefficients.linear
        val c: Float = _state.value.coefficients.constant
        _state.value = _state.value.copy(coefficients = Coefficients(a, b, c))
    }

    fun updateLinearCoefficient(b: Float) {
        val a: Float = _state.value.coefficients.quadratic
        val c: Float = _state.value.coefficients.constant
        _state.value = _state.value.copy(coefficients = Coefficients(a, b, c))
    }

    fun updateConstantCoefficient(c: Float) {
        val a: Float = _state.value.coefficients.quadratic
        val b: Float = _state.value.coefficients.linear
        _state.value = _state.value.copy(coefficients = Coefficients(a, b, c))
    }

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