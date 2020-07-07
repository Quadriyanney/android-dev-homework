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

    private val _getCharacterStatus = MutableLiveData<State<CharacterModel>>()
    val getCharacterStatus: LiveData<State<CharacterModel>> = _getCharacterStatus

    private val _getLocationStatus = MutableLiveData<State<LocationModel>>()
    val getLocationStatus: LiveData<State<LocationModel>> = _getLocationStatus

    fun getCharacter(id: Int) {
        _getCharacterStatus.postValue(State.Loading)

        getCharacter(
            singleObserver(::handleGetCharacterSuccess, ::handleGetCharacterFailure),
            GetCharacter.Params(id)
        )
    }

    private fun handleGetCharacterSuccess(newCharacter: Character) {
        character = characterModelMapper.mapToUI(newCharacter)
        _getCharacterStatus.postValue(State.Success(character))

        getLocation()
    }

    private fun handleGetCharacterFailure(error: Throwable) {
        _getCharacterStatus.postValue(State.Error(error))
    }

    fun getLocation() {
        val id = character.location?.url?.getIdFromUrl()

        id?.let {
            _getLocationStatus.postValue(State.Loading)

            getLocation(
                singleObserver(::handleGetLocationSuccess, ::handleGetLocationFailure),
                GetLocation.Params(id)
            )
        }
    }

    private fun handleGetLocationSuccess(newLocation: Location) {
        location = locationModelMapper.mapToUI(newLocation)
        _getLocationStatus.postValue(State.Success(location))
    }

    private fun handleGetLocationFailure(error: Throwable) {
        _getLocationStatus.postValue(State.Error(error))
    }

    override fun onCleared() {
        super.onCleared()
        getCharacter.dispose()
        getLocation.dispose()
    }
}