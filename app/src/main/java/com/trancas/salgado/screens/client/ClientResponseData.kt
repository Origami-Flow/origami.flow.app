package com.trancas.salgado.screens.client

import java.time.LocalDate

data class ClientResponseData(    val id: Int,
                                  val nome: String,
                                  val email: String,
                                  val telefone: String,
                                  val tipoCabelo: String,
                                  val corCabelo: String,
                                  val ocupacao: String,
                                  val dataNascimento: String,
                                  val primeiraTranca: Boolean = false,
                                  val progressiva: Boolean = false,
    )