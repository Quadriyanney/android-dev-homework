package com.urban.androidhomework.viewmodel.character

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.*
import com.urban.androidhomework.assertUIState
import com.urban.androidhomework.data.CharacterDataFactory
import com.urban.androidhomework.data.CommonDataFactory
import com.urban.androidhomework.data.CommonDataFactory.data
import com.urban.androidhomework.data.LocationDataFactory
import com.urban.androidhomework.domain.models.character.Character
import com.urban.androidhomework.domain.models.location.Location
import com.urban.androidhomework.domain.usecases.character.GetCharacter
import com.urban.androidhomework.domain.usecases.location.GetLocation
import com.urban.androidhomework.presentation.mappers.character.CharacterModelMapper
import com.urban.androidhomework.presentation.mappers.location.LocationModelMapper
import com.urban.androidhomework.presentation.models.character.CharacterModel
import com.urban.androidhomework.presentation.models.location.LocationModel
import com.urban.androidhomework.ui.character.characterdetails.CharacterViewModel
import com.urban.androidhomework.utils.State
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CharacterViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getLocation: GetLocation

    @Mock
    private lateinit var getCharacter: GetCharacter

    @Mock
    private lateinit var locationModelMapper: LocationModelMapper

    @Mock
    private lateinit var characterModelMapper: CharacterModelMapper

    private lateinit var characterViewModel: CharacterViewModel

    private val getCharacterCaptor:
        KArgumentCaptor<DisposableSingleObserver<Character>> = argumentCaptor()

    private val getLocationCaptor:
        KArgumentCaptor<DisposableSingleObserver<Location>> = argumentCaptor()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        characterViewModel = CharacterViewModel(getLocation, getCharacter, locationModelMapper, characterModelMapper)
    }

    @After
    fun dispose() {
        getLocation.dispose()
        getCharacter.dispose()
    }

    @Test
    fun `check that executing getCharacter returns success`() {
        val characterModel = CharacterDataFactory.characterModel
        val characterDomainModel = CharacterDataFactory.characterDomainModel

        stubCharacterMapper(characterDomainModel, characterModel)

        `capture getCharacter subscriber`()
        getCharacterCaptor.firstValue.onSuccess(characterDomainModel)

        val returnedCharacterModel = characterViewModel.character
        val uiState = characterViewModel.getCharacterState.data

        assertUIState(State.Success(returnedCharacterModel), uiState)
        assertThat(characterModel).isEqualTo(returnedCharacterModel)
    }

    @Test
    fun `check that executing getCharacter returns error`() {
        val error = CommonDataFactory.throwable

        `capture getCharacter subscriber`()
        getCharacterCaptor.firstValue.onError(error)

        val uiState = characterViewModel.getCharacterState.data

        assertUIState(State.Error(error), uiState)
        assertThat(characterViewModel.isCharacterInitialized).isFalse()
    }

    @Test
    fun `check that executing getLocation returns success`() {
        val locationModel = LocationDataFactory.locationModel
        val locationDomainModel = LocationDataFactory.locationDomainModel

        stubLocationMapper(locationDomainModel, locationModel)

        `capture getLocation subscriber`()
        getLocationCaptor.firstValue.onSuccess(locationDomainModel)

        val returnedLocationModel = characterViewModel.location
        val uiState = characterViewModel.getLocationState.data

        assertUIState(State.Success(returnedLocationModel), uiState)
        assertThat(locationModel).isEqualTo(returnedLocationModel)
    }

    @Test
    fun `check that executing getLocation returns error`() {
        val error = CommonDataFactory.throwable

        `capture getLocation subscriber`()
        getLocationCaptor.firstValue.onError(error)

        val uiState = characterViewModel.getLocationState.data

        assertUIState(State.Error(error), uiState)
        assertThat(characterViewModel.isLocationInitialized).isFalse()
    }

    private fun `capture getCharacter subscriber`() {
        characterViewModel.getCharacter(CommonDataFactory.int)
        verify(getCharacter)(getCharacterCaptor.capture(), any())
    }

    private fun `capture getLocation subscriber`() {
        characterViewModel.getLocation(CommonDataFactory.int)
        verify(getLocation)(getLocationCaptor.capture(), any())
    }

    private fun stubCharacterMapper(domainModel: Character, uiModel: CharacterModel) {
        whenever(characterModelMapper.mapToUI(domainModel)).thenReturn(uiModel)
    }

    private fun stubLocationMapper(domainModel: Location, uiModel: LocationModel) {
        whenever(locationModelMapper.mapToUI(domainModel)).thenReturn(uiModel)
    }
}