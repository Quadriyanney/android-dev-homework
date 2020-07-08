package com.urban.androidhomework.domain.usecases.character

import com.urban.androidhomework.domain.executor.IExecutionThread
import com.urban.androidhomework.domain.models.character.Character
import com.urban.androidhomework.domain.repositories.ICharacterRepository
import com.urban.androidhomework.domain.usecases.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * UseCase Class to get a all Characters
 */
class GetCharacters @Inject constructor(
    private val characterRepository: ICharacterRepository,
    executionThread: IExecutionThread
) : SingleUseCase<Unit, List<Character>>(executionThread) {

    override fun build(params: Unit?): Single<List<Character>> {
        return characterRepository.getCharacters()
    }
}