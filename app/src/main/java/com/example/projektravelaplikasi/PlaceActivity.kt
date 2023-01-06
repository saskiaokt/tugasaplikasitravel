package com.example.projektravelaplikasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.projektravelaplikasi.api.RetrofitHelper
import com.example.projektravelaplikasi.api.TravelApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaceActivity : AppCompatActivity() {
    lateinit var labelHeader: TextView
    lateinit var listTodo: ListView

    val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImxzbnNtYXp0ZXN0ZW96ZG15dG5uIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzA1NzIxMTEsImV4cCI6MTk4NjE0ODExMX0.pAu3jZ4F56E5aDtJX72yxnUtqYKwegydyMgjafiz7zU"
    val token = "Bearer $apiKey"

    var Items = ArrayList<Model>()
    val travelApi = RetrofitHelper.getInstance().create(TravelApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)
        supportActionBar?.hide()

        labelHeader = findViewById(R.id.label_header)
        listTodo = findViewById(R.id.list_todo)

        CoroutineScope(Dispatchers.IO).launch {
            val response = travelApi.get(token = token, apiKey = apiKey)

            response.body()?.forEach {
                Items.add(
                    Model(
                        Id = it.id,
                        Title = it.title,
                        Description = it.description,
                    )
                )
            }
            CoroutineScope(Dispatchers.Main).launch {
                setList(Items)
            }
        }

        listTodo.setOnItemClickListener { adapterView, view, position, id ->
            val item = adapterView.getItemAtPosition(position) as Model
            val title = item.Title

            when (position) {
                0 -> {
                    val intent = Intent(this, TicketActivity::class.java)
                    startActivity(intent)
                }

                1 -> {
                    val intent = Intent(this, Kidzania::class.java)
                    startActivity(intent)
                }

                2 -> {
                    val intent = Intent(this, JungleLand::class.java)
                    startActivity(intent)
                }

                3 -> {
                    val intent = Intent(this, JakartaAquarium::class.java)
                    startActivity(intent)
                }

                4 -> {
                    val intent = Intent(this, WaterKingdom::class.java)
                    startActivity(intent)
                }

                5 -> {
                    val intent = Intent(this, TransSnowWorld::class.java)
                    startActivity(intent)
                }

                6 -> {
                    val intent = Intent(this, MuseumMacan::class.java)
                    startActivity(intent)
                }

                7 -> {
                    val intent = Intent(this, BatuSecretZoo::class.java)
                    startActivity(intent)
                }

            }

            Toast.makeText(
                applicationContext,
                "Kamu memilih $title",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun setList(Items: ArrayList<Model>) {
        val adapter = TravelAdapter(this, R.layout.travel_item, Items)
        listTodo.adapter = adapter

    }
}
