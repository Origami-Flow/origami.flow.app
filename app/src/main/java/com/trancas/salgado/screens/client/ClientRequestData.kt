package com.trancas.salgado.screens.client

data class ClientRequestData(
    val nome: String,
    val email: String,
    val telefone: String,
    val tipoCabelo: String?,
    val corCabelo: String?,
    val ocupacao: String?,
    val dataNascimento: String?
)