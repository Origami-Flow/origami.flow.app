package com.trancas.salgado.service

import com.trancas.salgado.screens.event.ServicesData
import retrofit2.http.GET

interface ServicesService {

    @GET("servicos")
    suspend fun getServices(): List<ServicesData>

    companion object {
        val api: ServicesService by lazy {
            SalgadoApi.retrofit.create(ServicesService::class.java)
        }
    }
}