package com.urban.androidhomework.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.urban.androidhomework.di.component.scopes.FragmentScope
import com.urban.androidhomework.domain.models.character.Character
import com.urban.androidhomework.domain.usecases.character.GetCharactersByIds
import com.urban.androidhomework.presentation.mappers.character.CharacterModelMapper
import com.urban.androidhomework.presentation.models.character.CharacterModel
import com.urban.androidhomework.utils.State
import com.urban.androidhomework.utils.singleObserver
import javax.inject.Inject

@FragmentScope
class LocationResidentsViewModel @Inject constructor(
    private val getCharactersByIds: GetCharactersByIds,
    private val characterModelMapper: CharacterModelMapper
) : ViewModel() {

    private val _getCharactersStatus = MutableLiveData<State<List<CharacterModel>>>()
    val getCharactersStatus: LiveData<State<List<CharacterModel>>> = _getCharactersStatus

    fun getCharacters(characterIds: List<Int>) {
        _getCharactersStatus.postValue(State.Loading)

        getCharactersByIds(
            singleObserver(::handleGetCharactersSuccess, ::handleGetCharactersFailure),
            GetCharactersByIds.Params(characterIds)
        )
    }

    private fun handleGetCharactersSuccess(characters: List<Character>) {
        _getCharactersStatus.postValue(State.Success(characterModelMapper.mapToUIList(characters)))
    }

    private fun handleGetCharactersFailure(error: Throwable) {
        _getCharactersStatus.postValue(State.Error(error))
    }

    override fun onCleared() {
        super.onCleared()
        getCharactersByIds.dispose()
    }
}