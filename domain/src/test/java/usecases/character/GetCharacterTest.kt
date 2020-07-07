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
import com.urban.androidhomework.domain.usecases.character.GetCharacter
import data.CharacterDataFactory
import data.CommonDataFactory
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetCharacterTest {

    private lateinit var getCharacter: GetCharacter

    @Mock
    private lateinit var characterRepository: ICharacterRepository

    @Mock
    private lateinit var executionThread: IExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getCharacter = GetCharacter(characterRepository, executionThread)
    }

    @Test
    fun `check that getCharacter completes`() {
        stubGetCharacter(Single.just(CharacterDataFactory.character))
        getCharacter.build(GetCharacter.Params(CommonDataFactory.int)).assertCompleteAndDispose()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `check that calling getCharacter without params throws exception`() {
        getCharacter.build().test()
    }

    @Test
    fun `check that getCharacter returns error`() {
        val error = Throwable(CommonDataFactory.string)
        stubGetCharacter(Single.error(error))
        getCharacter.build(GetCharacter.Params(CommonDataFactory.int)).assertErrorAndDispose(error)
    }

    @Test
    fun `check that calling getCharacter returns data`() {
        val character = CharacterDataFactory.character
        stubGetCharacter(Single.just(character))
        getCharacter.build(GetCharacter.Params(CommonDataFactory.int)).assertValueAndDispose(character)
    }

    @Test
    fun `check that getCharacter calls repository once`() {
        stubGetCharacter(Single.just(CharacterDataFactory.character))
        getCharacter.build(GetCharacter.Params(CommonDataFactory.int)).test()
        verify(characterRepository).getCharacter(any())
    }

    private fun stubGetCharacter(single: Single<Character>) {
        whenever(characterRepository.getCharacter(any())).thenReturn(single)
    }
}