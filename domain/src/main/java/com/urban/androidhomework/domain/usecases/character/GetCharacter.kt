package com.urban.androidhomework.domain.usecases.character

import com.urban.androidhomework.domain.executor.IExecutionThread
import com.urban.androidhomework.domain.models.character.Character
import com.urban.androidhomework.domain.repositories.ICharacterRepository
import com.urban.androidhomework.domain.usecases.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * UseCase Class to get a single Character
 */
class GetCharacter @Inject constructor(
    private val characterRepository: ICharacterRepository,
    executionThread: IExecutionThread
) : SingleUseCase<GetCharacter.Params, Character>(executionThread) {

    override fun build(params: Params?): Single<Character> {
        requireNotNull(params) { NO_PARAMS_ERROR }

        return characterRepository.getCharacter(params.id)
    }

    data class Params(val id: Int)
}
