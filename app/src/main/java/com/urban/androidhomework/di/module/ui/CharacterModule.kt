package com.urban.androidhomework.di.module.ui

import androidx.lifecycle.ViewModel
import com.urban.androidhomework.di.component.scopes.FragmentScope
import com.urban.androidhomework.di.utils.ViewModelKey
import com.urban.androidhomework.ui.character.characterdetails.CharacterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CharacterModule {

    @[Binds IntoMap FragmentScope ViewModelKey(CharacterViewModel::class)]
    abstract fun bindCharacterViewModel(viewModel: CharacterViewModel): ViewModel
}
