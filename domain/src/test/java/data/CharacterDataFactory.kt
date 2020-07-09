package data

import com.urban.androidhomework.domain.models.character.Character
import konveyor.base.randomBuild

internal object CharacterDataFactory {

    val character get() = randomBuild<Character>()

    val characters get() = MutableList(10) { character }
}
