package ru.yanot.practicum.data.model.api

import com.google.gson.annotations.SerializedName

data class CourseResponse(

    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("available")
    val available: Boolean,

    @SerializedName("duration")
    val duration: Int,

    @SerializedName("profession")
    val professionResponse: ProfessionResponse,

    )