package com.trancas.salgado.screens.client

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel

class ClientViewModel : ViewModel() {

    //private val api =SalgadoApi.api
    private var nome =""
    private var email =""
    private var telefone =""
    private val _erros= mutableStateListOf<String>();

    fun addClient(nome: String, email: String, telefone: String) {

        try{

        }catch (ex :Exception){
            _erros.add("Erro ao adicionar cliente")
        }
    }
}



