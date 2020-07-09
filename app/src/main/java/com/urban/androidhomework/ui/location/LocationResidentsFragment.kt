package com.urban.androidhomework.ui.location

import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.urban.androidhomework.R
import com.urban.androidhomework.databinding.FragmentLocationResidentsBinding
import com.urban.androidhomework.di.component.ui.inject
import com.urban.androidhomework.presentation.models.character.CharacterModel
import com.urban.androidhomework.ui.adapters.CharactersAdapter
import com.urban.androidhomework.ui.base.BaseFragment
import com.urban.androidhomework.utils.State
import com.urban.androidhomework.utils.factoryViewModels
import com.urban.androidhomework.utils.imageloader.ImageLoader
import com.urban.androidhomework.utils.observe
import com.urban.androidhomework.utils.showSnackBar
import javax.inject.Inject

class LocationResidentsFragment : BaseFragment(R.layout.fragment_location_residents) {

    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var _binding: FragmentLocationResidentsBinding
    private val binding get() = _binding

    private val fragmentArgs: LocationResidentsFragmentArgs by navArgs()
    private val viewModel: LocationResidentsViewModel by factoryViewModels()

    private val characterIds get() = fragmentArgs.characterIds.toList()

    private val charactersAdapter by lazy {
        CharactersAdapter(imageLoader, null)
    }

    override fun setUp() {
        supportActionBar?.title = fragmentArgs.locationName

        binding.rvResidents.adapter = charactersAdapter

        viewModel.getCharacters(characterIds)

        observe(viewModel.getCharactersState, ::observeGetCharacters)
    }

    private fun observeGetCharacters(state: State<List<CharacterModel>>) {
        when (state) {
            is State.Success -> {
                hideLoading()
                charactersAdapter.characters = state.data
            }
            is State.Error -> {
                hideLoading(state.error.localizedMessage)
                showSnackBar(state.error.localizedMessage)
            }
            is State.Loading -> showLoading()
        }
    }

    private fun showLoading() {
        binding.progressBar.isVisible = true
        binding.rvResidents.isVisible = false
        binding.errorGroup.isVisible = false
    }

    private fun hideLoading(error: String? = null) {
        binding.progressBar.isVisible = false

        if (error.isNullOrEmpty()) {
            binding.rvResidents.isVisible = true
        } else {
            binding.tvError.text = error
            binding.errorGroup.isVisible = true
        }
    }

    override fun injectDependencies() {
        inject()
    }

    override fun bindView(view: View) {
        _binding = FragmentLocationResidentsBinding.bind(view)
    }
}