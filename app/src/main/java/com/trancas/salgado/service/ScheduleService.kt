package com.trancas.salgado.service

import com.trancas.salgado.screens.event.EventData
import com.trancas.salgado.screens.schedule.classes.Event
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ScheduleService {
    @GET("eventos/{id}")
    suspend fun getEventoById(@Path("id") id: Int): Response<Event>

    @PUT("eventos/{id}")
    suspend fun updateEvento(@Path("id") id: Int, @Body evento: EventData): Response<Event>

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

    companion object {
        val api: ScheduleService by lazy {
            SalgadoApi.retrofit.create(ScheduleService::class.java)
        }

    }
}
