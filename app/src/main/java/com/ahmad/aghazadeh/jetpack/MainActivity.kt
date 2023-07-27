package com.ahmad.aghazadeh.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmad.aghazadeh.jetpack.ui.theme.JetpackTheme
import com.ahmad.aghazadeh.jetpack.widets.RoundIconButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.padding(8.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}


@Preview(showBackground = false)
@Composable
fun MyApp(onValChange: (String) -> Unit = {}) {

    var totalBillState by remember {
        mutableStateOf("")
    }

    val validateBillState = remember(totalBillState) {
        totalBillState.trim().isNotEmpty()
    }

    var sliderTipValue by remember {
        mutableStateOf(0.35f)
    }

    var splitValue by remember {
        mutableStateOf(1)
    }

    var tipAmount by remember(totalBillState) {
        mutableStateOf(0.0)
    }

    val tipPerPerson = remember(tipAmount, splitValue, totalBillState) {
        tipAmount / splitValue
    }

    val keyboardController = LocalFocusManager.current


    Column {
        TopHeader(tipPerPerson)

        TipMainContent(
            totalBillState = totalBillState,
            validateBillState = validateBillState,
            onTotalBillChange = {
                totalBillState = it
            },
            onSplitValueChange = {
                splitValue = it
            },
            onPercentChange = {
                sliderTipValue = it
                tipAmount = calculateTotalTip(it.toDouble(), totalBillState.toInt())
            },
            keyboardController,
            splitValue,
            sliderTipValue,
            tipAmount
        )
    }
}

@Composable
fun TopHeader(totalPerPerson: Double = 134.0) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(
                shape = CircleShape.copy(all = CornerSize(12.dp)),
            ), color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var total = "$%.0f".format(totalPerPerson)
            Text(
                text = "Total Per Person",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.inversePrimary
            )
            Text(
                text = total,
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TipMainContent(
    totalBillState: String,
    validateBillState: Boolean,
    onTotalBillChange: (String) -> Unit,
    onSplitValueChange: (Int) -> Unit,
    onPercentChange: (Float) -> Unit,
    keyboardController: FocusManager,
    splitValue: Int,
    sliderTipValue: Float,
    tipAmount: Double
) {

    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(250.dp),
        shape = CircleShape.copy(all = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = totalBillState,
                onValueChange = { onTotalBillChange(it) },
                label = { Text("Enter Bill") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.AttachMoney,
                        contentDescription = "Money Icon"
                    )
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions {
                    if (!validateBillState) return@KeyboardActions
                    onTotalBillChange(totalBillState.trim())
                    keyboardController.clearFocus()
                }
            )
            if (validateBillState) {
                CreateSplit(splitValue, onValChange = onSplitValueChange)
                CreateTip(tipAmount)
                CreateSliderTip(
                    sliderTipValue = sliderTipValue,
                    onValueChange = onPercentChange
                )
            }
        }
    }
}

fun calculateTotalTip(
    totalBill: Double,
    tipPercentage: Int
): Double {
    return ((totalBill * tipPercentage))
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun CreateSliderTip(sliderTipValue: Float, onValueChange: (Float) -> Unit) {
    var value = (sliderTipValue * 100).toInt()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "%${value}",
            style = MaterialTheme.typography.bodyLarge,
        )
        Slider(
            value = sliderTipValue, onValueChange = onValueChange,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            steps = 100,
        )
    }
}


@Composable
fun CreateTip(tipAmount: Double) {

    Row(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Tip")
        Spacer(modifier = Modifier.weight(1.0f))
        Text(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp),
            text = "$%.0f".format(tipAmount)
        )
    }
}

@Composable
private fun CreateSplit(
    splitValue: Int,
    onValChange: (Int) -> Unit,
) {
    val range = IntRange(start = 0, endInclusive = 100)
    Row(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Split")
        Spacer(modifier = Modifier.weight(1.0f))
        RoundIconButton(
            imageVector = Icons.Default.Remove,
            contentDescription = "Remove",
            onClick = {
                if (splitValue > 1) {
                    onValChange((splitValue - 1))
                }
            }
        )
        Text(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .width(25.dp),
            text = "$splitValue"
        )
        RoundIconButton(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            onClick = {
                if (splitValue < range.last) {
                    onValChange((splitValue + 1))
                }
            }
        )
    }
}
