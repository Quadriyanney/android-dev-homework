package repositories

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.urban.androidhomework.data.mappers.character.CharacterEntityMapper
import com.urban.androidhomework.data.models.character.CharacterEntity
import com.urban.androidhomework.data.remote.ICharacterRemote
import com.urban.androidhomework.data.repositories.CharacterRepository
import com.urban.androidhomework.domain.models.character.Character
import data.CharacterDataFactory
import data.CommonDataFactory
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CharacterRepositoryTest {

    private lateinit var characterRepository: CharacterRepository

    @Mock
    private lateinit var characterRemote: ICharacterRemote

    @Mock
    private lateinit var characterEntityMapper: CharacterEntityMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        characterRepository = CharacterRepository(characterRemote, characterEntityMapper)
    }

    @Test
    fun `check that getCharacter completes`() {
        stubGetCharacter(Single.just(CharacterDataFactory.characterEntity))
        stubCharacterMapper(any(), CharacterDataFactory.character)

        characterRepository.getCharacter(CommonDataFactory.int)
            .test()
            .assertComplete()
            .dispose()
    }

    @Test
    fun `check that executing getCharacter returns success`() {
        val characterEntity = CharacterDataFactory.characterEntity
        val character = CharacterDataFactory.character

        stubGetCharacter(Single.just(characterEntity))
        stubCharacterMapper(characterEntity, character)

        characterRepository.getCharacter(CommonDataFactory.int)
            .test()
            .assertValue(character)
            .dispose()
    }

    @Test
    fun `check that executing getCharacter returns error`() {
        val error = CommonDataFactory.throwable

        stubGetCharacter(Single.error(error))

        characterRepository.getCharacter(CommonDataFactory.int)
            .test()
            .assertNotComplete()
            .assertError(error)
            .dispose()
    }

    @Test
    fun `check that getCharacters completes`() {
        stubGetCharacters(Single.just(CharacterDataFactory.characterEntities))
        stubCharactersMapper(any(), CharacterDataFactory.characters)

        characterRepository.getCharacters()
            .test()
            .assertComplete()
            .dispose()
    }

    @Test
    fun `check that executing getCharacters returns success`() {
        val characterEntities = CharacterDataFactory.characterEntities
        val characters = CharacterDataFactory.characters

        stubGetCharacters(Single.just(characterEntities))
        stubCharactersMapper(characterEntities, characters)

        characterRepository.getCharacters()
            .test()
            .assertValue(characters)
            .dispose()
    }

    @Test
    fun `check that executing getCharacters returns error`() {
        val error = CommonDataFactory.throwable

        stubGetCharacters(Single.error(error))

        characterRepository.getCharacters()
            .test()
            .assertNotComplete()
            .assertError(error)
            .dispose()
    }

    @Test
    fun `check that getCharactersByIds completes`() {
        stubGetCharactersByIds(Single.just(CharacterDataFactory.characterEntities))
        stubCharactersMapper(any(), CharacterDataFactory.characters)

        characterRepository.getCharactersByIds(CommonDataFactory.ints)
            .test()
            .assertComplete()
            .dispose()
    }

    @Test
    fun `check that executing getCharactersByIds returns success`() {
        val characterEntities = CharacterDataFactory.characterEntities
        val characters = CharacterDataFactory.characters

        stubGetCharactersByIds(Single.just(characterEntities))
        stubCharactersMapper(characterEntities, characters)

        characterRepository.getCharactersByIds(CommonDataFactory.ints)
            .test()
            .assertValue(characters)
            .dispose()
    }

    @Test
    fun `check that executing getCharactersByIds returns error`() {
        val error = CommonDataFactory.throwable

        stubGetCharactersByIds(Single.error(error))

        characterRepository.getCharactersByIds(CommonDataFactory.ints)
            .test()
            .assertNotComplete()
            .assertError(error)
            .dispose()
    }

    private fun stubGetCharacter(single: Single<CharacterEntity>) {
        whenever(characterRemote.getCharacter(any())).thenReturn(single)
    }

    private fun stubGetCharacters(single: Single<List<CharacterEntity>>) {
        whenever(characterRemote.getCharacters()).thenReturn(single)
    }

    private fun stubGetCharactersByIds(single: Single<List<CharacterEntity>>) {
        whenever(characterRemote.getCharactersByIds(any())).thenReturn(single)
    }

    private fun stubCharacterMapper(entity: CharacterEntity, domain: Character) {
        whenever(characterEntityMapper.mapFromEntity(entity)).thenReturn(domain)
    }

    private fun stubCharactersMapper(entities: List<CharacterEntity>, domainModels: List<Character>) {
        whenever(characterEntityMapper.mapFromEntityList(entities)).thenReturn(domainModels)
    }
}
