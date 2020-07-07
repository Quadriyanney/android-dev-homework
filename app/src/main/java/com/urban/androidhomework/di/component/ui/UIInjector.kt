package com.urban.androidhomework.di.component.ui

import androidx.fragment.app.Fragment
import com.urban.androidhomework.UrbanHomeworkApp
import com.urban.androidhomework.ui.character.characterdetails.CharacterFragment
import com.urban.androidhomework.ui.character.characterslist.CharactersFragment
import com.urban.androidhomework.ui.location.LocationResidentsFragment

private val Fragment.uiComponent: UIComponent
    get() {
        return (requireActivity().applicationContext as UrbanHomeworkApp)
            .appComponent
            .uiComponent()
            .create()
    }

fun CharactersFragment.inject() {
    uiComponent.inject(this)
}

fun CharacterFragment.inject() {
    uiComponent.inject(this)
}

fun LocationResidentsFragment.inject() {
    uiComponent.inject(this)
}