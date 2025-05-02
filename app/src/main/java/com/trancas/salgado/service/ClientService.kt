package com.trancas.salgado.service

import com.trancas.salgado.screens.event.ClientData
import retrofit2.http.GET

interface ClientService {
    @GET("clientes")
    suspend fun getClients(): List<ClientData>

    companion object {
        val api: ClientService by lazy {
            SalgadoApi.retrofit.create(ClientService::class.java)
        }
    }
}