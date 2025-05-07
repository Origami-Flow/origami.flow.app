package com.trancas.salgado.screens.event

import com.google.gson.annotations.SerializedName

data class ServicesData(
    val id:Int,
    @SerializedName("nome")
    val name: String
)
