package usecases.location

import assertCompleteAndDispose
import assertErrorAndDispose
import assertValueAndDispose
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.urban.androidhomework.domain.executor.IExecutionThread
import com.urban.androidhomework.domain.models.location.Location
import com.urban.androidhomework.domain.repositories.ILocationRepository
import com.urban.androidhomework.domain.usecases.location.GetLocation
import data.CommonDataFactory
import data.LocationDataFactory
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetLocationTest {

    private lateinit var getLocation: GetLocation

    @Mock
    private lateinit var locationRepository: ILocationRepository

    @Mock
    private lateinit var executionThread: IExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getLocation = GetLocation(locationRepository, executionThread)
    }

    @Test
    fun `check that getLocation completes`() {
        stubGetLocation(Single.just(LocationDataFactory.location))
        getLocation.build(GetLocation.Params(CommonDataFactory.int)).assertCompleteAndDispose()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `check that calling getLocation without params throws exception`() {
        getLocation.build().test()
    }

    @Test
    fun `check that getLocation returns error`() {
        val error = CommonDataFactory.throwable
        stubGetLocation(Single.error(error))
        getLocation.build(GetLocation.Params(CommonDataFactory.int)).assertErrorAndDispose(error)
    }

    @Test
    fun `check that calling getLocation returns data`() {
        val location = LocationDataFactory.location
        stubGetLocation(Single.just(location))
        getLocation.build(GetLocation.Params(CommonDataFactory.int)).assertValueAndDispose(location)
    }

    @Test
    fun `check that getLocation calls repository once`() {
        stubGetLocation(Single.just(LocationDataFactory.location))
        getLocation.build(GetLocation.Params(CommonDataFactory.int)).test()
        verify(locationRepository).getLocation(any())
    }

    private fun stubGetLocation(single: Single<Location>) {
        whenever(locationRepository.getLocation(any())).thenReturn(single)
    }
}