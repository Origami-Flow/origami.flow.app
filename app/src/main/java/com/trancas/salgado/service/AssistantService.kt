package com.trancas.salgado.service

import com.trancas.salgado.screens.event.AssistantData
import retrofit2.http.GET

interface AssistantService {
    @GET("auxiliares")
    suspend fun getAssistants(): List<AssistantData>

    companion object {
        val api: AssistantService by lazy {
            SalgadoApi.retrofit.create(AssistantService::class.java)
        }
    }
}