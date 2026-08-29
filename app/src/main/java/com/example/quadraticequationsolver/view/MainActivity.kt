package com.example.quadraticequationsolver.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quadraticequationsolver.R
import com.example.quadraticequationsolver.ui.theme.QuadraticEquationSolverTheme
import com.example.quadraticequationsolver.viewmodel.SolverVM
import androidx.compose.runtime.collectAsState
import com.example.quadraticequationsolver.model.Solution
import kotlin.math.abs

class MainActivity : ComponentActivity() {
    private val viewModel: SolverVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuadraticEquationSolverTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(viewModel = viewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun App(viewModel: SolverVM, modifier: Modifier = Modifier) {
    val state = viewModel.state.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Header(title = "Ecuaciones de segundo grado")
        QuadraticEquation(modifier)
        Column {
            CoefficientInput(
                variable = "a",
                value = state.value.coefficients.quadratic,
                onValueChange = { value: Float -> viewModel.updateQuadraticCoefficient(value) }
            )
            CoefficientInput(
                variable = "b",
                value = state.value.coefficients.linear,
                onValueChange = { value: Float -> viewModel.updateLinearCoefficient(value) }
            )
            CoefficientInput(
                variable = "c",
                value = state.value.coefficients.constant,
                onValueChange = { value: Float -> viewModel.updateConstantCoefficient(value) }
            )
        }
        ButtonSolve(viewModel = viewModel)
        Column {
            SolutionComponent(label = "Raíz 1:", state.value.result.x1)
            SolutionComponent(label = "Raíz 2:", state.value.result.x2)
        }
    }
}

@Composable
fun ButtonSolve(viewModel: SolverVM, modifier: Modifier = Modifier) {
    val state = viewModel.state.collectAsState()
    Button(
        onClick = { viewModel.solve() },
        enabled = state.value.coefficients.quadratic != 0f,
        modifier = modifier.padding(all = 16.dp)
    ) {
        Text(text = "Resolver")
    }
}

@Composable
fun SolutionComponent(label: String, solution: Solution, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(all = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            modifier = modifier.padding(end = 8.dp),
            fontWeight = FontWeight.SemiBold
        )
        var value: String = "%.2f".format(if (solution.rationalCoefficient != 0f) solution.rationalCoefficient else 0f)
        if (solution.irrationalCoefficient != 0f) {
            value += if (solution.irrationalCoefficient > 0) " + " else " - "
            value += "%.2f".format(abs(solution.irrationalCoefficient))
            value += "i"
        }
        TextField(
            value = value,
            onValueChange = { },
            readOnly = true,
        )
    }
}

@Composable
fun CoefficientInput(variable: String, value: Float, onValueChange: (Float) -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(all = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$variable:",
            modifier = modifier.padding(end = 8.dp),
            fontWeight = FontWeight.SemiBold
        )
        TextField(
            value = if (value % 1f == 0f) value.toInt().toString() else value.toString(),
            onValueChange = {
                try {
                    onValueChange(it.toFloat())
                } catch (_: NumberFormatException) {
                    onValueChange(0f)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
fun Header(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif,
        fontSize = 24.sp
    )
}

@Composable
fun QuadraticEquation(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_quadratic_equation),
        contentDescription = "Ecuación cuadrática: ax² + bx + c = 0",
        modifier = modifier.size(width = 300.dp, height = 100.dp),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val viewModel = SolverVM()
    QuadraticEquationSolverTheme {
        App(viewModel = viewModel)
    }
}