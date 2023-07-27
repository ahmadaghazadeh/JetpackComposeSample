package com.ahmad.aghazadeh.jetpack.widets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val IconButtonSizeModifier = Modifier.size(40.dp)

@Composable
fun RoundIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit,
    tint: Color = Color.Black.copy(alpha = 0.8f),
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    elevation: Dp = 4.dp,
    contentDescription: String = ""
) {
    Card(
        modifier = modifier
            .clickable { onClick.invoke() }
            .then(IconButtonSizeModifier)
            .background(color = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        shape = CircleShape,
    ) {
        Box(
            modifier =
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(), contentAlignment = Alignment.Center
        ) {

            Icon(
                modifier = modifier,
                imageVector = imageVector,
                contentDescription = contentDescription,
                tint = tint
            )
        }

    }


}
