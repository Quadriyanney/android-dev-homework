package com.urban.androidhomework.data.remote

import com.urban.androidhomework.data.model.CharacterEntity
import io.reactivex.rxjava3.core.Single

interface ICharactersRemote {

    fun getCharacters(): Single<List<CharacterEntity>>
}