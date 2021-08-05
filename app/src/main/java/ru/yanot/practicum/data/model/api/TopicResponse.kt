package ru.yanot.practicum.data.model.api

import com.google.gson.annotations.SerializedName

data class TopicResponse(

    @SerializedName("id")
    val id: Long,

    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("course")
    val course: CourseResponse,

    @SerializedName("steps")
    val steps: List<StepResponse>
)
