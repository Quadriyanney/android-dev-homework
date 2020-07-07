package com.urban.androidhomework.remote.impl

import com.urban.androidhomework.data.models.location.LocationEntity
import com.urban.androidhomework.data.remote.ILocationRemote
import com.urban.androidhomework.remote.api.LocationService
import com.urban.androidhomework.remote.mappers.location.LocationNetworkModelMapper
import com.urban.androidhomework.remote.utils.handleError
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LocationRemote @Inject constructor(
    private val locationService: LocationService,
    private val locationNetworkModelMapper: LocationNetworkModelMapper
) : ILocationRemote {

    override fun getLocation(id: Int): Single<LocationEntity> {
        return locationService.getLocation(id).map {
            locationNetworkModelMapper.mapFromModel(it)
        }.onErrorResumeNext {
            Single.error(handleError(it))
        }
    }
}