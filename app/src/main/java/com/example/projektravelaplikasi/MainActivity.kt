package com.example.projektravelaplikasi

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.projektravelaplikasi.api.RetrofitHelper
import com.example.projektravelaplikasi.api.UserApi
import com.example.projektravelaplikasi.api.data.Users
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var btnSignIn: Button
    lateinit var btnMoveToSignUp: Button
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText

    val apiKey =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImxzbnNtYXp0ZXN0ZW96ZG15dG5uIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzA1NzIxMTEsImV4cCI6MTk4NjE0ODExMX0.pAu3jZ4F56E5aDtJX72yxnUtqYKwegydyMgjafiz7zU"
    val token = "Bearer $apiKey"

    val travelApi = RetrofitHelper.getInstance().create(UserApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        btnSignIn = findViewById(R.id.buttonsignin)
        btnMoveToSignUp = findViewById(R.id.buttonsignup)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)

        btnSignIn.setOnClickListener {
            signIn(etEmail.text.toString(), etPassword.text.toString())
        }
        val intent = Intent(this, PlaceActivity::class.java)
        startActivity(intent)

        btnMoveToSignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signIn(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {

            val data = Users(email = email, password = password)
            val response = travelApi.signIn(token = token, apiKey = apiKey, data = data)

            val bodyResponse = if (response.code() == 200) {
                response.body()?.string()
            } else {
                response.errorBody()?.string().toString()
            }

            var failed = false
            val jsonResponse = JSONObject(bodyResponse)
            if (jsonResponse.keys().asSequence().toList().contains("error")) {
                failed = true
            }

            var msg = ""
            if (!failed) {
                var email = jsonResponse.getJSONObject("user").get("email").toString()
                msg = "Successfully login! Welcome back: $email"

                val sharedPreference = getSharedPreferences(
                    "app_preference", Context.MODE_PRIVATE
                )

                var editor = sharedPreference.edit()
                editor.putString("email", email)
                editor.commit()

            } else {
                msg += jsonResponse.get("error_description").toString()
            }

            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(
                    applicationContext,
                    msg,
                    Toast.LENGTH_SHORT
                ).show()

                if (!failed) {
                    goToHome();

                }
            }
        }
    }

    private fun goToHome() {
        val intent = Intent(this, PlaceActivity::class.java)
        startActivity(intent)
        finish()
    }
}