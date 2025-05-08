package com.trancas.salgado.screens.clients

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.screens.event.ClientData
import com.trancas.salgado.service.ClientService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClientViewModel : ViewModel() {
    private val _clients = mutableStateListOf<ClientData>()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        loadClients()
    }

    private fun loadClients() {
        viewModelScope.launch {
            try {
                val api = ClientService.api
                val clientsResponse = api.getClients()
                _clients.clear()
                _clients.addAll(clientsResponse)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun getFilteredClients(): List<ClientData> {
        return _clients.filter { it.name.contains(_searchQuery.value, ignoreCase = true) }
    }
}