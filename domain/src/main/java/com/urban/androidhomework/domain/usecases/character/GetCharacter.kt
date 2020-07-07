package com.urban.androidhomework.domain.usecases.character

import com.urban.androidhomework.domain.executor.IExecutionThread
import com.urban.androidhomework.domain.models.character.Character
import com.urban.androidhomework.domain.repositories.ICharacterRepository
import com.urban.androidhomework.domain.usecases.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCharacter @Inject constructor(
    private val characterRepository: ICharacterRepository,
    executionThread: IExecutionThread
) : SingleUseCase<GetCharacter.Params, Character>(executionThread) {

    override fun build(params: Params?): Single<Character> {
        requireNotNull(params) { "Params can't be null for this use case !!!" }

        return characterRepository.getCharacter(params.id)
    }

    data class Params(val id: Int)
}