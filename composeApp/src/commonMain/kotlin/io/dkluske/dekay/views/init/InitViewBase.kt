package io.dkluske.dekay.views.init

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import io.dkluske.dekay.util.CUSTOM_THEME_DARK

@Composable
fun Steppable.InitViewBase(
    block: @Composable Steppable.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(CUSTOM_THEME_DARK.background)
            .background(
                brush = object : ShaderBrush() {
                    override fun createShader(size: Size): Shader {
                        val maxSize = maxOf(size.width, size.height)
                        return RadialGradientShader(
                            colors = listOf(
                                Color(217f, 217f, 217f, 0.43f),
                                Color(166f, 166f, 166f, 0.26f),
                                Color(140f, 140f, 140f, 0.15f),
                                Color(115f, 115f, 115f, 0f)
                            ),
                            center = size.center.minus(Offset(x = 0f, y = size.height / 2)),
                            radius = maxSize / 1.2f,
                            colorStops = listOf(0.0f, 0.5f, 0.75f, 1f)
                        )
                    }
                }
            )
    ) {
        block()
    }
}