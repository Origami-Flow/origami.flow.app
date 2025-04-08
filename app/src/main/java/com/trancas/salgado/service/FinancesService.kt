package com.trancas.salgado.service

import com.trancas.salgado.screens.Finances.FinancesData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FinancesService {

    @GET("lucros")
    suspend fun getFinances(
        @Query("mes") mes : Int,
        @Query("ano") ano : Int
    ): FinancesData
}

object SalgadoApi {

    private val BASE_URL = "http://10.0.2.2:8080/metricas/"

    val api: FinancesService by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        .create(FinancesService::class.java)
    }
}