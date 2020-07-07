package com.urban.androidhomework.di.module.ui

import androidx.lifecycle.ViewModel
import com.urban.androidhomework.di.component.scopes.FragmentScope
import com.urban.androidhomework.di.utils.ViewModelKey
import com.urban.androidhomework.ui.character.characterslist.CharactersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CharactersModule {

    @[Binds IntoMap FragmentScope ViewModelKey(CharactersViewModel::class)]
    abstract fun bindCharactersViewModel(viewModel: CharactersViewModel): ViewModel
}
