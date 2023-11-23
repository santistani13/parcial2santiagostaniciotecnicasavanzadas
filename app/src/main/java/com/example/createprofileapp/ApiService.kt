package com.example.createprofileapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getPersona(@Url url: String): Response<Respuesta>
}