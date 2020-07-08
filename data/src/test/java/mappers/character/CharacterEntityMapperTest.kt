package mappers.character

import com.google.common.truth.Truth.assertThat
import com.urban.androidhomework.remote.data.mappers.character.CharacterEntityMapper
import com.urban.androidhomework.remote.data.models.character.CharacterEntity
import com.urban.androidhomework.domain.models.character.Character
import data.CharacterDataFactory
import konveyor.base.randomBuild
import org.junit.Test

class CharacterEntityMapperTest {

    private val mapper = CharacterEntityMapper(randomBuild())

    @Test
    fun `check that entity model maps to domain`() {
        val entity = CharacterDataFactory.characterEntity
        val domain = mapper.mapFromEntity(entity)

        assertEqualData(entity, domain)
    }

    @Test
    fun `check that domain model maps to entity`() {
        val domain = CharacterDataFactory.character
        val entity = mapper.mapToEntity(domain)

        assertEqualData(entity, domain)
    }

    private fun assertEqualData(entity: CharacterEntity, domain: Character) {
        assertThat(entity.id).isEqualTo(domain.id)
        assertThat(entity.name).isEqualTo(domain.name)
        assertThat(entity.status).isEqualTo(domain.status)
        assertThat(entity.species).isEqualTo(domain.species)
        assertThat(entity.type).isEqualTo(domain.type)
        assertThat(entity.gender).isEqualTo(domain.gender)
        assertThat(entity.image).isEqualTo(domain.image)
        assertThat(entity.created).isEqualTo(domain.created)
    }
}