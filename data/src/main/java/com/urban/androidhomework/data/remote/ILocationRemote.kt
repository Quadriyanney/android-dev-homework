package com.urban.androidhomework.data.remote

import com.urban.androidhomework.data.models.location.LocationEntity
import io.reactivex.rxjava3.core.Single

interface ILocationRemote {

    fun getLocation(id: Int): Single<LocationEntity>
}
