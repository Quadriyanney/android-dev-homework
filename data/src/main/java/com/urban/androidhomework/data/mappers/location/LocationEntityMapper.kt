package com.urban.androidhomework.data.mappers.location

import com.urban.androidhomework.data.mappers.EntityMapper
import com.urban.androidhomework.data.models.location.LocationEntity
import com.urban.androidhomework.domain.models.location.Location
import javax.inject.Inject

class LocationEntityMapper @Inject constructor() : EntityMapper<LocationEntity, Location>() {

    override fun mapFromEntity(entity: LocationEntity): Location {
        return Location(
            entity.id,
            entity.name,
            entity.type,
            entity.dimension,
            entity.residents
        )
    }

    override fun mapToEntity(domain: Location): LocationEntity {
        return LocationEntity(
            domain.id,
            domain.name,
            domain.type,
            domain.dimension,
            domain.residents
        )
    }
}
