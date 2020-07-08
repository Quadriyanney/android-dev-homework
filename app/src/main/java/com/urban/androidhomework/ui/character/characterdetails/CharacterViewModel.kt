package com.urban.androidhomework.ui.character.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.urban.androidhomework.di.component.scopes.FragmentScope
import com.urban.androidhomework.domain.models.character.Character
import com.urban.androidhomework.domain.models.location.Location
import com.urban.androidhomework.domain.usecases.character.GetCharacter
import com.urban.androidhomework.domain.usecases.location.GetLocation
import com.urban.androidhomework.presentation.mappers.character.CharacterModelMapper
import com.urban.androidhomework.presentation.mappers.location.LocationModelMapper
import com.urban.androidhomework.presentation.models.character.CharacterModel
import com.urban.androidhomework.presentation.models.location.LocationModel
import com.urban.androidhomework.utils.State
import com.urban.androidhomework.utils.getIdFromUrl
import com.urban.androidhomework.utils.singleObserver
import javax.inject.Inject

@FragmentScope
class CharacterViewModel @Inject constructor(
    private val getLocation: GetLocation,
    private val getCharacter: GetCharacter,
    private val locationModelMapper: LocationModelMapper,
    private val characterModelMapper: CharacterModelMapper
) : ViewModel() {

    lateinit var character: CharacterModel
    lateinit var location: LocationModel

    val residentsIds get() = location.residents.mapNotNull { it.getIdFromUrl() }

    private val _getCharacterState = MutableLiveData<State<CharacterModel>>()
    val getCharacterState: LiveData<State<CharacterModel>> = _getCharacterState

    private val _getLocationState = MutableLiveData<State<LocationModel>>()
    val getLocationState: LiveData<State<LocationModel>> = _getLocationState

    //// For Test Purposes
    val isCharacterInitialized get() = ::character.isInitialized
    val isLocationInitialized get() = ::location.isInitialized

    fun getCharacter(id: Int) {
        _getCharacterState.postValue(State.Loading)

        getCharacter(
            singleObserver(::handleGetCharacterSuccess, ::handleGetCharacterFailure),
            GetCharacter.Params(id)
        )
    }

    private fun handleGetCharacterSuccess(newCharacter: Character) {
        character = characterModelMapper.mapToUI(newCharacter)
        _getCharacterState.postValue(State.Success(character))

        performGetCharacterLocation()
    }

    private fun handleGetCharacterFailure(error: Throwable) {
        _getCharacterState.postValue(State.Error(error))
    }

    fun performGetCharacterLocation() {
        val id = character.location?.url?.getIdFromUrl()

        id?.let {
            getLocation(it)
        }
    }

    fun getLocation(id: Int) {
        _getLocationState.postValue(State.Loading)

        getLocation(
            singleObserver(::handleGetLocationSuccess, ::handleGetLocationFailure),
            GetLocation.Params(id)
        )
    }

    private fun handleGetLocationSuccess(newLocation: Location) {
        location = locationModelMapper.mapToUI(newLocation)
        _getLocationState.postValue(State.Success(location))
    }

    private fun handleGetLocationFailure(error: Throwable) {
        _getLocationState.postValue(State.Error(error))
    }

    override fun onCleared() {
        super.onCleared()
        getCharacter.dispose()
        getLocation.dispose()
    }
}