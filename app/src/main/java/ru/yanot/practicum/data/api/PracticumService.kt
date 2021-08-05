package ru.yanot.practicum.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.yanot.practicum.data.model.api.*

interface PracticumService {

    @GET("professions")
    suspend fun getProfessions(): Response<List<ProfessionResponse>>

    @GET("courses")
    suspend fun getProfessionCourses(
        @Query("profession_id") professionId: Long
    ): Response<List<CourseResponse>>

    @GET("topics")
    suspend fun getCourseTopics(
        @Query("course_id") courseId: Long
    ): Response<List<TopicResponse>>

    @GET("lessons")
    suspend fun getLessonById(@Query("lesson_id") lessonId: Long): Response<LessonResponse>

    @GET("users")
    suspend fun getUserByToken(): Response<UserResponse>

}