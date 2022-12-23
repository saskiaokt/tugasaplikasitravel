package com.example.projektravelaplikasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class DuniaFantasi : AppCompatActivity() {
    lateinit var txtResult : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dunia_fantasi)

        txtResult = findViewById(R.id.label_header)

        val result = intent.getStringExtra("nilai1")
        txtResult.text = result
            }
        }

