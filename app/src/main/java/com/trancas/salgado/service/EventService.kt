package com.trancas.salgado.service

import com.trancas.salgado.screens.event.EventData
import retrofit2.http.Body
import retrofit2.http.POST

interface EventService  {
    @POST("eventos")
    suspend fun post(@Body eventData: EventData): EventData

    companion object {
        val api: EventService by lazy {
            SalgadoApi.retrofit.create(EventService::class.java)
        }
    }
}