package ru.yanot.practicum.presentation.professions

data class Profession(
    val id: Long,
    val title: String,
    val durationMonth: Int,
    val durationHours: Int,
    val imageUrl: String,
)