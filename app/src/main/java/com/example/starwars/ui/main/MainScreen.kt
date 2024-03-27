package com.example.starwars.ui.main

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.starwars.R
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.starwars.ui.people.PeopleScreen
import com.example.starwars.ui.theme.ButtonStyle
import com.example.starwars.ui.theme.StarwarsTheme
import com.example.starwars.ui.theme.bigFont
import com.example.starwars.ui.theme.normalStyle
import kotlinx.coroutines.delay

object MainScreen: Screen {
    final val ANIM_DUR = 1000

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        StarwarsTheme {

            var expanded by remember {
                mutableStateOf(false)
            }

            var visible by remember {
                mutableStateOf(false)
            }

            //Initial and final size of image
            val targetWidth = if (expanded) 157f else 258f
            val targetHeight = if (expanded) 71f else 118f

            //Initial and final position of image
            val targetPos = if (expanded) (-320f) else 0f

            //Animation to resize the image
            val scale_x by animateFloatAsState(
                targetValue = targetWidth,
                animationSpec = tween(durationMillis = ANIM_DUR),
                label = "")

            val scale_y by animateFloatAsState(
                targetValue = targetHeight,
                animationSpec = tween(durationMillis = ANIM_DUR),
                label = "")

            //Animation to move the image
            val move_y by animateFloatAsState(
                targetValue = targetPos,
                animationSpec = tween(durationMillis = ANIM_DUR),
                label = "")

            //Wait 1s and then start the animation
            LaunchedEffect(Unit) {
                delay(1000)
                expanded = true
                visible = true
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.starwars),
                    contentDescription = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .width(scale_x.dp)
                        .height(scale_y.dp)
                        .offset(y = move_y.dp)
                )
            }

            if (visible) {
                Column(
                    modifier = Modifier
                        .padding(
                            top = 130.dp,
                            start = 50.dp,
                            end = 50.dp
                        )
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.homeIntro),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 16.dp),
                        style = MaterialTheme.typography.normalStyle
                    )

                    Text(
                        text = stringResource(id = R.string.hometitle),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 40.dp),
                        style = MaterialTheme.typography.bigFont
                    )

                    Column {
                        val context = LocalContext.current

                        ButtonStyle(
                            text = stringResource(id = R.string.textBtnPeople),
                            onClick = {
                                navigator.push(PeopleScreen)
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        ButtonStyle(
                            text = stringResource(id = R.string.textBtnShip),
                            onClick = {
                                Toast.makeText(context, "Ship", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 30.dp)
                        )

                        ButtonStyle(
                            text = stringResource(id = R.string.textBtnPlanet),
                            onClick = {
                                Toast.makeText(context, "Planet", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}