package com.trancas.salgado.service

import com.trancas.salgado.screens.client.AddClientData
import com.trancas.salgado.screens.client.ClientRequestData
import com.trancas.salgado.screens.client.ClientResponseData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ClientService {
    @POST("cadastros/cliente-simples")
    suspend fun postClient(
        @Body client: ClientRequestData
    )

    @PUT("clientes/{id}")
    suspend fun editClient(
        @Path("id") id: Int,
        @Body client: ClientRequestData
    )

    @GET("clientes/{id}")
    suspend fun getClient(
        @Path("id") id: Int
    ): ClientResponseData
}


