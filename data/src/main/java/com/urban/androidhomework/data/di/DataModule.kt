package com.urban.androidhomework.data.di

import com.urban.androidhomework.data.repositories.CharactersRepository
import com.urban.androidhomework.domain.repositories.ICharactersRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindCharactersRepository(repository: CharactersRepository): ICharactersRepository
}