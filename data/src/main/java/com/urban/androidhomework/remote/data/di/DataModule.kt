package com.urban.androidhomework.remote.data.di

import com.urban.androidhomework.remote.data.repositories.CharacterRepository
import com.urban.androidhomework.remote.data.repositories.LocationRepository
import com.urban.androidhomework.domain.repositories.ICharacterRepository
import com.urban.androidhomework.domain.repositories.ILocationRepository
import dagger.Binds
import dagger.Module

/**
 * Provide Repositories
 */
@Module
abstract class DataModule {

    @Binds
    abstract fun bindCharactersRepository(repository: CharacterRepository): ICharacterRepository

    @Binds
    abstract fun bindLocationRepository(repository: LocationRepository): ILocationRepository
}