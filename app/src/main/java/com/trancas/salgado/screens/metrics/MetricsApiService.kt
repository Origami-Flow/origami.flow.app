package com.trancas.salgado.screens.metrics

import com.trancas.salgado.screens.metrics.classes.Metrics
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import retrofit2.http.GET

interface MetricsApiService {
    @GET("metricas")
    suspend fun buscarMetricas(
        @Query("mes") mes: Int,
        @Query("ano") ano: Int
    ): Response<Metrics>
}

object MetricsApi {
    private const val BASE_URL = "http://192.168.1.15:8080/api/"

    val api: MetricsApiService by lazy {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val clienteHttp = OkHttpClient.Builder().addInterceptor(interceptor).build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MetricsApiService::class.java)
    }
}