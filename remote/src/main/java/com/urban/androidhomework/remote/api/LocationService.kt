package com.urban.androidhomework.remote.api

import com.urban.androidhomework.remote.models.location.LocationNetworkModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * API Service Class for Location-Related Network Calls
 */
interface LocationService {

    @GET("location/{id}")
    fun getLocation(@Path("id") locationId: Int): Single<LocationNetworkModel>
}
