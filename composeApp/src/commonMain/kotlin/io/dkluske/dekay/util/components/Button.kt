package io.dkluske.dekay.util.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddButton(
    addFunction: () -> Unit
) {
    IconButton(
        onClick = addFunction,
        colors = IconButtonColors(
            containerColor = Color(45, 45, 255, 255),
            contentColor = Color.White,
            disabledContentColor = Color.DarkGray,
            disabledContainerColor = Color.Blue
        )
    ) {
        Icon(Icons.Default.Add, "add")
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(45, 45, 255, 255),
            contentColor = Color.White,
            disabledContentColor = Color.DarkGray,
        ),
        modifier = modifier,
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(text)
    }
}