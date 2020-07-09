package repositories

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.urban.androidhomework.data.mappers.location.LocationEntityMapper
import com.urban.androidhomework.data.models.location.LocationEntity
import com.urban.androidhomework.data.remote.ILocationRemote
import com.urban.androidhomework.data.repositories.LocationRepository
import com.urban.androidhomework.domain.models.location.Location
import data.CommonDataFactory
import data.LocationDataFactory
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LocationRepositoryTest {

    private lateinit var locationRepository: LocationRepository

    @Mock
    private lateinit var locationRemote: ILocationRemote

    @Mock
    private lateinit var locationEntityMapper: LocationEntityMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        locationRepository = LocationRepository(locationRemote, locationEntityMapper)
    }

    @Test
    fun `check that getLocation completes`() {
        stubGetLocation(Single.just(LocationDataFactory.locationEntity))
        stubLocationMapper(any(), LocationDataFactory.location)

        locationRepository.getLocation(CommonDataFactory.int)
            .test()
            .assertComplete()
            .dispose()
    }

    @Test
    fun `check that executing getLocation returns success`() {
        val locationEntity = LocationDataFactory.locationEntity
        val location = LocationDataFactory.location

        stubGetLocation(Single.just(locationEntity))
        stubLocationMapper(locationEntity, location)

        locationRepository.getLocation(CommonDataFactory.int)
            .test()
            .assertValue(location)
            .dispose()
    }

    @Test
    fun `check that executing getLocation returns error`() {
        val error = CommonDataFactory.throwable

        stubGetLocation(Single.error(error))

        locationRepository.getLocation(CommonDataFactory.int)
            .test()
            .assertNotComplete()
            .assertError(error)
            .dispose()
    }

    private fun stubGetLocation(single: Single<LocationEntity>) {
        whenever(locationRemote.getLocation(any())).thenReturn(single)
    }

    private fun stubLocationMapper(entity: LocationEntity, domain: Location) {
        whenever(locationEntityMapper.mapFromEntity(entity)).thenReturn(domain)
    }
}
