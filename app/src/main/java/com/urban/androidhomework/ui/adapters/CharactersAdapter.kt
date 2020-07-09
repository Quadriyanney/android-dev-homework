package com.urban.androidhomework.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.urban.androidhomework.R
import com.urban.androidhomework.databinding.ItemCharacterBinding
import com.urban.androidhomework.presentation.models.character.CharacterModel
import com.urban.androidhomework.utils.imageloader.ImageLoader
import com.urban.androidhomework.utils.recyclerview.AutoUpdateRecyclerView
import kotlin.properties.Delegates

/**
 * Adapter Class to populate list of [CharacterModel]
 */
class CharactersAdapter(
    private val imageLoader: ImageLoader,
    private val onCharacterItemClick: ((CharacterModel, View) -> Unit)?
) : RecyclerView.Adapter<CharactersAdapter.CharacterItemViewHolder>(), AutoUpdateRecyclerView {

    var characters: List<CharacterModel> by Delegates.observable(emptyList()) { _, old, new ->
        autoNotify(old, new) { oldCharacter, newCharacter ->
            oldCharacter.id == newCharacter.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        return CharacterItemViewHolder(
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    inner class CharacterItemViewHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: CharacterModel) {
            val context = binding.root.context

            binding.tvCharacterName.text = context.getString(R.string.character_name, character.name)
            binding.tvCharacterType.text = context.getString(R.string.character_gender, character.gender)

            if (character.image.isNotEmpty()) {
                imageLoader.loadImage(character.image, binding.ivCharacterImage)
            }

            ViewCompat.setTransitionName(binding.ivCharacterImage, character.name)

            binding.root.setOnClickListener {
                onCharacterItemClick?.invoke(character, binding.ivCharacterImage)
            }
        }
    }
}
