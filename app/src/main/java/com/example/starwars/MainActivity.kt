package com.example.starwars

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator
import com.example.starwars.ui.main.MainScreen
import com.example.starwars.ui.theme.StarwarsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarwarsTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    content = { padding ->
                        Surface(
                            modifier = Modifier.padding(padding),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            Navigator(screen = MainScreen)
                        }
                    }
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun GreetingPreview() {
    StarwarsTheme {
        Navigator(screen = MainScreen){}
    }
}