package com.trancas.salgado.service

import com.trancas.salgado.screens.stock.classes.Product
import com.trancas.salgado.screens.stock.classes.Stock
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Query

interface StockService {
    @GET("estoques/{id}")
    suspend fun getStockById(@Path("id") id: Int): Response<Stock>

    @PUT("estoques/{id}")
    suspend fun updateStock(@Path("id") id: Int, @Query("quantidade") quantidade: Int, @Query("quantidadeFixa") quantidadeFixa: Boolean): Response<Stock>

    @DELETE("estoques/{id}")
    suspend fun deleteStock(@Path("id") id: Int): Response<Unit>

    @GET("estoques")
    suspend fun getAllStocks(): Response<List<Stock>>

    @Multipart
    @PUT("produtos/{id}")
    suspend fun updateProduct(
        @Path("id") id: Int,
        @Part("nome") nome: RequestBody,
        @Part("marca") marca: RequestBody,
        @Part("valorCompra") valorCompra: RequestBody,
        @Part("valorVenda") valorVenda: RequestBody,
        @Part("quantidadeEmbalagem") quantidadeEmbalagem: RequestBody,
        @Part("unidadeMedida") unidadeMedida: RequestBody,
        @Part("tipo") tipo: RequestBody,
        @Part imagem: MultipartBody.Part?,
        @Part("quantidade") quantidade: RequestBody,
        @Part("salaoId") salaoId: RequestBody
    ): Response<Product>


    @DELETE("produtos/{id}")
    suspend fun deleteProduct(@Path("id") id: Int): Response<Unit>

    @GET("produtos")
    suspend fun getAllProducts(): Response<List<Product>>

    @Multipart
    @POST("produtos")
    suspend fun createProduct(
        @Part("nome") nome: RequestBody,
        @Part("marca") marca: RequestBody,
        @Part("valorCompra") valorCompra: RequestBody,
        @Part("valorVenda") valorVenda: RequestBody,
        @Part("quantidadeEmbalagem") quantidadeEmbalagem: RequestBody,
        @Part("unidadeMedida") unidadeMedida: RequestBody,
        @Part("tipo") tipo: RequestBody,
        @Part("quantidade") quantidade: RequestBody,
        @Part("idSalao") idSalao: RequestBody,
        @Part imagem: MultipartBody.Part?
    ): Response<Product>

    @GET("produtos/filtro-nome")
    suspend fun getProductsByName(@Query("nome") nome: String): Response<List<Product>>

    @GET("produtos/{id}")
    suspend fun getProductById(@Path("id") id: Int): Response<Product>

    companion object {
        val api: StockService by lazy {
            SalgadoApi.retrofit.create(StockService::class.java)
        }
    }
}