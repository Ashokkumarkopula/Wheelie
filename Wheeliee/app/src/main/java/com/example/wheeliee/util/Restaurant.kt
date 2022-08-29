package com.example.wheeliee.util

data class Restaurant(
    val name: String = "",
    val address: String = "",
    val photo: String = "",
    val facilities: List<String> = listOf("None")
)