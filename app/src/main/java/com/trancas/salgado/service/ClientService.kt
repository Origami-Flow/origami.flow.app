package com.trancas.salgado.service

import com.trancas.salgado.screens.client.ClientRequestData
import com.trancas.salgado.screens.client.ClientResponseData
import okhttp3.RequestBody
import retrofit2.Response

import retrofit2.http.Body
import com.trancas.salgado.screens.event.ClientData
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path


interface ClientService {
    @POST("cadastros/cliente-simples")
    suspend fun postClient(
        @Body client: ClientRequestData
    )

    @Multipart
    @PUT("clientes/{id}")
    suspend fun editClient(
        @Path("id") id: Int,
        @Part("nome") nome: RequestBody,
        @Part("email") email: RequestBody,
        @Part("telefone") telefone: RequestBody,
        @Part("tipoCabelo") tipoCabelo: RequestBody?,
        @Part("corCabelo") corCabelo: RequestBody?,
        @Part("ocupacao") ocupacao: RequestBody?,
        @Part("dataNascimento") dataNascimento: RequestBody,
        @Part("primeiraTranca") primeiraTranca: RequestBody,
        @Part("progressiva") progressiva: RequestBody
    ): Response<ClientResponseData>

    @GET("clientes")
    suspend fun getClients(): List<ClientData>

    @GET("clientes/{id}")
    suspend fun getClient(
        @Path("id") id: Int
    ): ClientResponseData

    companion object {
        val api: ClientService by lazy {
            SalgadoApi.retrofit.create(ClientService::class.java)
        }
    }
}