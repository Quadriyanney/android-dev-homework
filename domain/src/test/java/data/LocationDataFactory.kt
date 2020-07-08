package data

import com.urban.androidhomework.domain.models.location.Location
import konveyor.base.randomBuild

internal object LocationDataFactory {

    val location get() = randomBuild<Location>()
}