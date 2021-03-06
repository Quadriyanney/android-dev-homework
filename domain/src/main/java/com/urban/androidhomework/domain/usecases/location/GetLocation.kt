package com.urban.androidhomework.domain.usecases.location

import com.urban.androidhomework.domain.executor.IExecutionThread
import com.urban.androidhomework.domain.models.location.Location
import com.urban.androidhomework.domain.repositories.ILocationRepository
import com.urban.androidhomework.domain.usecases.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * UseCase Class to get a single Location
 */
class GetLocation @Inject constructor(
    private val locationRepository: ILocationRepository,
    executionThread: IExecutionThread
) : SingleUseCase<GetLocation.Params, Location>(executionThread) {

    override fun build(params: Params?): Single<Location> {
        requireNotNull(params) { NO_PARAMS_ERROR }

        return locationRepository.getLocation(params.id)
    }

    data class Params(val id: Int)
}
