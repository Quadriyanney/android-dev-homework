package com.urban.androidhomework.di.module

import com.urban.androidhomework.di.component.ui.UIComponent
import dagger.Module

/**
 * Specifies [AppComponent] Subcomponents
 */
@Module(subcomponents = [UIComponent::class])
object SubComponentModule
