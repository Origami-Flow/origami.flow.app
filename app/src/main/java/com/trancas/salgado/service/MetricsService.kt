package com.trancas.salgado.service

import com.trancas.salgado.screens.metrics.classes.Metrics
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MetricsService {
    @GET("metricas")
    suspend fun buscarMetricas(
        @Query("mes") mes: Int,
        @Query("ano") ano: Int
    ): Response<Metrics>

    companion object {
        val api: MetricsService by lazy {
            SalgadoApi.retrofit.create(MetricsService::class.java)
        }

    }
}