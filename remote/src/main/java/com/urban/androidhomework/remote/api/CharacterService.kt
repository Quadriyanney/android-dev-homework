package com.urban.androidhomework.remote.api

import com.urban.androidhomework.remote.models.charcater.CharacterNetworkModel
import com.urban.androidhomework.remote.models.networkresponse.GetCharactersResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET("character")
    fun getCharacters(): Single<GetCharactersResponse>

    @GET("character/{id}")
    fun getCharacter(@Path("id") characterId: Int): Single<CharacterNetworkModel>

    @GET("character/{ids}")
    fun getCharactersByIds(
        @Path("ids") characterIds: List<Int>
    ): Single<List<CharacterNetworkModel>>
}