package com.urban.androidhomework.viewmodel.character

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.KArgumentCaptor
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.urban.androidhomework.assertUIState
import com.urban.androidhomework.data.CharacterDataFactory
import com.urban.androidhomework.data.CommonDataFactory
import com.urban.androidhomework.data.CommonDataFactory.data
import com.urban.androidhomework.domain.models.character.Character
import com.urban.androidhomework.domain.usecases.character.GetCharacters
import com.urban.androidhomework.presentation.mappers.character.CharacterModelMapper
import com.urban.androidhomework.presentation.models.character.CharacterModel
import com.urban.androidhomework.ui.character.characterslist.CharactersViewModel
import com.urban.androidhomework.utils.State
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertTrue

class CharactersViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getCharacters: GetCharacters

    @Mock
    private lateinit var characterModelMapper: CharacterModelMapper

    private lateinit var charactersViewModel: CharactersViewModel

    private val getCharactersCaptor:
        KArgumentCaptor<DisposableSingleObserver<List<Character>>> = argumentCaptor()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        charactersViewModel = CharactersViewModel(getCharacters, characterModelMapper)
    }

    @After
    fun dispose() {
        getCharacters.dispose()
    }

    @Test
    fun `check that executing getCharacters returns success`() {
        val characterModels = CharacterDataFactory.characterModels
        val characterDomainModels = CharacterDataFactory.characterDomainModels

        stubCharactersMapper(characterDomainModels, characterModels)

        `capture getCharacters subscriber`()
        getCharactersCaptor.firstValue.onSuccess(characterDomainModels)

        val returnedCharacterModels = charactersViewModel.allCharacters
        val uiState = charactersViewModel.getCharactersState.data

        assertUIState(State.Success(returnedCharacterModels), uiState)
        assertThat(characterModels).isEqualTo(returnedCharacterModels)
    }

    @Test
    fun `check that executing getCharacters returns error`() {
        val error = CommonDataFactory.throwable

        `capture getCharacters subscriber`()
        getCharactersCaptor.firstValue.onError(error)

        val uiState = charactersViewModel.getCharactersState.data

        assertUIState(State.Error(error), uiState)
        assertTrue(charactersViewModel.allCharacters.isEmpty())
    }

    private fun `capture getCharacters subscriber`() {
        verify(getCharacters)(getCharactersCaptor.capture(), eq(null))
    }

    private fun stubCharactersMapper(domainModels: List<Character>, uiModels: List<CharacterModel>) {
        whenever(characterModelMapper.mapToUIList(domainModels)).thenReturn(uiModels)
    }
}
