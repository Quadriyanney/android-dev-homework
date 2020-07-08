package com.urban.androidhomework.viewmodel.location

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.urban.androidhomework.assertUIState
import com.urban.androidhomework.data.CharacterDataFactory
import com.urban.androidhomework.data.CommonDataFactory
import com.urban.androidhomework.data.CommonDataFactory.data
import com.urban.androidhomework.domain.models.character.Character
import com.urban.androidhomework.domain.usecases.character.GetCharactersByIds
import com.urban.androidhomework.presentation.mappers.character.CharacterModelMapper
import com.urban.androidhomework.presentation.models.character.CharacterModel
import com.urban.androidhomework.ui.location.LocationResidentsViewModel
import com.urban.androidhomework.utils.State
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LocationResidentsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getCharactersByIds: GetCharactersByIds

    @Mock
    private lateinit var characterModelMapper: CharacterModelMapper

    private lateinit var locationResidentsViewModel: LocationResidentsViewModel

    private val getCharactersCaptor:
        KArgumentCaptor<DisposableSingleObserver<List<Character>>> = argumentCaptor()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        locationResidentsViewModel = LocationResidentsViewModel(getCharactersByIds, characterModelMapper)
    }

    @After
    fun dispose() {
        getCharactersByIds.dispose()
    }

    @Test
    fun `check that executing getCharactersByIds returns success`() {
        val characterModels = CharacterDataFactory.characterModels
        val characterDomainModels = CharacterDataFactory.characterDomainModels

        stubCharactersMapper(characterDomainModels, characterModels)

        `capture getCharactersByIds subscriber`()
        getCharactersCaptor.firstValue.onSuccess(characterDomainModels)

        val uiState = locationResidentsViewModel.getCharactersState.data

        assertUIState(State.Success(characterModels), uiState)
    }

    @Test
    fun `check that executing getCharactersByIds returns error`() {
        val error = CommonDataFactory.throwable

        `capture getCharactersByIds subscriber`()
        getCharactersCaptor.firstValue.onError(error)

        val uiState = locationResidentsViewModel.getCharactersState.data

        assertUIState(State.Error(error), uiState)
    }

    private fun `capture getCharactersByIds subscriber`() {
        locationResidentsViewModel.getCharacters(CommonDataFactory.ints)
        verify(getCharactersByIds)(getCharactersCaptor.capture(), any())
    }

    private fun stubCharactersMapper(domainModels: List<Character>, uiModels: List<CharacterModel>) {
        whenever(characterModelMapper.mapToUIList(domainModels)).thenReturn(uiModels)
    }
}