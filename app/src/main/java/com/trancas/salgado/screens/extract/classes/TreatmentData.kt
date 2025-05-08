package com.trancas.salgado.screens.extract.classes
import com.google.gson.annotations.SerializedName
import com.trancas.salgado.screens.event.ClientData
import com.trancas.salgado.screens.schedule.classes.Event

data class TreatmentData(
    @SerializedName("evento")
    val event: Event,
    @SerializedName("receita")
    val value: Double,
    @SerializedName("cliente")
    val client: ClientData,
)
