package com.urban.androidhomework.remote.di

import com.urban.androidhomework.remote.data.remote.ICharacterRemote
import com.urban.androidhomework.remote.data.remote.ILocationRemote
import com.urban.androidhomework.remote.impl.CharacterRemote
import com.urban.androidhomework.remote.impl.LocationRemote
import dagger.Binds
import dagger.Module

@Module
abstract class RemoteModule {

    @Binds
    abstract fun bindCharactersRemote(remote: CharacterRemote): ICharacterRemote

    @Binds
    abstract fun bindLocationRemote(remote: LocationRemote): ILocationRemote
}