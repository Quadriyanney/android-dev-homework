package usecases.character

import assertCompleteAndDispose
import assertErrorAndDispose
import assertValueAndDispose
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.urban.androidhomework.domain.executor.IExecutionThread
import com.urban.androidhomework.domain.models.character.Character
import com.urban.androidhomework.domain.repositories.ICharacterRepository
import com.urban.androidhomework.domain.usecases.character.GetCharacters
import data.CharacterDataFactory
import data.CommonDataFactory
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetCharactersTest {

    private lateinit var getCharacters: GetCharacters

    @Mock
    private lateinit var characterRepository: ICharacterRepository

    @Mock
    private lateinit var executionThread: IExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getCharacters = GetCharacters(characterRepository, executionThread)
    }

    @Test
    fun `check that getCharacters completes`() {
        stubGetCharacters(Single.just(CharacterDataFactory.characters))
        getCharacters.build().assertCompleteAndDispose()
    }

    @Test
    fun `check that getCharacters returns error`() {
        val error = Throwable(CommonDataFactory.string)
        stubGetCharacters(Single.error(error))
        getCharacters.build().assertErrorAndDispose(error)
    }

    @Test
    fun `check that calling getCharacters returns data`() {
        val characters = CharacterDataFactory.characters
        stubGetCharacters(Single.just(characters))
        getCharacters.build().assertValueAndDispose(characters)
    }

    @Test
    fun `check that getCharacters calls repository once`() {
        stubGetCharacters(Single.just(CharacterDataFactory.characters))
        getCharacters.build().test()
        verify(characterRepository).getCharacter(any())
    }

    private fun stubGetCharacters(single: Single<List<Character>>) {
        whenever(characterRepository.getCharacters()).thenReturn(single)
    }
}