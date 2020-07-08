package com.urban.androidhomework.remote.data

import com.urban.androidhomework.remote.data.models.location.LocationEntity
import com.urban.androidhomework.remote.models.location.LocationNetworkModel
import konveyor.base.randomBuild

internal object LocationDataFactory {

    val locationModel get() = randomBuild<LocationNetworkModel>()

    val locationEntity get() = randomBuild<LocationEntity>()
}