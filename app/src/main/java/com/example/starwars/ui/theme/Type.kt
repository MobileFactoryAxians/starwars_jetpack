package com.example.starwars.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.starwars.R

// Set of Material typography styles to start with
val Typography.normalStyle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.font)),
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            color = AppColor
        )
    }

val Typography.buttonStyle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.font)),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.Black
        )
    }

val Typography.bigFont: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.font)),
            fontWeight = FontWeight.Normal,
            fontSize = 30.sp,
            color = AppColor
        )
    }