package com.example.starwars.ui.people

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositeKeyHash
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.starwars.R
import com.example.starwars.ui.theme.StarwarsTheme
import com.example.starwars.ui.theme.bigFont
import com.example.starwars.utils.composable.AppBar
import com.example.starwars.utils.composable.IndeterminateProgress

object PeopleScreen: Screen {
    @Composable
    override fun Content() {

        val screenModel = rememberScreenModel {
            PeopleScreenModel()
        }

        StarwarsTheme {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                AppBar()
                Text(
                    text = stringResource(id = R.string.textBtnPeople),
                    style = MaterialTheme.typography.bigFont,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(
                            bottom = 16.dp
                        )
                        .align(Alignment.CenterHorizontally)
                )
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    ItemList(screenModel = screenModel)
                }
            }



            LaunchedEffect(currentCompositeKeyHash) {
                screenModel.getPeople()
            }
        }
    }

    @Composable
    private fun ItemList(
        screenModel: PeopleScreenModel
    ) {
        val state by screenModel.state.collectAsState()
        val context = LocalContext.current

        when (val result = state) {
            is PeopleScreenModel.State.PeopleList -> {
                Column {
                    LazyColumn(
                        state = screenModel.llState,
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        items(result.results) {
                                people ->
                            PeopleItem(
                                people = people,
                                onItemClick = {
                                    Toast.makeText(context, people.name, Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    }
                }
            }

            is PeopleScreenModel.State.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    IndeterminateProgress()
                }
            }

            else -> {}
        }
    }
}