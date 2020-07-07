package com.urban.androidhomework.ui.base

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.urban.androidhomework.utils.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected val supportActionBar by lazy {
        (requireActivity() as AppCompatActivity).supportActionBar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition =
                TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)
        setUp()
    }

    fun goTo(direction: NavDirections, extras: Navigator.Extras = FragmentNavigatorExtras()) {
        findNavController().navigate(direction, extras)
    }

    abstract fun bindView(view: View)

    open fun injectDependencies() {}

    open fun setUp() {}
}