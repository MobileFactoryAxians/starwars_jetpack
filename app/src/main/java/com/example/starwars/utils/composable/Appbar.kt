package com.example.starwars.utils.composable

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.starwars.R
import com.example.starwars.ui.main.MainScreen
import com.example.starwars.ui.theme.AppColor
import com.example.starwars.ui.theme.StarwarsTheme

@Composable
fun AppBar() {
    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.starwars),
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier.fillMaxWidth(),
            alignment = Alignment.Center
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BackButton()
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun BackButton() {
    val navigator = LocalNavigator.current

    IconButton(
        onClick = {
            navigator?.popUntilRoot()
        }) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier.size(24.dp),
            tint = AppColor
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun GreetingPreview() {
    StarwarsTheme {
        AppBar()
    }
}