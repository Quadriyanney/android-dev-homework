package com.urban.androidhomework;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;

public interface NetworkApi {

    @GET("character/")
    Single<Response<Character>> getAllCharacters();

}
