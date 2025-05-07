package com.trancas.salgado.screens.extract.classes
import com.google.gson.annotations.SerializedName
import com.trancas.salgado.screens.schedule.classes.Event

data class TreatmentData(
    @SerializedName("nomeCliente")
    val clientName: String,
    @SerializedName("evento")
    val event: Event,
    @SerializedName("valor")
    val value: Double
)
