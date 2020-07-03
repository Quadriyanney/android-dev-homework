package com.urban.androidhomework.domain.repositories

import com.urban.androidhomework.domain.model.Character
import io.reactivex.rxjava3.core.Single

interface ICharactersRepository {

    fun getCharacters(): Single<List<Character>>
}