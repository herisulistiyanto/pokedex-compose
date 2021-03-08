package com.andro.indie.school.pokedex.di

import com.andro.indie.school.pokedex.data.repository.PokemonDetailRepositoryImpl
import com.andro.indie.school.pokedex.data.services.PokedexService
import com.andro.indie.school.pokedex.data.source.remote.PokemonDetailRemoteDataSource
import com.andro.indie.school.pokedex.data.source.remote.PokemonDetailRemoteDataSourceImpl
import com.andro.indie.school.pokedex.domain.repository.PokemonDetailRepository
import com.andro.indie.school.pokedex.domain.usecase.detail.GetPokemonDetailUseCase
import com.andro.indie.school.pokedex.mapper.PokemonDetailMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by herisulistiyanto on 07/03/21.
 * KjokenKoddinger
 */

@Module
@InstallIn(ViewModelComponent::class)
class DetailModule {

    @Provides
    @ViewModelScoped
    fun provideDetailRemoteDataSource(pokedexService: PokedexService): PokemonDetailRemoteDataSource {
        return PokemonDetailRemoteDataSourceImpl(pokedexService)
    }

    @Provides
    @ViewModelScoped
    fun providePokemonDetailMapper(): PokemonDetailMapper {
        return PokemonDetailMapper()
    }

    @Provides
    @ViewModelScoped
    fun provideDetailRepository(
        remoteDataSource: PokemonDetailRemoteDataSource,
        detailMapper: PokemonDetailMapper
    ): PokemonDetailRepository {
        return PokemonDetailRepositoryImpl(remoteDataSource, detailMapper)
    }

    @Provides
    @ViewModelScoped
    fun provideGetDetailUseCase(detailRepository: PokemonDetailRepository): GetPokemonDetailUseCase {
        return GetPokemonDetailUseCase(detailRepository)
    }
}