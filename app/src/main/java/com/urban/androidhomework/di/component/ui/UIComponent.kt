package com.urban.androidhomework.di.component.ui

import com.urban.androidhomework.di.component.scopes.FragmentScope
import com.urban.androidhomework.di.module.ui.CharacterModule
import com.urban.androidhomework.di.module.ui.CharactersModule
import com.urban.androidhomework.di.module.ui.LocationResidentsModule
import com.urban.androidhomework.ui.character.characterdetails.CharacterFragment
import com.urban.androidhomework.ui.character.characterslist.CharactersFragment
import com.urban.androidhomework.ui.location.LocationResidentsFragment
import dagger.Subcomponent

/**
 * Component for a UI Lifecycle
 */
@FragmentScope
@Subcomponent(
    modules = [
        CharactersModule::class,
        CharacterModule::class,
        LocationResidentsModule::class
    ]
)
interface UIComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): UIComponent
    }

    fun inject(fragment: CharacterFragment)

    fun inject(fragment: CharactersFragment)

    fun inject(fragment: LocationResidentsFragment)
}
