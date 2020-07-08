package com.urban.androidhomework.remote.impl

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.urban.androidhomework.remote.api.CharacterService
import com.urban.androidhomework.remote.data.CharacterDataFactory
import com.urban.androidhomework.remote.data.CommonDataFactory
import com.urban.androidhomework.remote.data.models.character.CharacterEntity
import com.urban.androidhomework.remote.mappers.character.CharacterNetworkModelMapper
import com.urban.androidhomework.remote.models.charcater.CharacterNetworkModel
import com.urban.androidhomework.remote.models.networkresponse.GetCharactersResponse
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CharacterRemoteTest {

    private lateinit var characterRemote: CharacterRemote

    @Mock
    private lateinit var characterService: CharacterService

    @Mock
    private lateinit var characterNetworkModelMapper: CharacterNetworkModelMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        characterRemote = CharacterRemote(characterService, characterNetworkModelMapper)
    }

    @Test
    fun `check that getCharacter completes`() {
        stubGetCharacter(Single.just(CharacterDataFactory.characterModel))
        stubCharacterMapper(CharacterDataFactory.characterEntity, any())

        characterRemote.getCharacter(CommonDataFactory.int)
            .test()
            .assertComplete()
            .dispose()
    }

    @Test
    fun `check that executing getCharacter returns success`() {
        val characterEntity = CharacterDataFactory.characterEntity
        val characterModel = CharacterDataFactory.characterModel

        stubGetCharacter(Single.just(characterModel))
        stubCharacterMapper(characterEntity, characterModel)

        characterRemote.getCharacter(CommonDataFactory.int)
            .test()
            .assertValue(characterEntity)
            .dispose()
    }

    @Test
    fun `check that getCharacters completes`() {
        stubGetCharacters(Single.just(CharacterDataFactory.charactersResponse))
        stubCharactersMapper(CharacterDataFactory.characterEntities, any())

        characterRemote.getCharacters()
            .test()
            .assertComplete()
            .dispose()
    }

    @Test
    fun `check that executing getCharacters returns success`() {
        val characterResponse = CharacterDataFactory.charactersResponse
        val characterEntities = CharacterDataFactory.characterEntities

        stubGetCharacters(Single.just(characterResponse))
        stubCharactersMapper(characterEntities, characterResponse.results)

        characterRemote.getCharacters()
            .test()
            .assertValue(characterEntities)
            .dispose()
    }

    @Test
    fun `check that getCharactersByIds completes`() {
        stubGetCharactersByIds(Single.just(CharacterDataFactory.characterModels))
        stubCharactersMapper(CharacterDataFactory.characterEntities, any())

        characterRemote.getCharactersByIds(CommonDataFactory.ints)
            .test()
            .assertComplete()
            .dispose()
    }

    @Test
    fun `check that executing getCharactersByIds returns success`() {
        val characterEntities = CharacterDataFactory.characterEntities
        val characterModels = CharacterDataFactory.characterModels

        stubGetCharactersByIds(Single.just(characterModels))
        stubCharactersMapper(characterEntities, characterModels)

        characterRemote.getCharactersByIds(CommonDataFactory.ints)
            .test()
            .assertValue(characterEntities)
            .dispose()
    }

    private fun stubGetCharacter(single: Single<CharacterNetworkModel>) {
        whenever(characterService.getCharacter(any())).thenReturn(single)
    }

    private fun stubGetCharacters(single: Single<GetCharactersResponse>) {
        whenever(characterService.getCharacters()).thenReturn(single)
    }

    private fun stubGetCharactersByIds(single: Single<List<CharacterNetworkModel>>) {
        whenever(characterService.getCharactersByIds(any())).thenReturn(single)
    }

    private fun stubCharacterMapper(entity: CharacterEntity, model: CharacterNetworkModel) {
        whenever(characterNetworkModelMapper.mapFromModel(model)).thenReturn(entity)
    }

    private fun stubCharactersMapper(entities: List<CharacterEntity>, models: List<CharacterNetworkModel>) {
        whenever(characterNetworkModelMapper.mapModelList(models)).thenReturn(entities)
    }
}