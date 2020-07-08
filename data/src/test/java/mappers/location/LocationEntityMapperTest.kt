package mappers.location

import com.google.common.truth.Truth.assertThat
import com.urban.androidhomework.remote.data.mappers.location.LocationEntityMapper
import com.urban.androidhomework.remote.data.models.location.LocationEntity
import com.urban.androidhomework.domain.models.location.Location
import data.LocationDataFactory
import org.junit.Test

class LocationEntityMapperTest {

    private val mapper = LocationEntityMapper()

    @Test
    fun `check that entity model maps to domain`() {
        val entity = LocationDataFactory.locationEntity
        val domain = mapper.mapFromEntity(entity)

        assertEqualData(entity, domain)
    }

    @Test
    fun `check that domain model maps to entity`() {
        val domain = LocationDataFactory.location
        val entity = mapper.mapToEntity(domain)

        assertEqualData(entity, domain)
    }

    private fun assertEqualData(entity: LocationEntity, domain: Location) {
        assertThat(entity.id).isEqualTo(domain.id)
        assertThat(entity.name).isEqualTo(domain.name)
        assertThat(entity.type).isEqualTo(domain.type)
        assertThat(entity.dimension).isEqualTo(domain.dimension)
        assertThat(entity.residents).isEqualTo(domain.residents)
    }
}