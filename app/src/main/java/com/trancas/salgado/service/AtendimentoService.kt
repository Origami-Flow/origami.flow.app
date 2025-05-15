package com.trancas.salgado.service

import com.trancas.salgado.screens.extract.classes.TreatmentData
import retrofit2.http.GET

interface AtendimentoService {
    @GET("atendimento-realizado")
    suspend fun listarAtendimentos(): List<TreatmentData>

    companion object {
        val api : AtendimentoService by lazy {
            SalgadoApi.retrofit.create(AtendimentoService::class.java)
        }
    }
}