package com.urban.androidhomework.domain.usecases

import com.urban.androidhomework.domain.executor.IExecutionThread
import com.urban.androidhomework.domain.model.Character
import com.urban.androidhomework.domain.repositories.ICharactersRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCharacters @Inject constructor(
    private val charactersRepository: ICharactersRepository,
    executionThread: IExecutionThread
) : SingleUseCase<Nothing, List<Character>>(executionThread) {

    override fun build(params: Nothing?): Single<List<Character>> {
        return charactersRepository.getCharacters()
    }
}