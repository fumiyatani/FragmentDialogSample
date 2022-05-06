package com.ftani.fragmentdialogsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.ftani.fragmentdialogsample.dialog.Event
import com.ftani.fragmentdialogsample.dialog.SampleDialogFragment
import com.ftani.fragmentdialogsample.dialog.SampleDialogViewModel

class MainActivity : AppCompatActivity() {
    private val dialogViewModel: SampleDialogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.show_dialog).setOnClickListener {
            SampleDialogFragment.Builder("タイトル")
                .setMessage("メッセージ")
                .setPositiveButton("OK")
                .setNegativeButton("キャンセル")
                .build()
                .show(supportFragmentManager, SAMPLE_DIALOG_TAG)
        }

        dialogViewModel.state.observe(this) { state ->
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