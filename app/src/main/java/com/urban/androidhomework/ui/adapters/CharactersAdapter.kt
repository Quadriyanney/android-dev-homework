package com.urban.androidhomework.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.urban.androidhomework.databinding.ItemCharacterBinding
import com.urban.androidhomework.presentation.models.character.CharacterModel
import com.urban.androidhomework.utils.imageloader.ImageLoader
import com.urban.androidhomework.utils.recyclerview.AutoUpdateRecyclerView
import kotlin.properties.Delegates

class CharactersAdapter(
    private val imageLoader: ImageLoader,
    private val onCharacterItemClick: ((CharacterModel) -> Unit)?
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

        @SuppressLint("SetTextI18n")
        fun bind(character: CharacterModel) {
            binding.tvCharacterName.text = "Name: ${character.name}"
            binding.tvCharacterType.text = "Gender: ${character.gender}"

            if (character.image.isNotEmpty()) {
                imageLoader.loadImage(character.image, binding.ivCharacterImage)
            }

            binding.root.setOnClickListener {
                onCharacterItemClick?.invoke(character)
            }
        }
    }
}