package data

import com.urban.androidhomework.remote.data.models.location.LocationEntity
import com.urban.androidhomework.domain.models.location.Location
import konveyor.base.randomBuild

internal object LocationDataFactory {

    val location get() = randomBuild<Location>()

    val locationEntity get() = randomBuild<LocationEntity>()
}