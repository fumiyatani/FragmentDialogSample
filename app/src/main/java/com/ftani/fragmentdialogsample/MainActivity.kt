package com.ftani.fragmentdialogsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.ftani.fragmentdialogsample.databinding.ActivityMainBinding
import com.ftani.fragmentdialogsample.dialog.Event
import com.ftani.fragmentdialogsample.dialog.SampleDialogFragment
import com.ftani.fragmentdialogsample.dialog.SampleDialogViewModel

class MainActivity : AppCompatActivity() {
    private val sampleDialogViewModel: SampleDialogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.showDialog.setOnClickListener {
            SampleDialogFragment.Builder("タイトル")
                .setMessage("メッセージ")
                .setPositiveButton("OK")
                .setNegativeButton("キャンセル")
                .build()
                .show(supportFragmentManager, SAMPLE_DIALOG_TAG)
        }

        sampleDialogViewModel.state.observe(this) { state ->
            state.events.forEach { event ->
                when(event) {
                    Event.POSITIVE -> showToast("PositiveAction!")
                    Event.NEGATIVE -> showToast("NegativeAction!")
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val SAMPLE_DIALOG_TAG = "sample_dialog"
    }
}