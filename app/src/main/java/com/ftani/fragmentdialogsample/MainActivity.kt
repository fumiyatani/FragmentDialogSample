package com.ftani.fragmentdialogsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ftani.fragmentdialogsample.dialog.SampleDialogFragment

class MainActivity : AppCompatActivity() {
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
    }

    companion object {
        private const val SAMPLE_DIALOG_TAG = "sample_dialog"
    }
}