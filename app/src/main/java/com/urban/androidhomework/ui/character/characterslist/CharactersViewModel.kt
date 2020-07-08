package com.urban.androidhomework.ui.character.characterslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.urban.androidhomework.di.component.scopes.FragmentScope
import com.urban.androidhomework.domain.models.character.Character
import com.urban.androidhomework.domain.usecases.character.GetCharacters
import com.urban.androidhomework.presentation.mappers.character.CharacterModelMapper
import com.urban.androidhomework.presentation.models.character.CharacterModel
import com.urban.androidhomework.utils.State
import com.urban.androidhomework.utils.singleObserver
import javax.inject.Inject

@FragmentScope
class CharactersViewModel @Inject constructor(
    private val getCharacters: GetCharacters,
    private val characterModelMapper: CharacterModelMapper
) : ViewModel() {

    private val _getCharactersState = MutableLiveData<State<List<CharacterModel>>>()
    val getCharactersState: LiveData<State<List<CharacterModel>>> = _getCharactersState

    val allCharacters = mutableListOf<CharacterModel>()

    var startDate = 0L
    var endDate = 0L

    init {
        getCharacters()
    }

    fun getCharacters() {
        _getCharactersState.postValue(State.Loading)

        getCharacters(singleObserver(::handleGetCharactersSuccess, ::handleGetCharactersFailure))
    }

    private fun handleGetCharactersSuccess(characters: List<Character>) {
        allCharacters.addAll(characterModelMapper.mapToUIList(characters))
        _getCharactersState.postValue(State.Success(allCharacters))

        setFilterDates()
    }

    private fun handleGetCharactersFailure(error: Throwable) {
        _getCharactersState.postValue(State.Error(error))
    }

    private fun setFilterDates() {
        val sortedDates = allCharacters.map { it.createdDate }.sorted()

        startDate = sortedDates[0].time
        endDate = sortedDates[sortedDates.lastIndex].time
    }

    fun applyFilter() {
        _getCharactersState.postValue(
            State.Success(allCharacters.filter { it.createdDate.time in startDate..endDate })
        )
    }

    override fun onCleared() {
        super.onCleared()
        getCharacters.dispose()
    }
}