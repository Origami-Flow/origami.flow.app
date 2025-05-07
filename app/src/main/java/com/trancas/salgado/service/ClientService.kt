package com.trancas.salgado.service

import com.trancas.salgado.screens.clients.classes.ClientDataClass
import retrofit2.http.GET

interface ClientService {
    @GET("clientes")
    suspend fun listarCliente(): List<ClientDataClass>

    companion object {
        val api: ClientService by lazy {
            SalgadoApi.retrofit.create(ClientService::class.java)
        }
    }
}