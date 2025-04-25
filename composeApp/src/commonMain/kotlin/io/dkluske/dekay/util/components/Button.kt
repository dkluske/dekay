package io.dkluske.dekay.util.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AddButton(
    colors: IconButtonColors = IconButtonColors(
            containerColor = Color(45, 45, 255, 255),
            contentColor = Color.White,
            disabledContentColor = Color.DarkGray,
            disabledContainerColor = Color(45, 45, 255, 150)
    ),
    icon: ImageVector = Icons.Default.Add,
    addFunction: () -> Unit
) {
    IconButton(
        onClick = addFunction,
        colors = colors
    ) {
        Icon(icon, "add")
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    colors: ButtonColors = ButtonDefaults.buttonColors(
            containerColor = Color(45, 45, 255, 255),
            contentColor = Color.White,
            disabledContentColor = Color.DarkGray,
            disabledContainerColor = Color(45, 45, 255, 150)
    ),
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = colors,
        modifier = modifier,
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(text)
    }
}