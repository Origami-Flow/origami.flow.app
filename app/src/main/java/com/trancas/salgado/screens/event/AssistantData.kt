package com.trancas.salgado.screens.event

import com.google.gson.annotations.SerializedName

data class AssistantData(
    val id: Int,
    @SerializedName("nome")
    val name: String,
    val email: String
)
