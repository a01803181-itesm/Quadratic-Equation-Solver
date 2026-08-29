package com.example.quadraticequationsolver.viewmodel

import com.example.quadraticequationsolver.model.Coefficients
import com.example.quadraticequationsolver.model.Result

data class SolverState(
    val coefficients: Coefficients = Coefficients(),
    val result: Result = Result()
)
