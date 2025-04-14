package com.trancas.salgado.screens.clients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.screens.clients.classes.ClientDataClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClientViewModel : ViewModel() {
    private val _clients = MutableStateFlow<List<ClientDataClass>>(emptyList())

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        loadClients()
    }

    private fun loadClients() {
        viewModelScope.launch {
            _clients.value = listOf(
                ClientDataClass(1,"Larissa"),
                ClientDataClass(2,"Julia"),
                ClientDataClass(3, "Camila"),
                ClientDataClass(4, "Luiza"),
                ClientDataClass(5, "Sabrina"),
                ClientDataClass(6, "Maya")
            )
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun getFilteredClients(): List<ClientDataClass> {
        return _clients.value.filter { it.nome.contains(_searchQuery.value, ignoreCase = true) }
    }
}