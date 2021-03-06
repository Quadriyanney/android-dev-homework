package com.urban.androidhomework.ui.character.characterdetails

import android.content.Context
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.urban.androidhomework.R
import com.urban.androidhomework.databinding.FragmentCharacterBinding
import com.urban.androidhomework.di.component.ui.inject
import com.urban.androidhomework.presentation.models.character.CharacterModel
import com.urban.androidhomework.presentation.models.location.LocationModel
import com.urban.androidhomework.ui.base.BaseFragment
import com.urban.androidhomework.utils.State
import com.urban.androidhomework.utils.factoryViewModels
import com.urban.androidhomework.utils.imageloader.ImageLoader
import com.urban.androidhomework.utils.isAvailable
import com.urban.androidhomework.utils.observe
import com.urban.androidhomework.utils.onBackPressed
import com.urban.androidhomework.utils.showSnackBar
import javax.inject.Inject

class CharacterFragment : BaseFragment(R.layout.fragment_character) {

    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var _binding: FragmentCharacterBinding
    private val binding get() = _binding

    private val fragmentArgs: CharacterFragmentArgs by navArgs()
    private val viewModel: CharacterViewModel by factoryViewModels()

    override fun setUp() {
        // // Check to know if this fragment was started from a deep-link or a nav action
        if (fragmentArgs.character != null) {
            viewModel.character = fragmentArgs.character!!
            viewModel.performGetCharacterLocation()

            // // Set transition name for shared element transition
            ViewCompat.setTransitionName(binding.ivCharacterImage, viewModel.character.name)

            displayCharacterDetails(viewModel.character)
        } else {
            viewModel.getCharacter(fragmentArgs.characterId)
        }

        binding.btnLocationRetry.setOnClickListener {
            viewModel.performGetCharacterLocation()
        }

        binding.btnPersonalRetry.setOnClickListener {
            viewModel.getCharacter(fragmentArgs.characterId)
        }

        binding.btnShowLocationResidents.setOnClickListener {
            goToLocationResidentsScreen()
        }

        observe(viewModel.getLocationState, ::observeGetLocation)
        observe(viewModel.getCharacterState, ::observeGetCharacter)
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

        binding.tvCharacterName.text = getString(R.string.character_name, character.name)
        binding.tvCharacterGender.text = getString(R.string.character_gender, character.gender)
        binding.tvCharacterSpecie.text = getString(R.string.character_specie, character.species)
        binding.tvCharacterStatus.text = getString(R.string.character_status, character.status)
        binding.tvCharacterType.text = getString(R.string.character_type, character.type)
    }

    private fun displayLocationDetails(location: LocationModel) {
        binding.tvLocationName.text = getString(R.string.location_name, location.name)
        binding.tvLocationType.text = getString(R.string.location_type, location.type)
        binding.tvLocationDimension.text = getString(R.string.location_dimension, location.dimension)
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

    override fun onAttach(context: Context) {
        super.onAttach(context)

        onBackPressed {
            findNavController().navigateUp()
        }
    }

    override fun bindView(view: View) {
        _binding = FragmentCharacterBinding.bind(view)
    }
}
