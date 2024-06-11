package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    var inputUnit by remember { mutableStateOf("Meter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val iConversionFactor = remember { mutableStateOf(1.0) }
    val oConversionFactor = remember { mutableStateOf(1.0) }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Serif,
        fontSize = 25.sp,
        color = Color.DarkGray
    )

    fun theConverter() {

        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val results = (inputValueDouble * iConversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = results.toString()
    }

    // here all the elements will be stacked below each other
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(50.dp))
        Text("Unit Converter", style =  customTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        // inside the onValueChange goes what should happen,
        // when the value of our OutlinedTextField changes.
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            theConverter()},
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
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded = false}) {
                    DropdownMenuItem(text = { Text("Millimeter")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeter"
                            iConversionFactor.value = 0.001
                            theConverter()
                        })
                    DropdownMenuItem(text = { Text("Centimeter")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeter"
                            iConversionFactor.value = 0.01
                            theConverter()
                        })
                    DropdownMenuItem(text = { Text("Meter")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meter"
                            iConversionFactor.value = 1.00
                            theConverter()
                        })
                    DropdownMenuItem(text = { Text("Kilometer")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Kilometer"
                            iConversionFactor.value = 1000.00
                            theConverter()
                        })
                    DropdownMenuItem(text = { Text("Inches")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Inches"
                            iConversionFactor.value = 0.0254
                            theConverter()
                        })
                    DropdownMenuItem(text = { Text("Feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            iConversionFactor.value = 0.3048
                            theConverter()
                        })
                    DropdownMenuItem(text = { Text("Mile")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Mile"
                            iConversionFactor.value = 1609.34
                            theConverter()
                        }
                    )
                }

            }

            Spacer(modifier = Modifier.width(16.dp))

            // output box
            Box {
                // output button
                Button(onClick = {oExpanded = true}) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {oExpanded = false}) {
                    DropdownMenuItem(text = { Text(text = "Millimeter")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Millimeter"
                            oConversionFactor.value = 0.001
                            theConverter()
                        })
                    DropdownMenuItem(text = { Text(text = "Centimeter")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeter"
                            oConversionFactor.value = 0.01
                            theConverter()
                        })
                    DropdownMenuItem(text = { Text(text = "Meter")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meter"
                            oConversionFactor.value = 1.00
                            theConverter()
                        })
                    DropdownMenuItem(text = { Text(text = "Kilometer")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Kilometer"
                            oConversionFactor.value = 1000.00
                            theConverter()
                        })
                    DropdownMenuItem(text = { Text("Inches")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Inches"
                            oConversionFactor.value = 0.0254
                            theConverter()
                        })
                    DropdownMenuItem(text = { Text("Feet")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            theConverter()
                        })
                    DropdownMenuItem(text = { Text("Mile")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Mile"
                            oConversionFactor.value = 1609.34
                            theConverter()
                        }
                    )

                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("The results: ${outputValue} ${outputUnit}",
            style = MaterialTheme.typography.titleLarge,
            color = Color.DarkGray)
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()

}
