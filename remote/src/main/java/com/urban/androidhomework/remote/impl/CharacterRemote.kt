package com.urban.androidhomework.remote.impl

import com.urban.androidhomework.remote.api.CharacterService
import com.urban.androidhomework.remote.data.models.character.CharacterEntity
import com.urban.androidhomework.remote.data.remote.ICharacterRemote
import com.urban.androidhomework.remote.mappers.character.CharacterNetworkModelMapper
import com.urban.androidhomework.remote.utils.handleError
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharacterRemote @Inject constructor(
    private val characterService: CharacterService,
    private val characterNetworkModelMapper: CharacterNetworkModelMapper
) : ICharacterRemote {

    override fun getCharacters(): Single<List<CharacterEntity>> {
        return characterService.getCharacters().map {
            characterNetworkModelMapper.mapModelList(it.results)
        }.onErrorResumeNext {
            Single.error(handleError(it))
        }
    }

    override fun getCharacter(id: Int): Single<CharacterEntity> {
        return characterService.getCharacter(id).map {
            characterNetworkModelMapper.mapFromModel(it)
        }.onErrorResumeNext {
            Single.error(handleError(it))
        }
    }

    override fun getCharactersByIds(ids: List<Int>): Single<List<CharacterEntity>> {
        return characterService.getCharactersByIds(ids).map {
            characterNetworkModelMapper.mapModelList(it)
        }.onErrorResumeNext {
            Single.error(handleError(it))
        }
    }
}