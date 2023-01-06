package com.example.projektravelaplikasi

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MuseumMacan : AppCompatActivity() {

    var nilai1:Int = 0
    var nilai2:Int = 80000
    var hasil1:Int = 0
    var hasil2:Int = 0

    lateinit var btnSubmit: Button
    lateinit var etName: EditText
    lateinit var txtName : TextView
    lateinit var buttonplus : Button
    lateinit var buttonminus : Button
    lateinit var txtJumlah : TextView
    lateinit var txtTotal : TextView

    var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum_macan)
        supportActionBar?.hide()

        buttonplus = findViewById(R.id.buttonPlus7)
        buttonminus = findViewById(R.id.buttonMinus7)
        txtJumlah = findViewById(R.id.tvjumlah7)
        txtTotal = findViewById(R.id.tvTotal7)

        btnSubmit = findViewById(R.id.buttonPesanSekarang7)
        etName = findViewById(R.id.etnamapengunjung7)
        etName = findViewById(R.id.etnomortelepon7)
        txtName = findViewById(R.id.tvpaket7)
        txtName = findViewById(R.id.tvtanggalkunjungan7)
        txtName = findViewById(R.id.tvjumlahpengunjung7)
        txtName = findViewById(R.id.tvdetailpengunjung7)
        txtName = findViewById(R.id.tvnamapengunjung7)
        txtName = findViewById(R.id.tvnomortelepon7)


        btnSubmit.setOnClickListener {
            if (etName.text.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Harap isi data terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        buttonplus.setOnClickListener{
            num++
            txtJumlah.text = num.toString()

            hasil1 = ++nilai1 * nilai2
            txtTotal.text = "Total Harga : Rp " + hasil1.toString()

        }

        buttonminus.setOnClickListener {
            num--
            txtJumlah.text = num.toString()

            hasil2 = --nilai1 * nilai2
            txtTotal.text = "Total Harga : Rp " + hasil2.toString()

        }

        val date = findViewById<TextView>(R.id.etdate7)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        date.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    date.text = " " + dayOfMonth + "/" + (month + 1) + "/" + year
                },
                year,
                month,
                day
            )
            datePickerDialog.show()

        }
    }
    }
