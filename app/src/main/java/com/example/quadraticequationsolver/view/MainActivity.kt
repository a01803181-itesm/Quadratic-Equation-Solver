package com.example.quadraticequationsolver.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuadraticEquationSolverTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    val coefficients: List<String> = listOf("a", "b", "c")
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
            coefficients.forEach {
                CoefficientInput(variable = it)
            }
        }
        ButtonSolve()
        Column {
            SolutionComponent(label = "Raíz 1:")
            SolutionComponent(label = "Raíz 2:")
        }
    }
}

@Composable
fun ButtonSolve(modifier: Modifier = Modifier) {
    Button(
        onClick = { },
        modifier = modifier.padding(all = 16.dp)
    ) {
        Text(text = "Resolver")
    }
}

@Composable
fun SolutionComponent(label: String, modifier: Modifier = Modifier) {
    var value by remember { mutableStateOf("0") }
    Row(
        modifier = modifier.padding(all = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            modifier = modifier.padding(end = 8.dp),
            fontWeight = FontWeight.SemiBold
        )
        TextField(
            value = value,
            onValueChange = { value = it },
            readOnly = true,
        )
    }
}

@Composable
fun CoefficientInput(variable: String, modifier: Modifier = Modifier) {
    var amount by remember { mutableStateOf("0") }
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
            value = amount,
            onValueChange = { amount = it },
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
    QuadraticEquationSolverTheme {
        App()
    }
}