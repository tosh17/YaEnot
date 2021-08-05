package ru.yanot.practicum.domain

data class Step(
   val id: Long,
   val title: String,
   val state: StepState
)
