package com.andro.indie.school.pokedex.presentation.home.component

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.andro.indie.school.pokedex.domain.model.Pokemon
import com.andro.indie.school.pokedex.presentation.home.HomeState
import com.andro.indie.school.pokedex.presentation.home.HomeViewModel
import com.andro.indie.school.pokedex.ui.navigation.ScreenRoute
import dev.chrisbanes.accompanist.coil.CoilImage
import java.util.Locale

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

@ExperimentalAnimationApi
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateDetail: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .padding(4.dp),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "PokeDex", color = Color.White)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                backgroundColor = MaterialTheme.colors.primarySurface
            )
        },
    ) {
        HomeScreenContent(
            viewModel = viewModel,
            onNavigateDetail = onNavigateDetail
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel,
    onNavigateDetail: (String) -> Unit
) {
    val state = viewModel
        .uiState()
        .observeAsState(HomeState.Uninitialized).value

    when (state) {
        is HomeState.Uninitialized -> {
        }
        is HomeState.Loading -> HomeLoadingScreen()
        is HomeState.Success -> {
            PokemonList(
                data = state.value,
                onNavigateDetail = onNavigateDetail,
                onLoadMore = { url ->
                    viewModel.loadMorePokemon(url)
                }
            )
        }
        is HomeState.Fail -> {
            // TODO : add fail screen
        }
    }
}

@Composable
fun HomeLoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(32.dp)

            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Loading",
                textAlign = TextAlign.Center
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun PokemonList(
    data: List<Pokemon>,
    onNavigateDetail: (String) -> Unit,
    onLoadMore: (String) ->
    Unit
) {
    LazyColumn {
        itemsIndexed(data) { index: Int, item: Pokemon ->
            PokemonCard(
                pokemon = item,
                onNavigateDetail = onNavigateDetail
            )
            if (data.isNotEmpty() && data.lastIndex == index) {
                onLoadMore(data[index].nextUrl)
            }
        }
    }
}

@Composable
fun PokemonCard(pokemon: Pokemon, onNavigateDetail: (String) -> Unit) {
    Card(
        elevation = 2.dp,
        shape = MaterialTheme.shapes.small,
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .height(160.dp)
                .padding(8.dp),
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                CoilImage(
                    data = pokemon.imgUrl,
                    contentDescription = "Pokemon Image",
                    modifier = Modifier
                        .padding(16.dp),
                    contentScale = ContentScale.Inside,
                    loading = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxSize(0.2f)
                            )
                        }
                    },
                    fadeIn = true,
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.primaryVariant)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(
                        text = pokemon.name.capitalize(Locale.US),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth()
                            .padding(4.dp),
                        style = MaterialTheme.typography.h6
                    )
                    Text(
                        text = String.format("#%03d", pokemon.id.toInt()),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth()
                            .padding(4.dp),
                        style = MaterialTheme.typography.h6
                    )
                    Button(
                        onClick = {
                            val navRoute = ScreenRoute.DetailScreen.route + "/${pokemon.id}"
                            onNavigateDetail(navRoute)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme
                            .colors.surface)
                    ) {
                        Text(
                            text = "Detail",
                            modifier = Modifier
                                .padding(4.dp),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.secondary
                        )
                    }
                }
            }
        }
    }
}