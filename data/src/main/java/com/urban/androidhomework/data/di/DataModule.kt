package com.urban.androidhomework.data.di

import com.urban.androidhomework.data.repositories.CharacterRepository
import com.urban.androidhomework.data.repositories.LocationRepository
import com.urban.androidhomework.domain.repositories.ICharacterRepository
import com.urban.androidhomework.domain.repositories.ILocationRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindCharactersRepository(repository: CharacterRepository): ICharacterRepository

    @Binds
    abstract fun bindLocationRepository(repository: LocationRepository): ILocationRepository
}