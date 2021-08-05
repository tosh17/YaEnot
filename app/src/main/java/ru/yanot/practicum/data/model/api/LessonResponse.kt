package ru.yanot.practicum.data.model.api

import com.google.gson.annotations.SerializedName

data class LessonResponse(

    @SerializedName("id")
    val id: Long,
)
