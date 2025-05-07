package com.trancas.salgado.screens.extract.classes

import com.google.gson.annotations.SerializedName

data class ExpenseData(
    @SerializedName("nome")
    val name: String,
    @SerializedName("descricao")
    val description: String,
    @SerializedName("data")
    val date: String,
    @SerializedName("valor")
    val value: Double
)
