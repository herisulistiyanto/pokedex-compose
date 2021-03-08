package com.andro.indie.school.pokedex.mapper

import androidx.compose.ui.graphics.toArgb
import com.andro.indie.school.core.base.BaseMapper
import com.andro.indie.school.pokedex.data.model.remote.AbilityModel
import com.andro.indie.school.pokedex.data.model.remote.PokemonAbilityModel
import com.andro.indie.school.pokedex.data.model.remote.PokemonDetailInfoModel
import com.andro.indie.school.pokedex.data.model.remote.PokemonStatsModel
import com.andro.indie.school.pokedex.data.model.remote.PokemonTypeModel
import com.andro.indie.school.pokedex.data.model.remote.StatModel
import com.andro.indie.school.pokedex.data.model.remote.TypeModel
import com.andro.indie.school.pokedex.domain.model.Ability
import com.andro.indie.school.pokedex.domain.model.PokemonAbility
import com.andro.indie.school.pokedex.domain.model.PokemonDetailInfo
import com.andro.indie.school.pokedex.domain.model.PokemonStats
import com.andro.indie.school.pokedex.domain.model.PokemonType
import com.andro.indie.school.pokedex.domain.model.Stat
import com.andro.indie.school.pokedex.ui.theme.speciesNormal
import com.andro.indie.school.pokedex.ui.theme.typeColorMap
import com.andro.indie.school.pokedex.util.orFalse
import com.andro.indie.school.pokedex.util.orZero
import java.util.Locale

/**
 * Created by herisulistiyanto on 07/03/21.
 * KjokenKoddinger
 */

class PokemonDetailMapper : BaseMapper<PokemonDetailInfoModel, PokemonDetailInfo> {

    @ExperimentalUnsignedTypes
    override fun toDomainModel(model: PokemonDetailInfoModel): PokemonDetailInfo {
        return with(model) {
            PokemonDetailInfo(
                id = id,
                name = name.capitalize(Locale.US),
                height = height,
                weight = weight,
                imgUrl = generateImgUrl(id),
                abilities = abilities.map { pokemonAbilityModel ->
                    PokemonAbility(
                        is_hidden = pokemonAbilityModel.is_hidden.orFalse(),
                        slot = pokemonAbilityModel.slot.orZero(),
                        ability = Ability(
                            pokemonAbilityModel.ability?.name.orEmpty()
                                .capitalize(Locale.US)
                        )
                    )
                },
                stats = stats.map { pokemonStatsModel ->
                    val baseStat = pokemonStatsModel.baseStat.orZero()
                    val statName = pokemonStatsModel.stat?.name.orEmpty()
                    PokemonStats(
                        baseStats = baseStat,
                        baseProcessStat = baseStat.statToProcess(statName),
                        stat = Stat(statName)
                    )
                },
                types = types.map { pokemonTypeModel ->
                    val type = pokemonTypeModel.type?.name.orEmpty()
                    PokemonType(
                        slot = pokemonTypeModel.slot.orZero(),
                        name = type,
                        color = mapTypeToColor(type.toLowerCase(Locale.US))
                    )
                }
            )
        }
    }

    override fun toModel(domain: PokemonDetailInfo): PokemonDetailInfoModel {
        return with(domain) {
            PokemonDetailInfoModel(
                id = id,
                name = name,
                height = height,
                weight = weight,
                abilities = abilities.map { pokemonAbility ->
                    PokemonAbilityModel(
                        is_hidden = pokemonAbility.is_hidden,
                        slot = pokemonAbility.slot,
                        ability = AbilityModel(pokemonAbility.ability.name)
                    )
                },
                stats = stats.map { pokemonStats ->
                    PokemonStatsModel(
                        baseStat = pokemonStats.baseStats,
                        stat = StatModel(pokemonStats.stat.name)
                    )
                },
                types = types.map { pokemonType ->
                    PokemonTypeModel(
                        slot = pokemonType.slot,
                        type = TypeModel(pokemonType.name)
                    )
                }
            )
        }
    }

    private fun generateImgUrl(id: String): String {
        return "https://pokeres.bastionbot.org/images/pokemon/$id.png"
    }

    private fun Int.statToProcess(statName: String = ""): Float {
        val floatStat: Float = this.toFloat()
        return when (statName.toLowerCase(Locale.US)) {
            "hp" -> floatStat / maxHp
            "attack" -> floatStat / maxAttack
            "defense" -> floatStat / maxDefense
            "speed" -> floatStat / maxSpeed
            "special-attack" -> floatStat / maxSpecialAttack
            "special-defense" -> floatStat / maxSpecialDefense
            else -> floatStat / maxExp
        }
    }

    @ExperimentalUnsignedTypes
    private fun mapTypeToColor(type: String): ULong {
        val color = typeColorMap[type] ?: speciesNormal
        return color.value
    }

    companion object {
        const val maxHp = 300
        const val maxAttack = 300
        const val maxDefense = 300
        const val maxSpeed = 300
        const val maxSpecialAttack = 300
        const val maxSpecialDefense = 300
        const val maxExp = 1000
    }
}