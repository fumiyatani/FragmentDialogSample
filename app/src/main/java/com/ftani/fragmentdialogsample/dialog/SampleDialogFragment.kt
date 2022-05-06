package com.ftani.fragmentdialogsample.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.ftani.fragmentdialogsample.databinding.DialogSampleBinding

class SampleDialogFragment : DialogFragment() {

    private val viewModel: SampleDialogViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        val layoutInflater = LayoutInflater.from(requireContext())
        val binding = DialogSampleBinding.inflate(layoutInflater)

        return Dialog(requireContext()).apply {
            setContentView(binding.root)
            binding.title.text = arguments?.getString(SampleDialogKey.TITLE.name, "")
            binding.message.text = arguments?.getString(SampleDialogKey.MESSAGE.name, "")
            binding.ok.text = arguments?.getString(SampleDialogKey.POSITIVE.name, "")
            binding.ok.setOnClickListener {
                viewModel.onClickPositive()
                viewModel.consumeEvent(Event.POSITIVE)
                dismiss()
            }
            binding.cancel.text = arguments?.getString(SampleDialogKey.NEGATIVE.name, "")
            binding.cancel.setOnClickListener {
                viewModel.onClickNegative()
                viewModel.consumeEvent(Event.NEGATIVE)
                dismiss()
            }
        }
    }

    class Builder(title: String) {
        private val bundle = Bundle()

        init {
            if (title.isEmpty()) throw IllegalArgumentException("タイトルは必須項目です")

            bundle.putString(SampleDialogKey.TITLE.name, title)
        }

        fun setMessage(message: String): Builder = apply {
            bundle.putString(SampleDialogKey.MESSAGE.name, message)
        }

        fun setPositiveButton(label: String): Builder = apply {
            bundle.putString(SampleDialogKey.POSITIVE.name, label)
        }

        fun setNegativeButton(label: String): Builder = apply {
            bundle.putString(SampleDialogKey.NEGATIVE.name, label)
        }

        fun build(): SampleDialogFragment = SampleDialogFragment().apply {
            arguments = bundle
        }
    }

    private enum class SampleDialogKey {
        TITLE,
        MESSAGE,
        POSITIVE,
        NEGATIVE,
    }
}

