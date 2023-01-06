package com.example.projektravelaplikasi

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
import retrofit2.create

class RegisterActivity : AppCompatActivity() {

    lateinit var btnSignUp : Button
    lateinit var etEmail : EditText
    lateinit var etPassword : EditText

    val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImxzbnNtYXp0ZXN0ZW96ZG15dG5uIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzA1NzIxMTEsImV4cCI6MTk4NjE0ODExMX0.pAu3jZ4F56E5aDtJX72yxnUtqYKwegydyMgjafiz7zU"
    val token = "Bearer $apiKey"

    val travelApi = RetrofitHelper.getInstance().create(UserApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        btnSignUp = findViewById(R.id.btn_sign_up)
        etEmail = findViewById(R.id.et_regis_email)
        etPassword = findViewById(R.id.et_regis_password)

        btnSignUp.setOnClickListener {
            signUp(etEmail.text.toString(), etPassword.text.toString())
    }
}
    private fun signUp(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {

            var data = Users(email = email, password = password)
            var response = travelApi.signUp(token = token, apiKey = apiKey, data = data)

            val bodyResponse = if(response.code() == 200) {
                response.body()?.string()
            } else {
                response.errorBody()?.string().toString()
            }

            var failed = false
            val jsonResponse = JSONObject(bodyResponse)
            if(jsonResponse.keys().asSequence().toList().contains("error")) {
                failed = true
            }

            var msg = ""
            if (!failed) {
                msg = "Successfully sign up!"
            } else {
                var errorMessage = jsonResponse.get("error_description")
                msg += errorMessage
            }

            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(
                    applicationContext,
                    msg,
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
        }
    }
}