package com.trancas.salgado.screens.extract.classes

data class Atendimento(
    val nomeCliente: String,
    val tipoAtendimento: Boolean,
    val data: List<Int>,
    val valor: Double
)