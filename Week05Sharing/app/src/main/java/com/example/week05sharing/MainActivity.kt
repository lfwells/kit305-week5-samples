package com.example.week05sharing

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week05sharing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var ui : ActivityMainBinding

    @SuppressLint("SetTextI18n") //get rid of a warning about line 35
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        //share out
        ui.btnSend.setOnClickListener {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, ui.txtToShare.text.toString())
                type = "text/plain"
            }
            startActivity(Intent.createChooser(sendIntent, "Share via..."))
        }

        //share in
        if (intent?.action == Intent.ACTION_SEND && intent?.type != null)
        {
            if (intent?.type == "text/plain")
            {
                val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT) ?: ""
                //do something with sharedText
                ui.lblSharedText.text = "Received: $sharedText"
            }
        }
    }
}