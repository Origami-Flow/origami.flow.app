package com.trancas.salgado.service

import com.trancas.salgado.screens.extract.classes.Atendimento
import retrofit2.http.GET
import retrofit2.http.Path

interface AtendimentoService {
    @GET("atendimento-realizado/por/cliente/{idCliente}")
    suspend fun listarAtendimentosPorCliente(@Path("idCliente") idCliente: Int): List<Atendimento>

    companion object {
        val api : AtendimentoService by lazy {
            SalgadoApi.retrofit.create(AtendimentoService::class.java)
        }
    }
}