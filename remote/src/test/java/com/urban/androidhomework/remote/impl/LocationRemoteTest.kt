package com.urban.androidhomework.remote.impl

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.urban.androidhomework.remote.api.LocationService
import com.urban.androidhomework.remote.data.CommonDataFactory
import com.urban.androidhomework.remote.data.LocationDataFactory
import com.urban.androidhomework.remote.data.models.location.LocationEntity
import com.urban.androidhomework.remote.mappers.location.LocationNetworkModelMapper
import com.urban.androidhomework.remote.models.location.LocationNetworkModel
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LocationRemoteTest {

    private lateinit var locationRemote: LocationRemote

    @Mock
    private lateinit var locationService: LocationService

    @Mock
    private lateinit var locationNetworkModelMapper: LocationNetworkModelMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        locationRemote = LocationRemote(locationService, locationNetworkModelMapper)
    }

    @Test
    fun `check that getLocation completes`() {
        stubGetLocation(Single.just(LocationDataFactory.locationModel))
        stubLocationMapper(LocationDataFactory.locationEntity, any())

        locationRemote.getLocation(CommonDataFactory.int)
            .test()
            .assertComplete()
            .dispose()
    }

    @Test
    fun `check that executing getLocation returns success`() {
        val locationEntity = LocationDataFactory.locationEntity
        val locationModel = LocationDataFactory.locationModel

        stubGetLocation(Single.just(locationModel))
        stubLocationMapper(locationEntity, locationModel)

        locationRemote.getLocation(CommonDataFactory.int)
            .test()
            .assertValue(locationEntity)
            .dispose()
    }

    private fun stubGetLocation(single: Single<LocationNetworkModel>) {
        whenever(locationService.getLocation(any())).thenReturn(single)
    }

    private fun stubLocationMapper(entity: LocationEntity, model: LocationNetworkModel) {
        whenever(locationNetworkModelMapper.mapFromModel(model)).thenReturn(entity)
    }
}