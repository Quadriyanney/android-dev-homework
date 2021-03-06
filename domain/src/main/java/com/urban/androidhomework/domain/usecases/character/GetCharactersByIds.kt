package com.urban.androidhomework.domain.usecases.character

import com.urban.androidhomework.domain.executor.IExecutionThread
import com.urban.androidhomework.domain.models.character.Character
import com.urban.androidhomework.domain.repositories.ICharacterRepository
import com.urban.androidhomework.domain.usecases.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * UseCase Class to get a multiple Characters by their IDs
 */
class GetCharactersByIds @Inject constructor(
    private val characterRepository: ICharacterRepository,
    executionThread: IExecutionThread
) : SingleUseCase<GetCharactersByIds.Params, List<Character>>(executionThread) {

    override fun build(params: Params?): Single<List<Character>> {
        requireNotNull(params) { NO_PARAMS_ERROR }

        return characterRepository.getCharactersByIds(params.ids)
    }

    data class Params(val ids: List<Int>)
}
