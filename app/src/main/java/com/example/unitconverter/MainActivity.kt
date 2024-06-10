package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("") }
    var outputUnit by remember { mutableStateOf("") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(0.001) }

    fun theConverter() {

        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val results = (inputValueDouble * conversionFactor.value * 100.0).roundToInt() / 100.0
        outputValue = results.toString()
    }

    // here all the elements will be stacked below each other
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Unit Converter")
        Spacer(modifier = Modifier.height(16.dp))
        // inside the onValueChange goes what should happen,
        // when the value of our OutlinedTextField changes.
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it},
            // here goes what should happen, when the value of our
            // outline
            label = { Text(text = "Enter a Value")}
        )

        Spacer(modifier = Modifier.height(16.dp))
        // here all the elements will be stacked next to each other
        Row {
            // input box
            Box {
                // input button
                Button(onClick = {iExpanded = true}) {
                    Text(text = "Select")
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded = false}) {
                    DropdownMenuItem(text = { Text("Millimeter")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeter"
                            conversionFactor.value = 0.0001})
                    DropdownMenuItem(text = { Text("Centimeter")},
                        onClick = { /*TODO*/ })
                    DropdownMenuItem(text = { Text("Meter")},
                        onClick = { /*TODO*/ })
                    DropdownMenuItem(text = { Text("Kilometer")},
                        onClick = { /*TODO*/ }
                    )
                }

            }

            Spacer(modifier = Modifier.width(16.dp))

            // output box
            Box {
                // output button
                Button(onClick = {oExpanded = true}) {
                    Text(text = "Select")
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {oExpanded = false}) {
                    DropdownMenuItem(text = { Text(text = "Millimeter")},
                        onClick = { /*TODO*/ })
                    DropdownMenuItem(text = { Text(text = "Centimeter")},
                        onClick = { /*TODO*/ })
                    DropdownMenuItem(text = { Text(text = "Meter")},
                        onClick = { /*TODO*/ })
                    DropdownMenuItem(text = { Text(text = "Kilometer")},
                        onClick = { /*TODO*/ }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "The results")
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()

}
