package com.andro.indie.school.pokedex.mapper

import com.andro.indie.school.core.base.BaseMapper
import com.andro.indie.school.pokedex.data.model.remote.PokemonModel
import com.andro.indie.school.pokedex.domain.model.Pokemon
import java.util.Locale

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

class PokemonMapper : BaseMapper<Pair<String, PokemonModel>, Pokemon> {

    override fun toDomainModel(model: Pair<String, PokemonModel>): Pokemon {
        return with(model) {
            val nextUrl = first
            val pokemonModel = second
            Pokemon(
                id = pokemonModel.generatePokemonId(),
                name = pokemonModel.name.capitalize(Locale.US),
                url = pokemonModel.url,
                imgUrl = pokemonModel.generateImgUrl(),
                nextUrl = nextUrl
            )
        }
    }

    override fun toModel(domain: Pokemon): Pair<String, PokemonModel> {
        return with(domain) {
            nextUrl to PokemonModel(
                name = name,
                url = url
            )
        }
    }

    fun toListDomainModel(data: Pair<String, List<PokemonModel>>): List<Pokemon> {
        val nextUrl = data.first
        val listPokemonModel = data.second
        return listPokemonModel.map { pokemonModel ->
            Pokemon(
                id = pokemonModel.generatePokemonId(),
                name = pokemonModel.name.capitalize(Locale.US),
                url = pokemonModel.url,
                imgUrl = pokemonModel.generateImgUrl(),
                nextUrl = nextUrl
            )
        }
    }

    private fun PokemonModel.generateImgUrl(): String {
        val index = generatePokemonId()
        return "https://pokeres.bastionbot.org/images/pokemon/$index.png"
    }

    private fun PokemonModel.generatePokemonId(): String {
        return url.split("/".toRegex()).dropLast(1).last()
    }
}