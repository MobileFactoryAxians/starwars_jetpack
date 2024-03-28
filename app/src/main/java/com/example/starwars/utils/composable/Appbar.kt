package com.example.starwars.utils.composable

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.starwars.R
import com.example.starwars.ui.theme.AppColor
import com.example.starwars.ui.theme.normalStyle

@Composable
fun AppBar(
    detail: Boolean,
    value: String,
    backScreen: Screen
) {
    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
            .fillMaxWidth()
    ) {
        if (detail) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.normalStyle,
                text = value)
        }

       else {
            Image(
                painter = painterResource(id = R.drawable.starwars),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier.fillMaxWidth(),
                alignment = Alignment.Center
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BackButton(backScreen)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun BackButton(
    backScreen: Screen
) {
    val navigator = LocalNavigator.current

    IconButton(
        onClick = {
            navigator?.push(backScreen)
        }) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier.size(24.dp),
            tint = AppColor
        )
    }
}