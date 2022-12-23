package com.example.projektravelaplikasi.api

data class TravelItem (
    val id: String,
    val title: String,
    val description: String? = null,
    val done_at: String? = null,
    val created_at: String,
    val url : String
)