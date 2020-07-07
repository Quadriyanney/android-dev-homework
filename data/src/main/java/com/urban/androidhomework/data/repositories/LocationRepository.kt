package com.urban.androidhomework.data.repositories

import com.urban.androidhomework.data.mappers.location.LocationEntityMapper
import com.urban.androidhomework.data.remote.ILocationRemote
import com.urban.androidhomework.domain.models.location.Location
import com.urban.androidhomework.domain.repositories.ILocationRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val locationRemote: ILocationRemote,
    private val locationEntityMapper: LocationEntityMapper
) : ILocationRepository {

    override fun getLocation(id: Int): Single<Location> {
        return locationRemote.getLocation(id).map {
            locationEntityMapper.mapFromEntity(it)
        }
    }
}