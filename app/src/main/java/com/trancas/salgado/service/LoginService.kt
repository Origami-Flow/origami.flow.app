package com.trancas.salgado.service

import com.trancas.salgado.screens.login.LoginRequestData
import com.trancas.salgado.screens.login.LoginResponseData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
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
    private val BASE_URL = "https://10.0.0.2:8080/logins/";

    val api: LoginService by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(LoginService::class.java)
    }
}