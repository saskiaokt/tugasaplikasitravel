package com.example.projektravelaplikasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var btnSubmit : Button
    lateinit var etName : EditText
    lateinit var txtName : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSubmit = findViewById(R.id.buttonlogin)
        etName = findViewById(R.id.editTextTextPersonName)
        etName = findViewById(R.id.editTextTextPassword)
        txtName = findViewById(R.id.textlogin)
        txtName = findViewById(R.id.textemail)
        txtName = findViewById(R.id.textpassword)

        btnSubmit.setOnClickListener {
            if (etName.text.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Harap isi nama terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val intent = Intent(this, PlaceActivity::class.java)
            intent.putExtra("result", etName.text.toString())
            startActivity(intent)
        }
    }
}