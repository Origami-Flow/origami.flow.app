package com.trancas.salgado.screens.schedule.classes

data class Event(
    val id: Int,
    val dataHoraInicio: String,
    val dataHoraTermino: String,
    val valorCobrado: Double?,
    val tipoEvento: String,
    val statusEvento: String,
    val cliente: Client,
    val servico: Service,
    val trancista: Trancista,
    val auxiliar: Auxiliar?
)
