package com.urban.androidhomework.remote.di

import com.urban.androidhomework.data.remote.ICharactersRemote
import com.urban.androidhomework.remote.impl.CharactersRemote
import dagger.Binds
import dagger.Module

@Module
abstract class RemoteModule {

    @Binds
    abstract fun bindCharactersRemote(remote: CharactersRemote): ICharactersRemote
}