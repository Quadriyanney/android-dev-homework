package com.urban.androidhomework.utils

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.urban.androidhomework.ui.base.BaseFragment
import com.urban.androidhomework.ui.dialogs.loading.LoadingDialog
import com.urban.androidhomework.utils.recyclerview.EndlessRecyclerViewScrollListener
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


//// CONTEXT EXTENSIONS

fun Context.showToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.getColorInt(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

fun Context.dpToPx(dp: Int): Int {
    val displayMetrics = resources.displayMetrics
    return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
}

fun View.generateColorDrawable(@ColorRes resId: Int): Drawable {
    return ColorDrawable(ContextCompat.getColor(this.context, resId))
}


//// FRAGMENT EXTENSIONS

fun Fragment.showToast(message: String?) {
    requireContext().showToast(message)
}

fun Fragment.getColorInt(@ColorRes colorRes: Int): Int {
    return requireContext().getColorInt(colorRes)
}

fun Fragment.showLoadingDialog() {
    LoadingDialog.showLoading(childFragmentManager)
}

fun Fragment.hideLoadingDialog() {
    LoadingDialog.hideLoading(childFragmentManager)
}

fun Fragment.showDatePickerDialog(millis: Long, action: (Long) -> Unit) {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = millis

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(
        requireContext(),
        DatePickerDialog.OnDateSetListener { _, setYear, setMonth, setDay ->
            calendar.set(Calendar.YEAR, setYear)
            calendar.set(Calendar.MONTH, setMonth)
            calendar.set(Calendar.DAY_OF_MONTH, setDay)

            action(calendar.timeInMillis)
        }, year, month, day
    ).show()
}

fun Fragment.showSnackBar(text: String?, timeLength: Int = Snackbar.LENGTH_SHORT) {
    if (text.isNullOrEmpty()) {
        return
    }

    val snack =
        Snackbar.make(requireActivity().findViewById(android.R.id.content), text, timeLength)
    val view = snack.view
    val params = view.layoutParams as FrameLayout.LayoutParams
    params.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
    view.layoutParams = params
    snack.show()
}

inline fun <reified T : ViewModel> BaseFragment.factoryViewModels(): Lazy<T> {
    return viewModels { viewModelFactory }
}


//// RECYCLERVIEW EXTENSIONS

fun RecyclerView.setEndlessScrollListener(action: () -> Unit) {
    addOnScrollListener(
        object : EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
            override fun loadMore() {
                action()
            }
        }
    )
}


//// LIVEDATA EXTENSIONS

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) {
    liveData.observe(this, Observer(body))
}


//// STRING EXTENSIONS

fun String.isAvailable(): Boolean {
    return isNotEmpty() && !equals("N/A")
}

fun String.formatToDate(pattern: String): Date {
    return try {
        SimpleDateFormat(pattern, Locale.getDefault()).parse(this)!!
    } catch (_: Exception) {
        Date()
    }
}

fun String.getIdFromUrl(): Int? {
    return try {
        toUri().lastPathSegment?.toInt()
    } catch (_: Exception) {
        null
    }
}


//// LONG EXTENSIONS
fun Long.toDateString(pattern: String): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(Date(this))
}


//// VIEW EXTENSIONS
fun View.generateTransitionExtras(transitionName: String): Navigator.Extras {
    var extras = FragmentNavigatorExtras()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.transitionName = transitionName
        extras = FragmentNavigatorExtras(this to transitionName)
    }

    return extras
}