package com.trancas.salgado.screens.client

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.service.SalgadoApi
import kotlinx.coroutines.launch

class ClientViewModel : ViewModel() {

    private val api = SalgadoApi.api
    private var nome =""
    private var email =""
    private var telefone =""
    private val _erros= mutableStateListOf<String>();

    fun addClient(nome: String, email: String, telefone: String) {
        _erros.clear();
        viewModelScope.launch {
            try {

                if (nome.isEmpty()) {
                    _erros.add("Nome não pode ser vazio")
                }
                if (email.isEmpty()) {
                    _erros.add("Email não pode ser vazio")
                }
                if (telefone.isEmpty()) {
                    _erros.add("Telefone não pode ser vazio")
                }
                if (_erros.isEmpty()) {

                    val client = AddClientData(nome, email, telefone);
                    val response = api.postClient(client);
                    Log.d("API", "Cliente adicionado com sucesso")
                } else {
                    Log.d("API", "Erro: ${_erros.joinToString(", ")}")
                }
            } catch (e: Exception) {
                Log.d("API", "Erro: ${e.message}")
                _erros.add("Erro ao adicionar cliente")
                //corrigir Log.d para Log.e
            }
        }
    }
}



