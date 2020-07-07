package com.urban.androidhomework.domain.repositories

import com.urban.androidhomework.domain.models.location.Location
import io.reactivex.rxjava3.core.Single

interface ILocationRepository {

    fun getLocation(id: Int): Single<Location>
}