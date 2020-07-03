package com.urban.androidhomework.remote.impl

import com.urban.androidhomework.data.model.CharacterEntity
import com.urban.androidhomework.data.remote.ICharactersRemote
import com.urban.androidhomework.remote.api.APIService
import com.urban.androidhomework.remote.mapper.CharacterNetworkModelMapper
import com.urban.androidhomework.remote.utils.handleError
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharactersRemote @Inject constructor(
    private val apiService: APIService,
    private val characterNetworkModelMapper: CharacterNetworkModelMapper
) : ICharactersRemote {

    override fun getCharacters(): Single<List<CharacterEntity>> {
        return apiService.getCharacters().map {
            characterNetworkModelMapper.mapModelList(it.results)
        }.onErrorResumeNext {
            Single.error(handleError(it))
        }
    }
}