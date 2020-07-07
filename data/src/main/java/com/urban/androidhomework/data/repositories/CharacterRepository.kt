package com.urban.androidhomework.data.repositories

import com.urban.androidhomework.data.mappers.character.CharacterEntityMapper
import com.urban.androidhomework.data.remote.ICharacterRemote
import com.urban.androidhomework.domain.models.character.Character
import com.urban.androidhomework.domain.repositories.ICharacterRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Repository Implementation for Character-Related actions
 */
class CharacterRepository @Inject constructor(
    private val characterRemote: ICharacterRemote,
    private val characterEntityMapper: CharacterEntityMapper
) : ICharacterRepository {

    override fun getCharacters(): Single<List<Character>> {
        return characterRemote.getCharacters().map {
            characterEntityMapper.mapFromEntityList(it)
        }
    }

    override fun getCharacter(id: Int): Single<Character> {
        return characterRemote.getCharacter(id).map {
            characterEntityMapper.mapFromEntity(it)
        }
    }

    override fun getCharactersByIds(ids: List<Int>): Single<List<Character>> {
        return characterRemote.getCharactersByIds(ids).map {
            characterEntityMapper.mapFromEntityList(it)
        }
    }
}