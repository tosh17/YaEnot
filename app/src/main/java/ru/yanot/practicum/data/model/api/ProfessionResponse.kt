package ru.yanot.practicum.data.model.api

import com.google.gson.annotations.SerializedName

data class ProfessionResponse(

    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("durationMonths")
    val durationMonth: Int,

    @SerializedName("durationHours")
    val durationHours: Int,

    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("costFull")
    val costFull: Int,

    @SerializedName("costMonths")
    val costMonths: Int,

    @SerializedName("available")
    val available: Boolean,
)
