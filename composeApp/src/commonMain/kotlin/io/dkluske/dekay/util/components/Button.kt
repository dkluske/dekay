package io.dkluske.dekay.util.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AddButton(
    addFunction: () -> Unit
) {
    IconButton(
        onClick = addFunction,
        colors = IconButtonColors(
            containerColor = Color.Blue,
            contentColor = Color.White,
            disabledContentColor = Color.DarkGray,
            disabledContainerColor = Color.Blue
        )
    ) {
        Icon(Icons.Default.Add, "add")
    }
}