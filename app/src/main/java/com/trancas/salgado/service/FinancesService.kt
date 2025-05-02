package com.trancas.salgado.service



import com.trancas.salgado.screens.finances.FinancesData
import retrofit2.http.GET
import retrofit2.http.Query

interface FinancesService {

    @GET("metricas/lucros")
    suspend fun getFinances(
        @Query("mes") mes : Int,
        @Query("ano") ano : Int
    ): FinancesData

    companion object {
        val api : FinancesService by lazy {
            SalgadoApi.retrofit.create(FinancesService::class.java)
        }
    }
}