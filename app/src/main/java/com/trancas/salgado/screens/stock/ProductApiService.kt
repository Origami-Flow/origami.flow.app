package com.trancas.salgado.screens.stock

import com.trancas.salgado.screens.stock.classes.Product
import com.trancas.salgado.screens.stock.classes.Stock
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

interface ProductApiService {
    @GET("produtos/{id}")
    suspend fun getProductById(@Path("id") id: Int): Response<Product>

    @PUT("produtos/{id}")
    suspend fun updateProduct(@Path("id") id: Int, @Body estoque: Product): Response<Product>

    @DELETE("produtos/{id}")
    suspend fun deleteProduct(@Path("id") id: Int): Response<Unit>

    @GET("produtos")
    suspend fun getAllProducts(): Response<List<Product>>

    @POST("produtos")
    suspend fun createProduct(@Body product: Product): Response<Product>

    @GET("produtos/filtro-nome")
    suspend fun getProductsByName(@Query("nome") nome: String): Response<List<Product>>
}

object ProductApi {

    private const val BASE_URL = "http://192.168.1.17:8080/"

    val api: ProductApiService by lazy {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val clienteHttp = OkHttpClient.Builder().addInterceptor(interceptor).build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApiService::class.java)
    }
}