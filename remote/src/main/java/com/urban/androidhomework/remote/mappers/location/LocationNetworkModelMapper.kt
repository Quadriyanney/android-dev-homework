package com.urban.androidhomework.remote.mappers.location

import com.urban.androidhomework.remote.data.models.location.LocationEntity
import com.urban.androidhomework.remote.mappers.RemoteModelMapper
import com.urban.androidhomework.remote.models.location.LocationNetworkModel
import javax.inject.Inject

class LocationNetworkModelMapper @Inject constructor() :
    RemoteModelMapper<LocationNetworkModel, LocationEntity> {

    override fun mapFromModel(model: LocationNetworkModel): LocationEntity {
        return LocationEntity(
            safeInt(model.id),
            safeString(model.name),
            safeString(model.type),
            safeString(model.dimension),
            safeList(model.residents)
        )
    }
}