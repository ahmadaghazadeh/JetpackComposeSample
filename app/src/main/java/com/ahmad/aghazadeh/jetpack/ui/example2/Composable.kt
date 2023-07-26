package com.ahmad.aghazadeh.jetpack.ui.example2

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
@Composable
fun MyApp() {
//    var monenyC = remember {
//        mutableStateOf(0)
//    }
//    monenyC.value += 1
    var moneyCounter by remember {
        mutableStateOf(0)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), color = Color(0xff546e7a)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$ $moneyCounter",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Spacer(modifier = Modifier.height(100.dp))
            CreateCircles(moneyCounter = moneyCounter) {
                moneyCounter++
            }
        }
    }
}

@Composable
fun CreateCircles(moneyCounter: Int = 0, updateMoneyCounter: (Int) -> Unit = {}) {

    Card(
        modifier = Modifier
            .padding(3.dp)
            .size(100.dp)
            .clickable {
                updateMoneyCounter(moneyCounter)
                Log.d("moneyCounter", moneyCounter.toString())
            },

        shape = CircleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),

        ) {
        Box(
            modifier =
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(), contentAlignment = Alignment.Center
        ) {
            Text(text = "Tap", modifier = Modifier)
        }
    }
}