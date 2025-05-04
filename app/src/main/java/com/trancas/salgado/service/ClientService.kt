package com.trancas.salgado.service

import com.trancas.salgado.screens.client.AddClientData
import com.trancas.salgado.screens.client.EditClientData
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ClientService {
    @POST("cadastros/cliente-simples")
    suspend fun postClient(
        @Body client: AddClientData
    )

    @PUT("clientes/{id}")
    suspend fun editClient(
        @Path("id") id: Int,
        @Body client: EditClientData
    )
}


