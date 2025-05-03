package com.trancas.salgado.screens.schedule

import com.trancas.salgado.screens.schedule.classes.Event
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ScheduleApiService {
    @GET("eventos/{id}")
    suspend fun getEventoById(@Path("id") id: Int): Response<Event>

    @PUT("eventos/{id}")
    suspend fun updateEvento(@Path("id") id: Int, @Body evento: Event): Response<Event>

    @DELETE("eventos/{id}")
    suspend fun deleteEvento(@Path("id") id: Int): Response<Unit>

    @PUT("eventos/finalizar/{id}")
    suspend fun finalizarEvento(@Path("id") id: Int): Response<Event>

    @GET("eventos")
    suspend fun getAllEventos(): Response<List<Event>>

    @POST("eventos")
    suspend fun createEvento(@Body evento: Event): Response<Event>

    @GET("eventos/por/cliente/{id}")
    suspend fun getEventosByClienteId(@Path("id") clienteId: Int): Response<List<Event>>

    @GET("eventos/data")
    suspend fun getEventosByData(@Query("data") data: String): Response<List<Event>>

    @GET("eventos/buscar-intervalo-tempo")
    suspend fun getEventosByIntervaloTempo(
        @Query("dataInicio") dataInicio: String,
        @Query("dataFim") dataFim: String
    ): Response<List<Event>>
}

object ScheduleApi {

    private const val BASE_URL = "http://192.168.11.173:8080/api/"

    val api: ScheduleApiService by lazy {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val clienteHttp = OkHttpClient.Builder().addInterceptor(interceptor).build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ScheduleApiService::class.java)
    }
}