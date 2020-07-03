package com.urban.androidhomework.remote.api

import com.urban.androidhomework.remote.model.networkresponse.GetCharactersResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface APIService {

    @GET("character")
    fun getCharacters(): Single<GetCharactersResponse>
}