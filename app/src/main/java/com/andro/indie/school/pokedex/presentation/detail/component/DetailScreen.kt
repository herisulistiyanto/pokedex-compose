package com.andro.indie.school.pokedex.presentation.detail.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import com.andro.indie.school.pokedex.domain.model.PokemonDetailInfo
import com.andro.indie.school.pokedex.presentation.detail.DetailUiState
import com.andro.indie.school.pokedex.presentation.detail.DetailViewModel
import com.andro.indie.school.pokedex.util.ColorUtil
import dev.chrisbanes.accompanist.coil.CoilImage

/**
 * Created by herisulistiyanto on 07/03/21.
 * KjokenKoddinger
 */

@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    pokemonId: String
) {

    val uiState = viewModel.uiState().observeAsState(DetailUiState.Uninitialized).value

    Scaffold {
        DetailScreenContent(uiState = uiState) {
            viewModel.getPokemonDetail(pokemonId)
        }
    }
}

@Composable
fun DetailScreenContent(
    uiState: DetailUiState,
    onInit: () -> Unit
) {
    when (uiState) {
        is DetailUiState.Uninitialized -> {
            onInit()
        }
        is DetailUiState.Loading -> {
            DetailLoadingScreen()
        }
        is DetailUiState.Success -> {
            BasicInfoScreen(pokemonDetailInfo = uiState.value)
        }
        is DetailUiState.Fail -> {

        }
    }
}

@Composable
fun BasicInfoScreen(pokemonDetailInfo: PokemonDetailInfo) {
    Column {
        BigPokemonImage(detailInfo = pokemonDetailInfo)
        Spacer(modifier = Modifier.size(4.dp))
        DetailInfo(pokemonDetailInfo = pokemonDetailInfo)
    }
}

@Composable
fun DetailInfo(pokemonDetailInfo: PokemonDetailInfo) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(horizontal = 6.dp),
    ) {
        BasicPokemonInfo(detailInfo = pokemonDetailInfo)
        BasicSkillInfo(detailInfo = pokemonDetailInfo)
        BasicPokemonStats(detailInfo = pokemonDetailInfo)
    }
}

@Composable
fun BasicPokemonInfo(detailInfo: PokemonDetailInfo) {
    TemplateCardInfo(title = "Basic Info") {
        Column(modifier = Modifier.padding(6.dp)) {
            Text(text = "Pokedex Id : ${String.format("#%03d", detailInfo.id.toInt())}")
            Text(text = "Name : ${detailInfo.name}")
            Text(text = "Height : ${detailInfo.height}")
            Text(text = "Weight : ${detailInfo.weight}")
        }
    }
}

@Composable
fun BasicSkillInfo(detailInfo: PokemonDetailInfo) {
    TemplateCardInfo(title = "Skills") {
        Column(modifier = Modifier.padding(6.dp)) {
            detailInfo.abilities.forEach { pokemonAbility ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Slot : ${pokemonAbility.slot}",
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = pokemonAbility.ability.name,
                        modifier = Modifier
                            .weight(2f)
                    )
                }
            }
        }
    }
}

@Composable
fun BigPokemonImage(detailInfo: PokemonDetailInfo) {

    val defaultBgColor = MaterialTheme.colors.primary.toArgb()
    var bgColorState by remember { mutableStateOf<Int>(defaultBgColor) }

    Card(
        elevation = 2.dp,
        backgroundColor = Color(bgColorState),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),

        ) {
        Box(modifier = Modifier.fillMaxSize()) {
            CoilImage(
                data = detailInfo.imgUrl,
                contentDescription = "Pokemon Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(16.dp),
                contentScale = ContentScale.Fit,
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
                error = {

                },
                requestBuilder = {
                    allowHardware(false)
                    target { drawable ->
                        Palette.Builder(drawable.toBitmap())
                            .generate { palette ->
                                palette?.dominantSwatch?.rgb?.let { rgb ->
                                    bgColorState = ColorUtil.darkenColor(rgb)
                                }
                            }
                    }
                },
                fadeIn = true
            )
            ChipPokemonType(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .align(Alignment.BottomEnd),
                detailInfo = detailInfo
            )
        }

    }
}

@Composable
fun ChipPokemonType(modifier: Modifier, detailInfo: PokemonDetailInfo) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End
    ) {
        detailInfo.types.forEach { pokemonType ->
            ChipTemplate(pokemonType.name, pokemonType.color)
        }
    }
}

@Composable
fun ChipTemplate(label: String, chipColor: ULong) {
    Surface(
        modifier = Modifier
            .padding(4.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        color = Color(chipColor)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body2,
            color = Color.White,
            modifier = Modifier
                .padding(6.dp)
        )
    }
}

@Composable
fun BasicPokemonStats(detailInfo: PokemonDetailInfo) {

    TemplateCardInfo(title = "Basic Stats") {
        Column(modifier = Modifier.padding(6.dp)) {
            detailInfo.stats.forEach { pokemonStats ->

                Text(text = pokemonStats.stat.name, modifier = Modifier.fillMaxWidth())
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LinearProgressIndicator(
                        progress = pokemonStats.baseProcessStat,
                        color = ColorUtil.getStatColor(pokemonStats.baseProcessStat),
                        modifier = Modifier
                            .height(height = 16.dp)
                            .fillMaxWidth()
                            .weight(4f),
                        backgroundColor = MaterialTheme.colors.primaryVariant
                    )
                    Text(
                        text = "${pokemonStats.baseStats}",
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                }
                Spacer(modifier = Modifier
                    .height(4.dp)
                    .fillMaxWidth())
            }
        }
    }
}

@Composable
fun DetailLoadingScreen() {
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

@Composable
fun TemplateCardInfo(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Column(modifier = Modifier.padding(horizontal = 6.dp)) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
            Divider(
                color = MaterialTheme.colors.background,
                thickness = 2.dp,
                modifier = Modifier.fillMaxWidth()
            )
            content()
        }
    }
}

