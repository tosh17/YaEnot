package ru.yanot.practicum.data.api

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    companion object {
        const val TOKEN = "saofhij1n231jh2b312uh"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .header("token", TOKEN)
            .build()

        return chain.proceed(newRequest)
    }
}