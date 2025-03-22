package io.dkluske.dekay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import io.dkluske.dekay.store.database.DriverFactory
import io.dkluske.dekay.util.Constants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sqlDriver = DriverFactory(context = this.baseContext)
            .createDriver(Constants.DB_NAME)

        setContent {
            App(
                sqlDriver = sqlDriver
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    val sqlDriver = DriverFactory(context = LocalContext.current)
        .createDriver(Constants.DB_NAME)
    App(
        sqlDriver = sqlDriver
    )
}