package com.urban.androidhomework.presentation.mappers.location

import com.urban.androidhomework.domain.models.location.Location
import com.urban.androidhomework.presentation.mappers.Mapper
import com.urban.androidhomework.presentation.models.location.LocationModel
import javax.inject.Inject

class LocationModelMapper @Inject constructor() : Mapper<Location, LocationModel> {

    override fun mapToUI(domain: Location): LocationModel {
        return LocationModel(
            domain.id,
            domain.name,
            domain.type,
            domain.dimension,
            domain.residents
        )
    }

    override fun mapToDomain(ui: LocationModel): Location {
        return Location(
            ui.id,
            ui.name,
            ui.type,
            ui.dimension,
            ui.residents
        )
    }
}