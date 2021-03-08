package com.andro.indie.school.pokedex.di

import com.andro.indie.school.pokedex.data.repository.PokemonRepositoryImpl
import com.andro.indie.school.pokedex.data.services.PokedexService
import com.andro.indie.school.pokedex.data.source.remote.PokemonRemoteDataSource
import com.andro.indie.school.pokedex.data.source.remote.PokemonRemoteDataSourceImpl
import com.andro.indie.school.pokedex.domain.repository.PokemonRepository
import com.andro.indie.school.pokedex.domain.usecase.home.GetPokemonUseCase
import com.andro.indie.school.pokedex.domain.usecase.home.LoadMorePokemonUseCase
import com.andro.indie.school.pokedex.mapper.PokemonMapper
import com.andro.indie.school.pokedex.presentation.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

@Module
@InstallIn(ViewModelComponent::class)
class HomeModule {

    @Provides
    @ViewModelScoped
    fun providePokemonRemoteDataSource(pokedexService: PokedexService): PokemonRemoteDataSource {
        return PokemonRemoteDataSourceImpl(pokedexService)
    }

    @Provides
    @ViewModelScoped
    fun providePokemonMapper(): PokemonMapper = PokemonMapper()

    @Provides
    @ViewModelScoped
    fun providePokemonRepository(
        pokemonRemoteDataSource: PokemonRemoteDataSource,
        pokemonMapper: PokemonMapper
    ): PokemonRepository {
        return PokemonRepositoryImpl(
            pokemonRemoteDataSource, pokemonMapper
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetPokemonUseCase(pokemonRepository: PokemonRepository): GetPokemonUseCase {
        return GetPokemonUseCase(pokemonRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideLoadMorePokemonUseCase(pokemonRepository: PokemonRepository):
        LoadMorePokemonUseCase {
        return LoadMorePokemonUseCase(pokemonRepository)
    }

}