package com.example.starwars.ui.people.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.starwars.R
import com.example.starwars.ui.people.PeopleScreen
import com.example.starwars.ui.theme.StarwarsTheme
import com.example.starwars.ui.theme.normalStyle
import com.example.starwars.ui.theme.smallFont
import com.example.starwars.utils.composable.AppBar

data class PeopleDetailsScreen(val peopleName: String) : Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel {
            PeopleDetailsScreenModel()
        }

        StarwarsTheme {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                AppBar(
                    detail = true,
                    value = peopleName,
                    PeopleScreen
                )

                Box(
                    contentAlignment = Alignment.Center
                ) {
                    ItemList(screenModel = screenModel)
                }
            }
        }
    }

    @Composable
    private fun ItemList(
        screenModel: PeopleDetailsScreenModel
    ) {
        val state by screenModel.state.collectAsState()
        screenModel.getPeople(peopleName)

        when (val result = state) {
            is PeopleDetailsScreenModel.State.PeopleItem -> {
                val people = result.result

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.detailBio),
                        style = MaterialTheme.typography.normalStyle,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    DetailInfo(
                        title = stringResource(id = R.string.detail_born),
                        value = people.birthYear
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = stringResource(id = R.string.detailPhysical),
                        style = MaterialTheme.typography.normalStyle,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            else -> {}
        }
    }

    @Composable
    private fun DetailInfo(
        title: String,
        value: String
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(.5f)
                    .padding(end = 20.dp),
                style = MaterialTheme.typography.smallFont
            )
            Text(
                text = value,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(.5f),
                style = MaterialTheme.typography.smallFont
            )
        }
    }
}