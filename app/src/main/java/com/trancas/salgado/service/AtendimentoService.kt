package com.trancas.salgado.service

import com.trancas.salgado.screens.extract.classes.TreatmentData
import retrofit2.http.GET
import retrofit2.http.Path

interface AtendimentoService {
    @GET("atendimento-realizado")
    suspend fun listarAtendimentos(): List<TreatmentData>

    companion object {
        val api : AtendimentoService by lazy {
            SalgadoApi.retrofit.create(AtendimentoService::class.java)
        }
    }
}