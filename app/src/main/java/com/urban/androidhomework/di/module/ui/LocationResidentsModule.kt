package com.urban.androidhomework.di.module.ui

import androidx.lifecycle.ViewModel
import com.urban.androidhomework.di.component.scopes.FragmentScope
import com.urban.androidhomework.di.utils.ViewModelKey
import com.urban.androidhomework.ui.location.LocationResidentsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LocationResidentsModule {

    @[Binds IntoMap FragmentScope ViewModelKey(LocationResidentsViewModel::class)]
    abstract fun bindLocationResidentsViewModel(viewModel: LocationResidentsViewModel): ViewModel
}
