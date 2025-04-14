package com.trancas.salgado.service

import com.trancas.salgado.screens.client.AddClientData
import com.trancas.salgado.screens.client.EditClientData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT


interface ClientService {
    @POST("cadastros/cliente")
    suspend fun postClient(
        @Body client: AddClientData
    )

    @PUT("cadastros/cliente")
    suspend fun editClient(
        @Body client: EditClientData
    )
}
object SalgadoApi {
    private val BASE_URL = "https://10.0.0.2:8080/";

    val api: ClientService by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(ClientService::class.java)
    }
}


