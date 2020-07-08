package mappers.character

import com.google.common.truth.Truth.assertThat
import com.urban.androidhomework.remote.data.mappers.character.CharacterLocationEntityMapper
import com.urban.androidhomework.remote.data.models.character.CharacterLocationEntity
import com.urban.androidhomework.domain.models.character.CharacterLocation
import data.CharacterDataFactory
import org.junit.Test

class CharacterLocationEntityMapperTest {

    private val mapper = CharacterLocationEntityMapper()

    @Test
    fun `check that entity model maps to domain`() {
        val entity = CharacterDataFactory.characterLocationEntity
        val domain = mapper.mapFromEntity(entity)

        assertEqualData(entity, domain)
    }

    @Test
    fun `check that domain model maps to entity`() {
        val domain = CharacterDataFactory.characterLocation
        val entity = mapper.mapToEntity(domain)

        assertEqualData(entity, domain)
    }

    private fun assertEqualData(entity: CharacterLocationEntity, domain: CharacterLocation) {
        assertThat(entity.name).isEqualTo(domain.name)
        assertThat(entity.url).isEqualTo(domain.url)
    }
}