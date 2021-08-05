package ru.yanot.practicum.data.model.api

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @SerializedName("name")
    val name: String,

    @SerializedName("surname")
    val surname: String,

    @SerializedName("imageUrl")
    val imageUrl: String,
)