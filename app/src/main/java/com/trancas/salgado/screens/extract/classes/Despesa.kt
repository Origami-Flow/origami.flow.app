package com.trancas.salgado.screens.extract.classes

data class Despesa(
    val nome: String,
    val descricao: String,
    val valor: Double,
    val data: List<Int>
)
