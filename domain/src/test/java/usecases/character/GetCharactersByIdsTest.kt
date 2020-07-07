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
import com.urban.androidhomework.domain.usecases.character.GetCharactersByIds
import data.CharacterDataFactory
import data.CommonDataFactory
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetCharactersByIdsTest {

    private lateinit var getCharactersByIds: GetCharactersByIds

    @Mock
    private lateinit var characterRepository: ICharacterRepository

    @Mock
    private lateinit var executionThread: IExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getCharactersByIds = GetCharactersByIds(characterRepository, executionThread)
    }

    @Test
    fun `check that getCharactersByIds completes`() {
        stubGetCharactersByIds(Single.just(CharacterDataFactory.characters))
        getCharactersByIds.build(GetCharactersByIds.Params(CommonDataFactory.ints))
            .assertCompleteAndDispose()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `check that calling getCharactersByIds without params throws exception`() {
        getCharactersByIds.build().test()
    }

    @Test
    fun `check that getCharactersByIds returns error`() {
        val error = Throwable(CommonDataFactory.string)
        stubGetCharactersByIds(Single.error(error))
        getCharactersByIds.build(GetCharactersByIds.Params(CommonDataFactory.ints))
            .assertErrorAndDispose(error)
    }

    @Test
    fun `check that calling getCharactersByIds returns data`() {
        val characters = CharacterDataFactory.characters
        stubGetCharactersByIds(Single.just(characters))
        getCharactersByIds.build(GetCharactersByIds.Params(CommonDataFactory.ints))
            .assertValueAndDispose(characters)
    }

    @Test
    fun `check that getCharactersByIds calls repository once`() {
        stubGetCharactersByIds(Single.just(CharacterDataFactory.characters))
        getCharactersByIds.build(GetCharactersByIds.Params(CommonDataFactory.ints)).test()
        verify(characterRepository).getCharactersByIds(any())
    }

    private fun stubGetCharactersByIds(single: Single<List<Character>>) {
        whenever(characterRepository.getCharactersByIds(any())).thenReturn(single)
    }
}