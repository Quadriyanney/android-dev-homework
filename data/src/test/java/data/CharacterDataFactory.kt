package data

import com.urban.androidhomework.data.models.character.CharacterEntity
import com.urban.androidhomework.data.models.character.CharacterLocationEntity
import com.urban.androidhomework.domain.models.character.Character
import com.urban.androidhomework.domain.models.character.CharacterLocation
import konveyor.base.randomBuild

internal object CharacterDataFactory {

    val character get() = randomBuild<Character>()

    val characters get() = MutableList(10) { character }

    val characterEntity get() = randomBuild<CharacterEntity>()

    val characterEntities get() = MutableList(10) { characterEntity }

    val characterLocation get() = randomBuild<CharacterLocation>()

    val characterLocationEntity get() = randomBuild<CharacterLocationEntity>()
}
