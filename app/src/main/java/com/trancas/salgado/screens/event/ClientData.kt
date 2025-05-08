package com.trancas.salgado.screens.event

import com.google.gson.annotations.SerializedName

data class ClientData(
    val id: Int,
    @SerializedName("nome")
    val name: String,
    val email : String = "",
    @SerializedName("telefone")
    val phone: String,
    @SerializedName("genero")
    val gender : String = "",
    @SerializedName("tipoCabelo")
    val hairType: String = "",
    @SerializedName("corCabelo")
    val hairColor : String = "",
    @SerializedName("ocupoacao")
    val ocupation : String = "",

)