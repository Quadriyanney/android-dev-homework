package data

import com.urban.androidhomework.domain.models.location.Location
import konveyor.base.randomBuild

object LocationDataFactory {

    val location get() = randomBuild<Location>()
}