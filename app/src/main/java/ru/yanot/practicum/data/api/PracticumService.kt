package ru.yanot.practicum.data.api

import retrofit2.Response
import retrofit2.http.GET
import ru.yanot.practicum.data.model.api.ProfessionResponse

interface PracticumService {

    @GET("professions")
    suspend fun getProfessions(): Response<List<ProfessionResponse>>

}