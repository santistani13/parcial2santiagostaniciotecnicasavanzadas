package com.example.createprofileapp

data class Respuesta(
    val results: List<Persona>
)
data class Persona(
    val gender: String,
    val name: Name,
    val email: String,
    val dob: Dob,
    val phone: String,
    val picture: Picture,
)
data class Name (
    val first: String,
    val last: String
)
data class Dob (
    val age: Long
)

data class Picture (
    val thumbnail: String
)