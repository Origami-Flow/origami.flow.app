package com.trancas.salgado.screens.schedule.classes

data class Service(
    val id: Int,
    val nome: String,
    val valorMinimoServico: Double?,
    val valorMaximoServico: Double?,
    val descricao: String
)
