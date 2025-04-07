package com.trancas.salgado.screens.stock

import com.trancas.salgado.screens.stock.classes.Stock
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface StockApiService {
    @GET("estoques/{id}")
    suspend fun getStockById(@Path("id") id: Int): Response<Stock>

    @PUT("estoques/{id}")
    suspend fun updateStock(@Path("id") id: Int, @Body estoque: Stock): Response<Stock>

    @DELETE("estoques/{id}")
    suspend fun deleteStock(@Path("id") id: Int): Response<Unit>

    @GET("estoques")
    suspend fun getAllStocks(): Response<List<Stock>>
}

object StockApi {

    private const val BASE_URL = "http://192.168.1.17:8080/"

    val api: StockApiService by lazy {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val clienteHttp = OkHttpClient.Builder().addInterceptor(interceptor).build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockApiService::class.java)
    }
}