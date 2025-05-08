package com.trancas.salgado.screens.client

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.service.ClientService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ClientViewModel(
    private val api: ClientService
) : ViewModel() {


    var nome by mutableStateOf("")
    var id by mutableStateOf(0)
    var email by mutableStateOf("")
    var telefone by mutableStateOf("")
    var tipoCabelo by mutableStateOf("")
    var corCabelo by mutableStateOf("")
    var ocupacao by mutableStateOf("")
    private var _dataNascimento by mutableStateOf<LocalDate>(LocalDate.now(ZoneId.of("America/Sao_Paulo")))
    val dataNascimento: LocalDate get() = _dataNascimento

    fun setDataNascimento(data: LocalDate) {
        _dataNascimento = data
    }

    private val _erros = mutableStateListOf<String>()
    private fun validarCamposObrigatorios(nome:String, email:String, telefone:String): Boolean {
        _erros.clear()
        if (nome.isBlank()) _erros.add("Nome não pode ser vazio")
        if (email.isBlank()) _erros.add("Email não pode ser vazio")
        if (telefone.isBlank()) _erros.add("Telefone não pode ser vazio")
        return _erros.isEmpty()
    }

    fun addClient(nome:String, email:String, telefone:String) {
        viewModelScope.launch {
            if (validarCamposObrigatorios(nome, email, telefone)) {
                try {
                    val client = ClientRequestData(
                        nome, email, telefone, tipoCabelo, corCabelo, ocupacao,
                        dataNascimento.toString()
                    )
                    api.postClient(client)
                    Log.d("API", "Cliente adicionado com sucesso")
                    resetarCampos()  // Resetando os campos após o sucesso
                } catch (e: Exception) {
                    Log.e("API", "Erro ao adicionar cliente: ${e.message}")
                    _erros.add("Erro ao adicionar cliente")
                }
            } else {
                Log.d("API", "Erro de validação: ${_erros.joinToString(", ")}")
            }
        }
    }

    fun editClient(imagemBytes: ByteArray? = null, imagemNome: String? = null) {
        viewModelScope.launch {
            if (validarCamposObrigatorios(nome, email, telefone)) {
                try {
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

                    val nomePart = nome.toRequestBody("text/plain".toMediaTypeOrNull())
                    val emailPart = email.toRequestBody("text/plain".toMediaTypeOrNull())
                    val telefonePart = telefone.toRequestBody("text/plain".toMediaTypeOrNull())
                    val tipoCabeloPart = tipoCabelo.takeIf { it.isNotBlank() }?.toRequestBody("text/plain".toMediaTypeOrNull())
                    val corCabeloPart = corCabelo.takeIf { it.isNotBlank() }?.toRequestBody("text/plain".toMediaTypeOrNull())
                    val ocupacaoPart = ocupacao.takeIf { it.isNotBlank() }?.toRequestBody("text/plain".toMediaTypeOrNull())
                    val dataNascimentoPart = _dataNascimento.format(formatter).toRequestBody("text/plain".toMediaTypeOrNull())

                    val request =api.editClient(
                        1,
                        nomePart,
                        emailPart,
                        telefonePart,
                        tipoCabeloPart,
                        corCabeloPart,
                        ocupacaoPart,
                        dataNascimentoPart

                    )

                    Log.d("API", "Cliente editado com sucesso $request")
                    resetarCampos()

                } catch (e: Exception) {
                    Log.e("API", "Erro ao editar cliente: ${e.message}")
                    _erros.add("Erro ao editar cliente")
                }
            } else {
                Log.d("API", "Erro de validação: ${_erros.joinToString(", ")}")
            }
        }
    }


    fun getClient(id: Int) {
        viewModelScope.launch {
            _erros.clear()
            try {
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val response = api.getClient(id)
                Log.d("API", "Dados recebidos: $response")
                nome = response.nome
                email = response.email
                telefone = response.telefone
                tipoCabelo = response.tipoCabelo
                corCabelo = response.corCabelo
                ocupacao = response.ocupacao
                _dataNascimento = LocalDate.parse(response.dataNascimento, formatter)

                this@ClientViewModel.id = response.id
            } catch (e: Exception) {
                Log.e("API", "Erro ao buscar dados: ${e.message}")
                _erros.add("Erro ao buscar dados: ${e.message}")
            }
        }
    }

    private fun resetarCampos() {
        nome = ""
        email = ""
        telefone = ""
        tipoCabelo = ""
        corCabelo = ""
        ocupacao = ""
        _dataNascimento = LocalDate.now(ZoneId.of("America/Sao_Paulo"))
    }
}
