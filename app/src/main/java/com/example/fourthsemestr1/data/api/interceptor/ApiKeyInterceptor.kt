package com.example.fourthsemestr1.data.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

private const val API_KEY = "b27d37e0816963e5e76c1ac07836193f"
private const val QUERY_API_KEY = "appid"

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl = request.url.newBuilder()
            .addQueryParameter(QUERY_API_KEY, API_KEY)
            .build()

        return chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }
}
