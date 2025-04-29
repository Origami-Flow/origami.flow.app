package com.trancas.salgado.screens.stock.classes

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import okhttp3.RequestBody

data class Product(
    val id: Int? = null,
    val nome: String,
    val marca: String,
    val valorCompra: Double,
    val valorVenda: Double,
    val quantidadeEmbalagem: Int,
    val unidadeMedida: String,
    val tipo: String,
    val imagem: MultipartBody.Part?,
    val imagemUrl: String? = null,
    val quantidade: Int,
    @SerializedName("idSalao") val salaoId: Int
)
