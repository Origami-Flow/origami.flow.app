package com.trancas.salgado.service

import com.trancas.salgado.screens.extract.classes.ExpenseData
import retrofit2.http.GET

interface DespesaService {
    @GET("despesas")
    suspend fun listarDespesas(): List<ExpenseData>

    companion object {
        val api : DespesaService by lazy {
            SalgadoApi.retrofit.create(DespesaService::class.java)
        }
    }
}