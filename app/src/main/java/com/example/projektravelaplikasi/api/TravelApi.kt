package com.example.projektravelaplikasi.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface TravelApi {
    @GET("/rest/v1/travel?select=*")
    suspend fun get(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String
    ) : Response<List<TravelItem>>
}