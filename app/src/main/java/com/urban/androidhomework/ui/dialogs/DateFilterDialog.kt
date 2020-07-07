package com.urban.androidhomework.ui.dialogs

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.urban.androidhomework.R
import com.urban.androidhomework.data.Constants
import com.urban.androidhomework.databinding.DialogDateFilterBinding
import com.urban.androidhomework.ui.dialogs.loading.LoadingDialog
import com.urban.androidhomework.utils.showDatePickerDialog
import com.urban.androidhomework.utils.toDateString

class DateFilterDialog(
    private val applyFilter: ((Long, Long) -> Unit)
) : DialogFragment() {

    private lateinit var _binding: DialogDateFilterBinding
    private val binding get() = _binding

    private var startDate = System.currentTimeMillis()
    private var endDate = System.currentTimeMillis()

    private val dialogArgs get() = requireArguments()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogDateFilterBinding.inflate(layoutInflater)

        setUp()

        return AlertDialog.Builder(requireContext()).apply {
            setView(binding.root)
            setPositiveButton(R.string.apply) { _: DialogInterface, _: Int ->
                applyFilter(startDate, endDate)
            }
        }.create()
    }

    private fun setUp() {
        startDate = dialogArgs[EXTRA_START_DATE] as Long
        endDate = dialogArgs[EXTRA_END_DATE] as Long

        displayStartDate()
        displayEndDate()

        binding.etStartDate.setOnClickListener {
            showDatePickerDialog(startDate) {
                startDate = it
                displayStartDate()
            }
        }

        binding.etEndDate.setOnClickListener {
            showDatePickerDialog(endDate) {
                endDate = it
                displayEndDate()
            }
        }
    }

    private fun displayStartDate() {
        binding.etStartDate.setText(startDate.toDateString(Constants.DATE_DISPLAY_PATTERN))
    }

    private fun displayEndDate() {
        binding.etEndDate.setText(endDate.toDateString(Constants.DATE_DISPLAY_PATTERN))
    }

    companion object {
        private val TAG = LoadingDialog::class.java.simpleName

        private const val EXTRA_START_DATE = "EXTRA_START_DATE"
        private const val EXTRA_END_DATE = "EXTRA_END_DATE"

        fun showDialog(
            fragmentManager: FragmentManager,
            startDate: Long,
            endDate: Long,
            applyFilter: ((Long, Long) -> Unit)
        ) {
            DateFilterDialog(applyFilter).apply {
                arguments = Bundle().apply {
                    putLong(EXTRA_START_DATE, startDate)
                    putLong(EXTRA_END_DATE, endDate)
                }
            }.show(fragmentManager, TAG)
        }
    }
}