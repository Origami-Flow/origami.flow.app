package com.trancas.salgado.screens.stock.classes

import com.google.gson.annotations.SerializedName

data class Product(
    val id: Int? = null,
    val nome: String,
    val marca: String,
    val valorCompra: Double,
    val valorVenda: Double,
    val quantidadeEmbalagem: Int,
    val unidadeMedida: String,
    val tipo: String,
    val quantidade: Int,
    @SerializedName("idSalao") val salaoId: Int
)
