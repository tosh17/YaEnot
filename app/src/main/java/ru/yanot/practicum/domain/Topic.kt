package ru.yanot.practicum.domain

data class Topic(
    val id: Long,
    val description: String,
    val available: Boolean,
    val steps: List<Step>
)
