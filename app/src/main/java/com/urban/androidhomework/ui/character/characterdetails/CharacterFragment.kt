package com.urban.androidhomework.ui.character.characterdetails

import android.annotation.SuppressLint
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.urban.androidhomework.R
import com.urban.androidhomework.databinding.FragmentCharacterBinding
import com.urban.androidhomework.di.component.ui.inject
import com.urban.androidhomework.presentation.models.character.CharacterModel
import com.urban.androidhomework.presentation.models.location.LocationModel
import com.urban.androidhomework.ui.base.BaseFragment
import com.urban.androidhomework.utils.*
import com.urban.androidhomework.utils.imageloader.ImageLoader
import javax.inject.Inject

@SuppressLint("SetTextI18n")
class CharacterFragment : BaseFragment(R.layout.fragment_character) {

    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var _binding: FragmentCharacterBinding
    private val binding get() = _binding

    private val fragmentArgs: CharacterFragmentArgs by navArgs()
    private val viewModel: CharacterViewModel by factoryViewModels()

    override fun setUp() {
        if (fragmentArgs.character != null) {
            viewModel.character = fragmentArgs.character!!
            viewModel.getLocation()

            ViewCompat.setTransitionName(binding.ivCharacterImage, viewModel.character.name)
            displayCharacterDetails(viewModel.character)
        } else {
            viewModel.getCharacter(fragmentArgs.characterId)
        }

        binding.btnLocationRetry.setOnClickListener {
            viewModel.getLocation()
        }

        binding.btnPersonalRetry.setOnClickListener {
            viewModel.getCharacter(fragmentArgs.characterId)
        }

        binding.btnShowLocationResidents.setOnClickListener {
            goToLocationResidentsScreen()
        }

        observe(viewModel.getLocationStatus, ::observeGetLocation)
        observe(viewModel.getCharacterStatus, ::observeGetCharacter)
    }

    private fun goToLocationResidentsScreen() {
        goTo(
            CharacterFragmentDirections.actionCharacterFragmentToLocationResidentsFragment(
                viewModel.residentsIds.toIntArray(),
                viewModel.location.name
            )
        )
    }

    private fun displayCharacterDetails(character: CharacterModel) {
        supportActionBar?.title = character.name

        if (character.image.isAvailable()) {
            imageLoader.loadImage(character.image, binding.ivCharacterImage)
        }

        binding.tvCharacterName.text = "Name: ${character.name}"
        binding.tvCharacterGender.text = "Gender: ${character.gender}"
        binding.tvCharacterSpecie.text = "Specie: ${character.species}"
        binding.tvCharacterStatus.text = "Status: ${character.status}"
        binding.tvCharacterType.text = "Type: ${character.type}"
    }

    private fun displayLocationDetails(location: LocationModel) {
        binding.tvLocationName.text = "Name: ${location.name}"
        binding.tvLocationType.text = "Type: ${location.type}"
        binding.tvLocationDimension.text = "Dimension: ${location.dimension}"
    }

    private fun observeGetCharacter(state: State<CharacterModel>) {
        when (state) {
            is State.Success -> {
                hideCharacterLoading()
                displayCharacterDetails(state.data)
            }
            is State.Error -> {
                hideCharacterLoading(state.error.localizedMessage)
                showSnackBar(state.error.localizedMessage)
            }
            is State.Loading -> showCharacterLoading()
        }
    }

    private fun showCharacterLoading() {
        binding.personalLoadingGroup.isVisible = true
        binding.personalErrorGroup.isVisible = false
        binding.personalContentGroup.visibility = View.INVISIBLE
    }

    private fun hideCharacterLoading(error: String? = null) {
        binding.personalLoadingGroup.isVisible = false

        if (error.isNullOrEmpty()) {
            binding.personalContentGroup.isVisible = true
        } else {
            binding.tvPersonalError.text = error
            binding.personalErrorGroup.isVisible = true
        }
    }

    private fun observeGetLocation(state: State<LocationModel>) {
        when (state) {
            is State.Success -> {
                hideLocationLoading()
                displayLocationDetails(state.data)
            }
            is State.Error -> {
                hideLocationLoading(state.error.localizedMessage)
                showSnackBar(state.error.localizedMessage)
            }
            is State.Loading -> showLocationLoading()
        }
    }

    private fun showLocationLoading() {
        binding.locationLoadingGroup.isVisible = true
        binding.locationErrorGroup.isVisible = false
        binding.locationContentGroup.visibility = View.INVISIBLE
    }

    private fun hideLocationLoading(error: String? = null) {
        binding.locationLoadingGroup.isVisible = false

        if (error.isNullOrEmpty()) {
            binding.locationContentGroup.isVisible = true
        } else {
            binding.tvLocationError.text = error
            binding.locationErrorGroup.isVisible = true
        }
    }

    override fun injectDependencies() {
        inject()
    }

    override fun bindView(view: View) {
        _binding = FragmentCharacterBinding.bind(view)
    }
}