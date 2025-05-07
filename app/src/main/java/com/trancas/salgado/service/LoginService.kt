package com.trancas.salgado.service

import com.trancas.salgado.screens.login.LoginRequestData
import com.trancas.salgado.screens.login.LoginResponseData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query


interface LoginService {
    @POST("logins")
    suspend fun postLogin(
        @Body loginRequestData: LoginRequestData
    ): LoginResponseData
}

object SalgadoApi {
    private val BASE_URL = "http://10.0.2.2:8080/api/";

    fun getApi(token: String): LoginService {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(newRequest)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(LoginService::class.java)
    }
}

