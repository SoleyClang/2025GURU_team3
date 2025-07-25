package com.example.habit


data class Routine(
    val id: Int,
    val title: String,
    val description: String,
    var isChecked: Boolean,
    val point: Int = 10
)

