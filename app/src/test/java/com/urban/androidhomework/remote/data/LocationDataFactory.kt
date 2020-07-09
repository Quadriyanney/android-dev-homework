package com.urban.androidhomework.remote.data

import com.urban.androidhomework.domain.models.location.Location
import com.urban.androidhomework.presentation.models.location.LocationModel
import konveyor.base.randomBuild

internal object LocationDataFactory {

    val locationModel get() = randomBuild<LocationModel>()

    val locationModels get() = MutableList(10) { locationModel }

    val locationDomainModel get() = randomBuild<Location>()

    val locationDomainModels get() = MutableList(10) { locationDomainModel }
}