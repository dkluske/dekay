package io.dkluske.dekay.views.init

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.dkluske.dekay.util.CUSTOM_THEME_DARK

@Composable
fun StepText(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = CUSTOM_THEME_DARK.onPrimary
    )
}

@Composable
fun StepTitle(text: String) {
    Text(
        text = text,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color(200f, 200f, 200f)
    )
}

@Composable
fun StepTextInput(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        placeholder = {
            Text(
                text = placeholder,
                color = Color(200, 200, 200, 255),
                fontSize = 16.sp
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(60, 60, 60, 200),
            unfocusedContainerColor = Color(60, 60, 60, 200),
            disabledContainerColor = Color(60, 60, 60, 200),
            errorContainerColor = Color(60, 60, 60, 200),
            focusedIndicatorColor = Color(220, 220, 220, 255),
            errorIndicatorColor = Color(220, 220, 220, 255),
        ),
        onValueChange = {
            onValueChange(it)
        },
        textStyle = TextStyle(
            color = Color(200, 200, 200, 255),
            fontSize = 16.sp
        )
    )
}

@Composable
fun InputButton(
    displayValue: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(60, 60, 60, 200),
            disabledContainerColor = Color(60, 60, 60, 200)
        ),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(
            text = displayValue,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            style = TextStyle(
                color = Color(200, 200, 200, 255),
                fontSize = 16.sp
            )
        )
    }
}