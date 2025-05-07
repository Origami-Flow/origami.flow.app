package com.trancas.salgado.service

import com.trancas.salgado.screens.extract.classes.Despesa
import retrofit2.http.GET

interface DespesaService {
    @GET("despesas")
    suspend fun listarDespesas(): List<Despesa>

    companion object {
        val api : DespesaService by lazy {
            SalgadoApi.retrofit.create(DespesaService::class.java)
        }
    }
}