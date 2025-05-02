package com.trancas.salgado.screens.event

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class EventData(
    val id: Int? = null,
    @SerializedName("dataHoraInicio")
    val dateTimeStart: String,
    @SerializedName("dataHoraTermino")
    val dateTimeEnd: String,
    @SerializedName("tipoEvento")
    val eventType: String,
    @SerializedName("clienteId")
    val clientId: Int? = null,
    @SerializedName("servicoId")
    val serviceId: Int? = null,
    @SerializedName("trancistaId")
    val trancistId: Int? = null,
    @SerializedName("auxiliarId")
    val assistantId: Int? = null,
) {
}