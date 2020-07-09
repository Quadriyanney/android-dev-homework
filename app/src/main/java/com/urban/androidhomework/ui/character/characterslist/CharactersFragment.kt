package com.urban.androidhomework.ui.character.characterslist

import android.view.View
import androidx.core.view.isVisible
import com.urban.androidhomework.R
import com.urban.androidhomework.databinding.FragmentCharactersBinding
import com.urban.androidhomework.di.component.ui.inject
import com.urban.androidhomework.presentation.models.character.CharacterModel
import com.urban.androidhomework.ui.adapters.CharactersAdapter
import com.urban.androidhomework.ui.base.BaseFragment
import com.urban.androidhomework.ui.dialogs.DateFilterDialog
import com.urban.androidhomework.utils.*
import com.urban.androidhomework.utils.imageloader.ImageLoader
import javax.inject.Inject

class CharactersFragment : BaseFragment(R.layout.fragment_characters), (CharacterModel, View) -> Unit {

    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var _binding: FragmentCharactersBinding
    private val binding get() = _binding

    private val viewModel: CharactersViewModel by factoryViewModels()

    private val charactersAdapter by lazy {
        CharactersAdapter(imageLoader, this)
    }

    override fun setUp() {
        binding.rvCharacters.apply {
            adapter = charactersAdapter

            //// handle back press for shared element transition
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }

        binding.fabFilter.setOnClickListener {
            showDateFilterDialog()
        }

        binding.btnRetry.setOnClickListener {
            viewModel.getCharacters()
        }

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
        binding.contentGroup.isVisible = false
        binding.errorGroup.isVisible = false
    }

    private fun hideLoading(error: String? = null) {
        binding.progressBar.isVisible = false

        if (error.isNullOrEmpty()) {
            binding.contentGroup.isVisible = true
        } else {
            binding.tvError.text = error
            binding.errorGroup.isVisible = true
        }
    }

    private fun showDateFilterDialog() {
        DateFilterDialog.showDialog(
            childFragmentManager,
            viewModel.startDate,
            viewModel.endDate
        ) { newStartDate, newEndDate ->
            applyFilter(newStartDate, newEndDate)
        }
    }

    private fun applyFilter(startDate: Long, endDate: Long) {
        viewModel.startDate = startDate
        viewModel.endDate = endDate
        viewModel.applyFilter()
    }

    private fun goToCharacterDetails(character: CharacterModel, view: View) {
        goTo(
            CharactersFragmentDirections.actionCharactersFragmentToCharacterFragment().setCharacter(character),
            view.generateTransitionExtras(character.name)
        )
    }

    override fun bindView(view: View) {
        _binding = FragmentCharactersBinding.bind(view)
    }

    override fun injectDependencies() {
        inject()
    }

    //// Character Item Click Listener
    override fun invoke(character: CharacterModel, view: View) {
        goToCharacterDetails(character, view)
    }
}