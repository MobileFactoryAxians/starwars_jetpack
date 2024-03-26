package com.example.starwars.ui.people

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositeKeyHash
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.starwars.ui.main.MainScreenModel
import com.example.starwars.utils.composable.AppBar

object PeopleScreen: Screen {
    @Composable
    override fun Content() {

        val screenModel = rememberScreenModel {
            MainScreenModel()
        }

        val state by screenModel.state.collectAsState()

        when (val result = state) {
            is MainScreenModel.State.PeopleList -> {
                Column {
                    AppBar()
                    LazyColumn(
                        state = screenModel.llState,
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {}
                        }
                        var con = result.results.count()
                    }
                }
            }

            else -> {}
        }

        LaunchedEffect(currentCompositeKeyHash) {
            screenModel.getPeople()
        }
    }
}