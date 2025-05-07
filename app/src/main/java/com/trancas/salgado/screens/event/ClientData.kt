package com.trancas.salgado.screens.event

import com.google.gson.annotations.SerializedName

data class ClientData(
    val id: Int,
    @SerializedName("nome")
    val name: String,
    @SerializedName("telefone")
    val phone: String,
)
