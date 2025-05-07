package com.trancas.salgado.screens.client

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.service.ClientService
import kotlinx.coroutines.launch

class ClientViewModel(
    private val api: ClientService
) : ViewModel() {

    private var nome = ""
    private var id = 0
    private var email = ""
    private var telefone = ""
    private var tipoCabelo = ""
    private var corCabelo = ""
    private var ocupacao = ""
    private var dataNascimento = ""
    private val _erros = mutableStateListOf<String>()

    fun addClient(nome: String, email: String, telefone: String) {
        _erros.clear()
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
                    val client = ClientRequestData(nome, email, telefone, tipoCabelo, corCabelo, ocupacao, dataNascimento)
                    api.postClient(client)
                    Log.d("API", "Cliente adicionado com sucesso")
                } else {
                    Log.d("API", "Erro: ${_erros.joinToString(", ")}")
                }
            } catch (e: Exception) {
                Log.e("API", "Erro: ${e.message}")
                _erros.add("Erro ao adicionar cliente")
            }
        }
    }

    fun editClient() {
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
                    val client = ClientRequestData(
                        nome = nome,
                        email = email,
                        telefone = telefone,
                        tipoCabelo = tipoCabelo,
                        corCabelo = corCabelo,
                        ocupacao = ocupacao,
                        dataNascimento = dataNascimento
                    )
                    api.editClient(id, client)
                    Log.d("API", "Cliente editado com sucesso")
                } else {
                    Log.d("API", "Erro: ${_erros.joinToString(", ")}")
                }
            } catch (e: Exception) {
                Log.e("API", "Erro ao editar cliente: ${e.message}")
                _erros.add("Erro ao editar cliente")
            }
        }
    }
    fun getClient(id: Int) {
        viewModelScope.launch {
            _erros.clear()
            try {
                val response = api.getClient(id);
                Log.d("API", "Dados recebidos: ${response}")
                }
             catch (e: Exception) {
                Log.e("API", "Erro ao buscar dados: ${e.message}")
                _erros.add("Erro ao buscar dados: ${e.message}")
            }
        }
    }
}

