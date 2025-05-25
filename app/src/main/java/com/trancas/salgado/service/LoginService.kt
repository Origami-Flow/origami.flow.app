package com.trancas.salgado.service

import com.trancas.salgado.screens.login.LoginRequestData
import com.trancas.salgado.screens.login.LoginResponseData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginService {
    @POST("logins")
    suspend fun postLogin(
        @Body loginRequestData: LoginRequestData
    ): LoginResponseData
}

