package com.urban.androidhomework.data.repositories

import com.urban.androidhomework.data.mapper.CharacterEntityMapper
import com.urban.androidhomework.data.remote.ICharactersRemote
import com.urban.androidhomework.domain.model.Character
import com.urban.androidhomework.domain.repositories.ICharactersRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val charactersRemote: ICharactersRemote,
    private val characterEntityMapper: CharacterEntityMapper
) : ICharactersRepository {

    override fun getCharacters(): Single<List<Character>> {
        return charactersRemote.getCharacters().map {
            characterEntityMapper.mapFromEntityList(it)
        }
    }
}