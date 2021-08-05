package ru.yanot.practicum.data.model.api

import com.google.gson.annotations.SerializedName
import ru.yanot.practicum.domain.StepState

data class StepResponse(

    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("lessonId")
    val lessonId: Long,

    @SerializedName("stepState")
    val stepState: StepState,
)